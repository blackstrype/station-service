package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class StatusResourceTest {

    @Test
    void getStatus() {
        given()
                .when().get("/status")
                .then()
                .statusCode(200);
    }
}