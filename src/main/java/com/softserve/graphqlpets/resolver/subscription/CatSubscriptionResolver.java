package com.softserve.graphqlpets.resolver.subscription;

import com.softserve.graphqlpets.reactive.CatCountPublisher;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
public class CatSubscriptionResolver implements GraphQLSubscriptionResolver {

    private final CatCountPublisher catCountPublisher;

    public CatSubscriptionResolver(CatCountPublisher catCountPublisher) {
        this.catCountPublisher = catCountPublisher;
    }

    public Publisher<Integer> catCount() {
        return catCountPublisher.getPublisher();
    }
}