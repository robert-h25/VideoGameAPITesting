package com.sparta;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestDELETECommandsWithFirstController {

    static Response response;

    private static List<Integer> ids;

    static Response makeResponse(Integer id) {
        return RestAssured
                .given(Utils.requestSpecificationWithID(id))
                .when()
                .log().all()
                .delete()
                .then()
                .log().all()
                .extract().response();
    }

    @BeforeAll
    static void setUp() {
        ids = new ArrayList<>();
        ids.add(1);
        ids.add(11);
    }

    @Test
    @DisplayName("Happy Path: Test returned status code is 200")
    void getStatusCode200() {
        response = makeResponse(ids.get(0));
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Sad Path: Test returned status code 404")
    void getStatusCode404() {
        response = makeResponse(ids.get(1));
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(404));
    }

    @Test
    @DisplayName("Test for response body")
    void testResponseBody(){
        response = makeResponse(ids.get(0));
        String responseBody = response.getBody().asString();

       MatcherAssert.assertThat(responseBody, Matchers.is( "Video game deleted")  );
    }


}
