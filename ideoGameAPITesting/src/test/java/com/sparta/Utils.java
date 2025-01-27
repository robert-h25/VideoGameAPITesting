package com.sparta;

import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import java.util.Map;

public class Utils {

    private static final String BASE_URL = "https://videogamedb.uk:443/api";
    private static final String BASE_PATH_TO_VIDEOGAME = "/videogame";
    private static final String POST_COMMENT_VIDEOGAME_ID = "/videogame/{id}";
    private static final String BASE_PATH_WITH_V2_VIDEOGAME = "/v2/videogame";
    private static final String BASE_PATH_WITH_V2_VIDEOGAME_ID = "/v2/videogame/{id}";

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

    public static RequestSpecification requestSpecification() {
        return getBaseSpecBuilder()
                .setContentType(ContentType.JSON)
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

    public static RequestSpecification getVideoGameList() {
        return getBaseSpecBuilder().setBasePath(BASE_PATH_TO_VIDEOGAME).build();
    }

    public static RequestSpecification getVideoGameListV2() {
        return getBaseSpecBuilder().setBasePath(BASE_PATH_WITH_V2_VIDEOGAME).build();
    }

    public static RequestSpecification getVideoGameListXML() {
        return getBaseSpecBuilderXML().setBasePath(BASE_PATH_WITH_V2_VIDEOGAME).build();
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