package com.automation.student1;

import com.automation.config.ApiConfig;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ActivitiesTest {
    private final String BASE_URL = ApiConfig.BASE_URL;

    @Test
    public void testGetAllActivities() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(BASE_URL + "/Activities")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testCreateActivity() {
        String requestBody = """
            {
              "id": 0,
              "title": "Test Activity",
              "dueDate": "2024-01-10T12:00:00",
              "completed": true
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "/Activities")
                .then()
                .statusCode(200)
                .body("title", equalTo("Test Activity"));
    }

    @Test
    public void testUpdateActivity() {
        String requestBody = """
            {
              "id": 1,
              "title": "Updated Activity",
              "dueDate": "2024-01-10T12:00:00",
              "completed": false
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(BASE_URL + "/Activities/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Activity"));
    }

    @Test
    public void testDeleteActivity() {
        when()
                .delete(BASE_URL + "/Activities/1")
                .then()
                .statusCode(200);
    }
}