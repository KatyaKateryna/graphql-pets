package com.softserve.graphqlpets.util;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.function.BiFunction;

import static graphql.scalars.util.Kit.typeName;

public class UuidCoercing implements Coercing<UUID, String> {

    /**
     * Convert Java type into a query or mutation result
     */
    @Override
    public String serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
        return dataFetcherResult.toString();
    }

    /**
     * Convert value passed into the query or mutation as a variable to Java type
     */
    @Override
    @NotNull
    public UUID parseValue(@NotNull Object input) throws CoercingParseValueException {
        return parseFromString(String.valueOf(input), CoercingParseValueException::new);
    }

    /**
     * Convert value passed directly into the query or mutation to Java type
     */
    @Override
    @NotNull
    public UUID parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
        if (!(input instanceof StringValue)) {
            throw new CoercingParseLiteralException(
                    "Expected AST type 'StringValue' but was '" + typeName(input) + "'."
            );
        }
        String value = ((StringValue) input).getValue();
        return parseFromString(value, CoercingParseLiteralException::new);
    }

    private UUID parseFromString(String string, BiFunction<String, Throwable, RuntimeException> exceptionMaker) {
        try {
            return UUID.fromString(string);
        } catch (Exception e) {
            throw exceptionMaker.apply(String.format("Invalid UUID %s", string), e);
        }
    }
}
