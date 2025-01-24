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


}