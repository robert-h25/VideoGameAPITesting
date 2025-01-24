package com.sparta.stepdefs;

import com.sparta.Utils;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class VideoGameListStepdefs {
    Response response;
    RequestSpecification request;

    @Given("The API end-point videogame")
    public void the_api_end_point_videogame() {
        request = Utils.getVideoGameList();
    }

    @When("I send GET HTTP request to API videogame")
    public void i_send_GET_http_request_to_api_videogame() {
        response = RestAssured
                .given(request)
                .when()
                .get()
                .then()
                .log().all()
                .extract().response();
    }

    @Then("I should receive a list of video games in a list")
    public void i_should_receive_a_list_of_video_games_in_a_list() {
        assertThat("Response body should not be null", response.getBody().asString(), is(not(emptyString())));
        assertThat("Response should contain a list of video games", response.jsonPath().getList("$"), is(not(empty())));
    }

    @And("I get 200 status code")
    public void i_get_200_status_code() {
        assertThat("Status code should be 200", response.getStatusCode(), is(200));
    }


    @And("the response body should contain a list of video games with attributes")
    public void theResponseBodyShouldContainAListOfVideoGamesWithAttributes() {
        // Verify each game object contains expected attributes
        response.jsonPath().getList("$").forEach(game -> {
            assertThat("Game should have an id", ((Map<?, ?>) game).get("id"), is(notNullValue()));
            assertThat("Game should have a name", ((Map<?, ?>) game).get("name"), is(notNullValue()));
            assertThat("Game should have a releaseDate", ((Map<?, ?>) game).get("releaseDate"), is(notNullValue()));
            assertThat("Game should have a reviewScore", ((Map<?, ?>) game).get("reviewScore"), is(notNullValue()));
            assertThat("Game should have a category", ((Map<?, ?>) game).get("category"), is(notNullValue()));
            assertThat("Game should have a rating", ((Map<?, ?>) game).get("rating"), is(notNullValue()));
        });
    }

}

