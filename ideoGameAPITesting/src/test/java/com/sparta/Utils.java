package com.sparta;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import java.util.Map;


public class Utils {
    private static final String BASE_URI = "https://videogamedb.uk:443/api";
    private static final String BASE_PATH_TO_VIDEOGAME = "/videogame";
    private static final String BASE_PATH_TO_VIDEOGAME_ID = "/videogame/{id}";
    private static final String BASE_PATH_WITH_V2_VIDEOGAME = "/v2/videogame";
    private static final String BASE_PATH_WITH_V2_VIDEOGAME_ID = "/v2/videogame/{id}";

    private static final String BEARER_TOKEN =
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczNzcyODUzMCwiZXhwIjoxNzM3NzMyMTMwfQ.oL2RLBDwo-9I0Vqz9LKjIFMa7OSAWu9eoH82MFKQL-4";

    static int id;



    static void getBearerToken() {
        Response response = RestAssured
                .given(getAuthenticatedRequest())
                .when()
                .post()
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response();

        BEARER_TOKEN = "Bearer " + response.jsonPath().getString("token");

    }

    private static RequestSpecification getAuthenticatedRequest() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH_TO_AUTHENTICATE)
                .setContentType("application/json")
                .setBody("{\n" +
                        "  \"password\": \"admin\",\n" +
                        "  \"username\": \"admin\"\n" +
                        "}")
                .build();
    }

    private static RequestSpecBuilder getBaseSpecBuilder() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ));
    }

    private static RequestSpecBuilder getBaseSpecBuilderWithPath(String path, Integer id) {
        return new RequestSpecBuilder()
                .setBaseUri(path)
                .addPathParams(Map.of(
                        "id", id
                ))
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ));
    }


//    static RequestSpecification requestSpecificationWithIdAndBody(Integer id, String body) {
//        return getBaseSpecBuilder()
//
//                .setContentType(ContentType.JSON)
//                .build();
//    };
    static RequestSpecification requestSpecificationWithIdAndBody(Integer id) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH_TO_VIDEOGAME_ID)
                .addHeaders(Map.of(
                        "Accept", "application/json",
                        "Authorization",BEARER_TOKEN
                ))
                .addPathParams(Map.of(
                        "id", id
                ))
                .setContentType(ContentType.JSON)
                .setBody(CORRECT_BODY)
                .build();
    }

    static RequestSpecification requestSpecificationWithIdAndIncorrectBody(Integer id) {
        return getBaseSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH_TO_VIDEOGAME_ID)
                .addHeaders(Map.of(
                        "Accept", "application/json",
                        "Authorization",BEARER_TOKEN
                ))
                .addPathParams(Map.of(
                        "id",id
                ))
                .setContentType(ContentType.JSON)
                .setBody( "" )
                .build();
    };


}