package com.sparta;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import java.util.Map;


public class Utils {
    private static final String BASE_URL = "https://videogamedb.uk:443/api";
    private static final String BASE_PATH_WITH_V1_VIDEOGAME_ID = "/videogame/{id}";
    private static final String POST_PATH_WITH_VIDEOGAME = "/videogame";
    private static final String POST_PATH_WITH_V2_VIDEOGAME = "/v2/videogame";
    private static final String BASE_PATH_WITH_V2_VIDEOGAME_ID = "/v2/videogame/{id}";
    private static final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczNzczMDgzMCwiZXhwIjoxNzM3NzM0NDMwfQ.eHVPHfKPnJRAOWt3BG8OC7CYAdgyDCS-wp4gI30CC0I";


    static RequestSpecification getVideoGameWithId_V1(Integer id){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(BASE_PATH_WITH_V1_VIDEOGAME_ID)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ))
                .addPathParams(Map.of(
                        "id", id
                ))
                .build();
    }

    static RequestSpecification getVideoGame_WithId_v2(Integer id){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(BASE_PATH_WITH_V2_VIDEOGAME_ID)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ))
                .addPathParams(Map.of(
                        "id", id
                ))
                .build();
    }

    private static RequestSpecBuilder getBaseSpecBuilder() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ));
    }
    static RequestSpecification requestSpecification(Integer id,String body) {
        return getBaseSpecBuilder()

                .setContentType(ContentType.JSON)
                .build();
    };
    static RequestSpecification requestSpecificationID(Integer id,String body) {
        return getBaseSpecBuilder()
                .addPathParams(Map.of(
                        "id",id
                ))
                .setContentType(ContentType.JSON)
                .setBody( body )
                .build();
    };

    static RequestSpecification postVideoGame_v1(PostVideoGameRequestBody requestBody) {
        return new RequestSpecBuilder()
                //TODO: May need to include bearer token
                .addHeaders(Map.of(
                        "Accept", "application/json",
                        "Content-Type", "application/json",
                        "Authorization", "Bearer " + BEARER_TOKEN
                ))
                .setBaseUri(BASE_URL)
                .setBasePath(POST_PATH_WITH_VIDEOGAME)
                .setContentType(ContentType.JSON)
                .setBody(requestBody)
                //TODO: Need to add request body json, records...
                .build();
    }

    static RequestSpecification postVideoGame_v2(PostVideoGameRequestBody requestBody) {
        return new RequestSpecBuilder()
                //TODO: May need to include bearer token
                .addHeaders(Map.of(
                        "Accept", "application/json",
                        "Content-Type", "application/json"
                ))
                .setBaseUri(BASE_URL)
                .setBasePath(POST_PATH_WITH_V2_VIDEOGAME)
                .setContentType(ContentType.JSON)
                .setBody(requestBody)
                //TODO: Need to add request body json, records...
                .build();
    }

    static Response createVideoGame_AndPostToDatabase_v1(int id, String category, String name, String rating, String releaseDate, int reviewScore) {
        Response response =
                RestAssured
                        .given(postVideoGame_v1(new PostVideoGameRequestBody(id, category, name, rating, releaseDate, reviewScore)))
                        .when()
                        .post()
                        .then()
                        .log().all()
                        .assertThat().statusCode(200)
                        .extract().response();
        return response;
    }

    static Response createVideoGame_AndPostToDatabase_v2(int id, String category, String name, String rating, String releaseDate, int reviewScore) {
        Response response =
                RestAssured
                        .given(postVideoGame_v2(new PostVideoGameRequestBody(id, category, name, rating, releaseDate, reviewScore)))
                        .when()
                            .post()
                        .then()
                            .log().all()
                            .assertThat().statusCode(200)
                            .extract().response();
        return response;
    }
}