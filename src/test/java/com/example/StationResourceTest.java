package com.example;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
class StationResourceTest {

    @Test
    @TestSecurity(user = "train-line-service", roles = "station-reader")
    void testStationsEndpoint() {
        given()
                .when().get("/stations")
                .then()
                .statusCode(200)
                .body("", hasSize(3))
                .body("[0].name", is("Central Station"))
                .body("[0].location", is("Downtown"))
                .body("[1].name", is("East Station"))
                .body("[1].location", is("Eastside"))
                .body("[2].name", is("West Station"))
                .body("[2].location", is("Westside"));
    }

}