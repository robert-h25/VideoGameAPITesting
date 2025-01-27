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
    private static final String BASE_URI = "https://videogamedb.uk:443/api";
    private static final String BASE_PATH_TO_AUTHENTICATE = "/authenticate";
    private static final String BASE_PATH_TO_VIDEOGAME_ID = "/videogame/{id}";
    private static final String BASE_PATH_WITH_V2_VIDEOGAME_ID = "/v2/videogame/{id}";

    private static final String CORRECT_BODY = "{\n" +
            "  \"category\": \"Platform\",\n" +
            "  \"name\": \"Mario\",\n" +
            "  \"rating\": \"Mature\",\n" +
            "  \"releaseDate\": \"2012-05-04\",\n" +
            "  \"reviewScore\": 85\n" +
            "}";
    private static final String INCORRECT_BODY = "{\n" +
            "  \"category\": \"Platform\",\n" +
            "  \"name\": \"Mario2\",\n" +
            "  \"rating\": \"Mature\",\n" +
            "  \"releaseDate\": \"2012-05-04\",\n" +
            "  \"reviewScore\": 85\n" +
            "}";

    private static String BEARER_TOKEN;
    static int id;



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

     static RequestSpecification getAuthenticatedRequest() {
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

    static RequestSpecification getAuthenticatedRequestWrongBody() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH_TO_AUTHENTICATE)
                .setContentType("application/json")
                .setBody("{\n" +
                        "  \"password\": \"ssss\",\n" +
                        "  \"username\": \"ssss\"\n" +
                        "}")
                .build();
    }

    private static RequestSpecBuilder getBaseSpecBuilder() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ));
    }
    private static RequestSpecBuilder getBaseSpecBuilderXML() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeaders(Map.of(
                        "Accept", "application/xml"
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
    }




    static RequestSpecification requestSpecificationIDNoBody(Integer id) {
        return getBaseSpecBuilderWithPath(BASE_PATH_TO_VIDEOGAME_ID,id)
//                .setBasePath(POST_COMMENT_VIDEOGAME_ID)

                .setContentType(ContentType.JSON)
                .build();
    };


    static RequestSpecification requestSpecificationWithID(Integer id) {
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
                .build();

    }

    public static RequestSpecification requestSpecificationID(Integer id,String body) {
        return getBaseSpecBuilder()
                .addPathParams(Map.of(
                        "id",id
                ))
                .setContentType(ContentType.JSON)
                .setBody( body )
                .build();
    }

    static RequestSpecification requestSpecificationWithIDForSecondController(Integer id) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH_WITH_V2_VIDEOGAME_ID)
                .addHeaders(Map.of(
                        "Accept", "text/plain",
                        "Authorization",BEARER_TOKEN
                ))
                .addPathParams(Map.of(
                        "id", id
                ))
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
    public static RequestSpecification getVideoGameList() {
        return getBaseSpecBuilder().setBasePath(POST_PATH_WITH_VIDEOGAME).build();
    }

    public static RequestSpecification getVideoGameListV2() {
        return getBaseSpecBuilder().setBasePath(POST_PATH_WITH_V2_VIDEOGAME).build();
    }

    public static RequestSpecification getVideoGameListXML() {
        return getBaseSpecBuilderXML().setBasePath(POST_PATH_WITH_V2_VIDEOGAME).build();
    }

    public static RequestSpecification getVideoGame_WithId_json(int id){
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

    public static RequestSpecification getVideoGame_WithId_xml(int id) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(BASE_PATH_WITH_V2_VIDEOGAME_ID)
                .addHeaders(Map.of(
                        "Accept", "application/xml"
                ))
                .addPathParams(Map.of(
                        "id", id
                ))
                .build();
    }
}