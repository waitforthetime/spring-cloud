package com.wy.study.oauth2.server.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.annotation.Resource;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/7 上午9:47
 */
@Configuration
@EnableResourceServer
@EnableAuthorizationServer
public class Oauth2Config {


    @Bean
    public AuthorizationServerConfigurer authorizationServerConfigurer() {
        return new Oauth2ServerConfig();
    }

    @Bean
    public ResourceServerConfigurer resourceServerConfigurer() {
        return new ResourceServerConfig();
    }


    class SecurityConfig extends WebSecurityConfigurerAdapter {

    }


    class ResourceServerConfig extends ResourceServerConfigurerAdapter {


        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId("oauth2server");
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .logout()
                    .and()
                    .httpBasic()
                    .and()
                    .formLogin()
                    .and()
                    .authorizeRequests()
                        .mvcMatchers("/test/world").permitAll()
                        .mvcMatchers("/actuator/**").permitAll()
                        .anyRequest().authenticated();
        }
    }


    class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

        @Resource
        private AuthenticationConfiguration authenticationConfiguration;

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

            security.tokenKeyAccess("isAuthenticated()")
                    .checkTokenAccess("isAuthenticated()")
                    .passwordEncoder(NoOpPasswordEncoder.getInstance())
                    .allowFormAuthenticationForClients();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("client_id")
                    .scopes("read", "write")
                    .authorizedGrantTypes("authorization_code",
                            "password", "client_credentials", "implicit", "refresh_token")
                    .resourceIds("oauth2server")
                    .secret("client_secret")
                    .authorities("ROLE_USER", "ROLE_WRITE");
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

            endpoints
                    .authenticationManager(authenticationConfiguration.getAuthenticationManager());
        }
    }
}
