package com.softserve.graphqlpets.service.impl;

import com.softserve.graphqlpets.dto.Cat;
import com.softserve.graphqlpets.dto.CatInput;
import com.softserve.graphqlpets.dto.Color;
import com.softserve.graphqlpets.exception.DuplicateCatException;
import com.softserve.graphqlpets.reactive.CatCountPublisher;
import com.softserve.graphqlpets.service.CatService;
import graphql.com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CatServiceImpl implements CatService {

    private final Map<UUID, Cat> cats = new ConcurrentHashMap<>();

    private final CatCountPublisher catCountPublisher;

    public CatServiceImpl(CatCountPublisher catCountPublisher) {
        this.catCountPublisher = catCountPublisher;
    }

    @PostConstruct
    private void setUp() {
        Cat foo = new Cat(UUID.fromString("00000000-0000-0000-0000-000000000001"), "Foo", Sets.immutableEnumSet(Color.BLACK));
        Cat bar = new Cat(UUID.fromString("00000000-0000-0000-0000-000000000002"), "Bar", Sets.immutableEnumSet(Color.RED, Color.WHITE));

        cats.put(foo.getId(), foo);
        cats.put(bar.getId(), bar);
    }

    @Override
    public Cat findById(UUID id) {
        return cats.get(id);
    }

    @Override
    public Cat createCat(CatInput cat) {
        if (cats.values().stream().anyMatch(c -> c.getName().equals(cat.getName()))) {
            throw new DuplicateCatException(cat.getName());
        }

        Cat newCat = new Cat(UUID.randomUUID(), cat.getName(), cat.getColors() == null ? Collections.emptySet() : cat.getColors());

        cats.put(newCat.getId(), newCat);
        catCountPublisher.publish(cats.size());

        return newCat;
    }

    @Override
    public Collection<Cat> getAllCats() {
        return cats.values();
    }
}