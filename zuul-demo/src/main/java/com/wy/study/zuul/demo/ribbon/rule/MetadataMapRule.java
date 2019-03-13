package com.wy.study.zuul.demo.ribbon.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.AvailabilityPredicate;
import com.netflix.loadbalancer.CompositePredicate;
import com.netflix.loadbalancer.PredicateBasedRule;
import com.netflix.loadbalancer.ZoneAvoidancePredicate;
import com.wy.study.zuul.demo.ribbon.predicate.MetadataPredicate;

import org.springframework.stereotype.Component;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/13 上午11:16
 */
@Component
public class MetadataMapRule extends PredicateBasedRule {

    private CompositePredicate compositePredicate;

    public MetadataMapRule() {
        super();
        MetadataPredicate metadataPredicate = new MetadataPredicate();
        ZoneAvoidancePredicate zonePredicate = new ZoneAvoidancePredicate(this, null);
        AvailabilityPredicate availabilityPredicate = new AvailabilityPredicate(this, null);
        compositePredicate = createCompositePredicate(metadataPredicate, zonePredicate, availabilityPredicate);
    }

    private CompositePredicate createCompositePredicate(MetadataPredicate metadataPredicate, ZoneAvoidancePredicate p1, AvailabilityPredicate p2) {
        return CompositePredicate.withPredicates(metadataPredicate, p1, p2)
                .addFallbackPredicate(p2)
                .addFallbackPredicate(AbstractServerPredicate.alwaysTrue())
                .build();

    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        MetadataPredicate metadataPredicate = new MetadataPredicate();
        ZoneAvoidancePredicate zonePredicate = new ZoneAvoidancePredicate(this, clientConfig);
        AvailabilityPredicate availabilityPredicate = new AvailabilityPredicate(this, clientConfig);
        compositePredicate = createCompositePredicate(metadataPredicate, zonePredicate, availabilityPredicate);
    }
    @Override
    public AbstractServerPredicate getPredicate() {
        return compositePredicate;
    }
}
