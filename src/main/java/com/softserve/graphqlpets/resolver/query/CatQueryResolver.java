package com.softserve.graphqlpets.resolver.query;

import com.softserve.graphqlpets.dto.Cat;
import com.softserve.graphqlpets.service.CatService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Component
public class CatQueryResolver implements GraphQLQueryResolver {

    private final CatService catService;

    public CatQueryResolver(CatService catService) {
        this.catService = catService;
    }

    public Optional<Cat> cat(UUID id) {
        return catService.findById(id);
    }

    public Collection<Cat> allCats() {
        return catService.getAllCats();
    }
}
