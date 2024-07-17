package org.example;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

/** Tests for {@link UserProfileResource}. */
@QuarkusTest
final class UserProfileResourceTest {

  @Test
  void get_me() {
    given()
        .auth()
        .preemptive()
        .basic("user", "user")
        .when()
        .get("/api/users/me")
        .then()
        .statusCode(200)
        .body("username", equalTo("user"))
        .body("name", equalTo("Random User"));
  }
}
