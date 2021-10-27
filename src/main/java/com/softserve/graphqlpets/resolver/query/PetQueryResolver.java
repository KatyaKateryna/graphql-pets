package com.softserve.graphqlpets.resolver.query;

import com.softserve.graphqlpets.dto.Dog;
import com.softserve.graphqlpets.dto.Pet;
import com.softserve.graphqlpets.service.CatService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PetQueryResolver implements GraphQLQueryResolver {

    private static final UUID UUID_0 = UUID.fromString("00000000-0000-0000-0000-000000000000");

    private final CatService catService;

    public PetQueryResolver(CatService catService) {
        this.catService = catService;
    }

    public Pet pet(UUID id) {
        if (id.equals(UUID_0)) {
            return new Dog(id, "Fluffy", "Bulldog");
        } else {
            return catService.findById(id);
        }
    }
}
