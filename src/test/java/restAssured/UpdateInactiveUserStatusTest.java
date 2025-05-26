package restAssured;

import dto.UserDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import restAssured.helper.Data;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateInactiveUserStatusTest {
    private RequestSpecification requestSpec;

    private static final String BASE_URI = "https://gorest.co.in";
    private static final String BASE_PATH = "/public/v2";

    private RequestSpecification givenWithSpec() {
        return RestAssured
                .given()
                .spec(requestSpec);
    }

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URI;
        requestSpec = new RequestSpecBuilder()
                .setBasePath(BASE_PATH)
                .build();
    }

    @Test
    @Tag("API")
    void shouldUpdateInactiveUserToActive() {

        List<UserDto> allUsers = givenWithSpec()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .jsonPath()
                .getList("", UserDto.class);

        UserDto inactiveUser = allUsers.stream()
                .filter(u -> u.status().equalsIgnoreCase("inactive"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No inactive users found"));

        int userId = inactiveUser.id();
        System.out.println(userId);

        UserDto updatedUser = new UserDto(
                inactiveUser.id(),
                inactiveUser.name(),
                inactiveUser.email(),
                inactiveUser.gender(),
                "active"
        );

        UserDto updatedResponse = givenWithSpec()
                .header("Authorization", Data.TOKEN)
                .contentType(ContentType.JSON)
                .body(updatedUser)
                .when()
                .put("/users/" + userId)
                .then()
                .statusCode(200)
                .extract()
                .as(UserDto.class);

        UserDto fetchedUser = givenWithSpec()
                .header("Authorization", Data.TOKEN)
                .when()
                .get("/users/" + userId)
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .as(UserDto.class);

        assertEquals("active", fetchedUser.status(), "User status should be updated to active");

    }
}