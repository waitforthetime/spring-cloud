//package com.wy.study.gateway.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.context.SecurityContextRepository;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//import javax.annotation.Resource;
//
///**
// * @author 王元
// * @description 简单描述一下
// * @date 2019/3/7 下午2:56
// */
//@Configuration
//public class SecurityConfiguration {
//
//    @Resource
//    private AuthenticationManager authenticationManager;
//
//    @Resource
//    private SecurityContextRepository securityContextRepository;
//
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        // Disable default security.
//        http.httpBasic().disable();
//        http.formLogin().disable();
//        http.csrf().disable();
//        http.logout().disable();
//
//        // Add custom security.
//        http.authenticationManager(this.authenticationManager);
//        http.securityContextRepository(this.securityContextRepository);
//
//        // Disable authentication for `/auth/**` routes.
//        http.authorizeExchange().pathMatchers("/auth/**").permitAll();
//        http.authorizeExchange().anyExchange().authenticated();
//
//        return http.build();
//    }
//}
