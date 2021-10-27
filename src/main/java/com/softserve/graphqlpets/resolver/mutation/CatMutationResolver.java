package com.softserve.graphqlpets.resolver.mutation;

import com.softserve.graphqlpets.dto.Cat;
import com.softserve.graphqlpets.dto.CatInput;
import com.softserve.graphqlpets.service.CatService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class CatMutationResolver implements GraphQLMutationResolver {

    private final CatService catService;

    public CatMutationResolver(CatService catService) {
        this.catService = catService;
    }

    public Cat createCat(CatInput cat) {
        return catService.createCat(cat);
    }
}
