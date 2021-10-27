package com.softserve.graphqlpets.resolver.query;

import com.softserve.graphqlpets.dto.Dog;
import com.softserve.graphqlpets.dto.Pet;
import com.softserve.graphqlpets.service.CatService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class PetQueryResolver implements GraphQLQueryResolver {

    private final CatService catService;

    public PetQueryResolver(CatService catService) {
        this.catService = catService;
    }

    public Pet pet(Integer id) {
        if (id == 0) {
            return new Dog(0, "Fluffy", "Bulldog");
        } else {
            return catService.findById(id);
        }
    }
}
