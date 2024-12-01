package com.automation.student2;

import com.automation.config.ApiConfig;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BooksTest {
    private final String BASE_URL = ApiConfig.BASE_URL;

    @Test
    public void testGetAllBooks() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(BASE_URL + "/Books")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testCreateBook() {
        String requestBody = """
            {
              "id": 0,
              "title": "Test Book",
              "description": "Test Description",
              "pageCount": 100,
              "excerpt": "Test Excerpt",
              "publishDate": "2024-01-10T12:00:00.000Z"
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "/Books")
                .then()
                .statusCode(200)
                .body("title", equalTo("Test Book"))
                .body("pageCount", equalTo(100));
    }

    @Test
    public void testUpdateBook() {
        String requestBody = """
            {
              "id": 1,
              "title": "Updated Book",
              "description": "Updated Description",
              "pageCount": 200,
              "excerpt": "Updated Excerpt",
              "publishDate": "2024-01-10T12:00:00.000Z"
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(BASE_URL + "/Books/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Book"))
                .body("pageCount", equalTo(200));
    }

    @Test
    public void testDeleteBook() {
        when()
                .delete(BASE_URL + "/Books/1")
                .then()
                .statusCode(200);
    }
}