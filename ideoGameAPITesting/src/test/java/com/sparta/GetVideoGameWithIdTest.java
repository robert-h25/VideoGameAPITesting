package com.sparta;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

public class GetVideoGameWithIdTest {
    private static Response response;
    private static final int id = 1;

    @BeforeAll
    static void beforeAll() {
        response = RestAssured
                .given(Utils.getVideoGameWithId_V1(id))
                .when()
                    .get()
                .then()
                    .log().all()
                    .extract().response();
    }

    @Test
    @DisplayName("Get video game with a specified id from video game database")
    void GetVideoGame_withId_fromVideoGameDatabase_v1() {
        MatcherAssert.assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    @DisplayName("Get video game with an invalid id from video game database")
    void GetVideoGame_withInvalidId_fromVideoGameDatabase_v1() {
        response = RestAssured
                .given(Utils.getVideoGameWithId_V1(900))
                .when()
                .get()
                .then()
                .log().all()
                .extract().response();
        MatcherAssert.assertThat(response.getStatusCode(), equalTo(404));
    }
}
