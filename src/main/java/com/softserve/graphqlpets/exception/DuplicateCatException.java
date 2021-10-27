package com.softserve.graphqlpets.exception;

public class DuplicateCatException extends RuntimeException {
    public DuplicateCatException(String catName) {
        super(String.format("Cat with name %s already exists!", catName));
    }
}