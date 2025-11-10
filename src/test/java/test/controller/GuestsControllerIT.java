package test.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class GuestsControllerIT {

    @Test
    void shouldPersistGuestsAndFilterByStatus() {
        JsonPath confirmedGuest = given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "name": "Alice",
                          "age": 9,
                          "role": "CHILD"
                        }
                        """)
                .when()
                .post("/guests")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("status", equalTo("CONFIRMED"))
                .extract()
                .jsonPath();

        JsonPath notGoingGuest = given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "name": "Bob",
                          "age": 32,
                          "role": "ADULT",
                          "status": "NOT_GOING"
                        }
                        """)
                .when()
                .post("/guests")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("status", equalTo("NOT_GOING"))
                .extract()
                .jsonPath();

        int confirmedId = confirmedGuest.getInt("id");
        int notGoingId = notGoingGuest.getInt("id");

        given()
                .when()
                .get("/guests")
                .then()
                .statusCode(200)
                .body("size()", is(2))
                .body("id", containsInAnyOrder(confirmedId, notGoingId))
                .body("name", containsInAnyOrder("Alice", "Bob"));

        given()
                .queryParam("status", "CONFIRMED")
                .when()
                .get("/guests")
                .then()
                .statusCode(200)
                .body("size()", is(1))
                .body("[0].id", is(confirmedId))
                .body("[0].name", equalTo("Alice"))
                .body("[0].status", equalTo("CONFIRMED"));

        given()
                .when()
                .get("/guests/confirmed")
                .then()
                .statusCode(200)
                .body("size()", is(1))
                .body("[0].id", is(confirmedId))
                .body("[0].name", equalTo("Alice"))
                .body("[0].status", equalTo("CONFIRMED"));

        given()
                .queryParam("status", "NOT_GOING")
                .when()
                .get("/guests")
                .then()
                .statusCode(200)
                .body("size()", is(1))
                .body("[0].id", is(notGoingId))
                .body("[0].name", equalTo("Bob"))
                .body("[0].status", equalTo("NOT_GOING"));

        given()
                .when()
                .get("/guests")
                .then()
                .statusCode(200)
                .body("createdAt", everyItem(notNullValue()));
    }
}
