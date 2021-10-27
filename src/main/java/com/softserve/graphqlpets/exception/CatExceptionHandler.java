package com.softserve.graphqlpets.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.kickstart.spring.error.ErrorContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class CatExceptionHandler {

    @ExceptionHandler(value = DuplicateCatException.class)
    public GraphQLError handleDuplicateCatException(DuplicateCatException e, ErrorContext errorContext) {
        return GraphqlErrorBuilder.newError()
                .message(e.getMessage())
                .errorType(errorContext.getErrorType())
                .locations(errorContext.getLocations())
                .path(errorContext.getPath())
                .build();
    }
}
