package com.wy.study.zuul.demo.ribbon.predicate;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/13 上午11:27
 */
public abstract class DiscoveryEnabledServerPredicate extends AbstractServerPredicate {


    @Override
    public boolean apply(PredicateKey input) {
        return null != input &&
                input.getServer() instanceof DiscoveryEnabledServer &&
                predicate(input);
    }

    public abstract boolean predicate(PredicateKey key);
}
