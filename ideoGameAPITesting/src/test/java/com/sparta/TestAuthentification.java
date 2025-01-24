package com.sparta;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestAuthentification {

    static Response response;


    @BeforeAll
    public static void setup() {
        response = RestAssured
                .given(Utils.getAuthenticatedRequest())
                .when()
                .post()
                .then().extract().response();


    }


    @Test
    @DisplayName("Status code 200")
    public void statusCode200() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }




}
