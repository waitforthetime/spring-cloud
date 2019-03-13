package com.wy.study.zuul.demo.ribbon.predicate;

import com.netflix.loadbalancer.PredicateKey;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import com.netflix.zuul.context.RequestContext;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

import static com.wy.study.zuul.demo.constants.ZuulConstants.RIBBON_METADATA_PROFILE;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/13 上午11:33
 */
public class MetadataPredicate extends DiscoveryEnabledServerPredicate {

    @Override
    public boolean predicate(PredicateKey key) {

        RequestContext currentContext = RequestContext.getCurrentContext();
        String ribbonMetadataProfile = (String) currentContext.get(RIBBON_METADATA_PROFILE);

        DiscoveryEnabledServer server = (DiscoveryEnabledServer) key.getServer();
        Map<String, String> metadata = server.getInstanceInfo().getMetadata();
        String profile = metadata.get("profile");

        if (StringUtils.isAllEmpty(ribbonMetadataProfile, profile) || Objects.equals(ribbonMetadataProfile, profile)) {
            return true;
        }
        return false;
    }
}
