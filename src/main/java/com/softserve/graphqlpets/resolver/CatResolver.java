package com.softserve.graphqlpets.resolver;

import com.softserve.graphqlpets.dto.Cat;
import com.softserve.graphqlpets.dto.Passport;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class CatResolver implements GraphQLResolver<Cat> {

    public CompletableFuture<Passport> passport(Cat cat, boolean fastLoad, DataFetchingEnvironment dfe) {
        DataLoader<UUID, Passport> dataLoader = dfe.getDataLoader(Passport.class.getSimpleName());

        return dataLoader.load(cat.getId(), fastLoad);
    }
}
