package com.sparta;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestPUTCommandsWithFirstController {

    static Response response;

    private static List<Integer> ids;


    static Response makeResponse(Integer id) {
        return RestAssured
                .given(Utils.requestSpecificationWithIdAndBody(id))
                .when()
                .log().all()
                .put()
                .then()
                .log().all()
                .extract().response();
    }

    static Response makeBadResponse(int id) {

        return RestAssured
                .given(Utils.requestSpecificationWithIdAndIncorrectBody(id))
                .when()
                .log().all()
                .put()
                .then()
                .log().all()
                .extract().response();
    }

    @BeforeAll
    static void setUp() {
        Utils.getBearerToken();
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
    @DisplayName("Sad Path: Test returned status code 400")
    void getStatusCode400() {
        response = makeBadResponse(ids.get(0));
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(400));
    }

    @Test
    @DisplayName("Sad Path: Test returned status code 404")
    void getStatusCode404() {


        response = makeResponse(ids.get(1));
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(404));
    }


}
