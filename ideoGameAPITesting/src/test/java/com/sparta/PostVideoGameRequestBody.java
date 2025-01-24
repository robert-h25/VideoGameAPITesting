package com.sparta;

public record PostVideoGameRequestBody(int id, String category, String name, String rating, String releaseDate, int reviewScore) {
}
