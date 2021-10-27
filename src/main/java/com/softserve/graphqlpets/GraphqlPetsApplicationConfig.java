package com.softserve.graphqlpets;

import com.softserve.graphqlpets.dto.Cat;
import com.softserve.graphqlpets.dto.Dog;
import graphql.kickstart.tools.SchemaParserDictionary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphqlPetsApplicationConfig {

    /**
     * Unfortunately, the GraphQL autoconfiguration cannot discover interface implementations.
     * We have to manually register them in the schema dictionary.
     * <p>
     * Note: if the concrete type is already returned by any query or mutation,
     * we don't need to manually register it. In this case, {@link Cat} registration is optional,
     * whereas {@link Dog} has to be registered.
     */
    @Bean
    public SchemaParserDictionary dictionary() {
        SchemaParserDictionary dictionary = new SchemaParserDictionary();
        dictionary.add(Cat.class);
        dictionary.add(Dog.class);
        return dictionary;
    }
}
