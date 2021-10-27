package com.softserve.graphqlpets.resolver.query;

import com.softserve.graphqlpets.dto.Cat;
import com.softserve.graphqlpets.service.CatService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class CatQueryResolver implements GraphQLQueryResolver {

    private final CatService catService;

    public CatQueryResolver(CatService catService) {
        this.catService = catService;
    }

    public Cat cat(Integer id) {
        return catService.findById(id);
    }
}
