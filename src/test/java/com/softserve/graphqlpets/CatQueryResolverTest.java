package com.softserve.graphqlpets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.softserve.graphqlpets.dto.Cat;
import com.softserve.graphqlpets.dto.Color;
import com.softserve.graphqlpets.dto.InternalPassport;
import com.softserve.graphqlpets.service.CatService;
import com.softserve.graphqlpets.service.PassportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@GraphQLTest(useDefaultFilters = true)
class CatQueryResolverTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    private CatService catService;

    @MockBean
    private PassportService passportService;

    @Test
    void shouldGetCat() throws IOException {
        UUID catId = UUID.randomUUID();
        String catName = "Foo";
        Color catColor = Color.BLACK;
        LocalDate birthDate = LocalDate.now();

        ObjectNode variables = new ObjectMapper().createObjectNode();
        variables.put("id", catId.toString());

        when(catService.findById(catId)).thenReturn(new Cat(catId, catName, Collections.singleton(catColor)));
        when(passportService.getPassports(Collections.singletonList(catId), true))
                .thenReturn(Collections.singletonList(new InternalPassport(birthDate)));

        GraphQLResponse response = graphQLTestTemplate.perform("cat.graphql", variables);

        assertNotNull(response);
        assertTrue(response.isOk());

        assertEquals(catName, response.get("$.data.cat.name"));
        assertEquals(Collections.singletonList(catColor), response.getList("$.data.cat.colors", Color.class));
        assertEquals(birthDate.format(DateTimeFormatter.ISO_DATE), response.get("$.data.cat.passport.birthDate"));
    }

}
