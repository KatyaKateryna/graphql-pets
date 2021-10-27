package com.softserve.graphqlpets.resolver;

import com.softserve.graphqlpets.dto.Cat;
import com.softserve.graphqlpets.dto.Passport;
import com.softserve.graphqlpets.service.PassportService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class CatResolver implements GraphQLResolver<Cat> {

    private final PassportService passportService;

    public CatResolver(PassportService passportService) {
        this.passportService = passportService;
    }

    public Passport passport(Cat cat) {
        return passportService.getPassport(cat.getId());
    }
}
