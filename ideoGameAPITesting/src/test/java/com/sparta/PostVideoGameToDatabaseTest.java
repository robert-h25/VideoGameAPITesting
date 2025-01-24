package com.sparta;

import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostVideoGameToDatabaseTest {

    @Test
    @DisplayName("Post Video Game to Video Game Database Version 1")
    public void postVideoGameToDatabase_v1() {
        Response response = Utils.createVideoGame_AndPostToDatabase_v1(0, "Platform", "Luigi's Mansion", "Mature", "2001-11-18", 86);
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
    }

    @Test
    @DisplayName("Post Video Game to Video Game Database Version 2")
    public void postVideoGameToDatabase_v2() {
        Response response = Utils.createVideoGame_AndPostToDatabase_v2(0, "Platform", "Luigi's Mansion", "Mature", "2001-11-18", 86);
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
    }

    @Test
    @DisplayName("Post video game that exists in the video database Version 2")
    public void postVideoGameThatExistsInDatabase_V1(){
        Response response = Utils.createVideoGame_AndPostToDatabase_v1(1, "Shooter", "Resident Evil 4", "Universal", "2005-10-01 23:59:59",
                85
        );
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(400));
    }


    @Test
    @DisplayName("Post video game that exists in the video database Version 2")
    public void postVideoGameThatExistsInDatabase_V2(){
        Response response = Utils.createVideoGame_AndPostToDatabase_v2(2, "Driving", "Gran Turismo 3", "Universal", "2001-03-10 23:59:59",
                85
        );
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(400));
    }

}
