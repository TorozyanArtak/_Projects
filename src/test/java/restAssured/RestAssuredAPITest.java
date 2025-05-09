package restAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestAssuredAPITest {

    private RequestSpecification requestSpec;
    private static int userId;

    private static final String BASE_URI = "https://gorest.co.in";
    private static final String BASE_PATH = "/public-api";
    private static final String TOKEN = "Bearer 2b9bc29b3db818fc69f663f8c2af0720024db38d786387df66022f96b683fa77";

    private List<Object> getAllUsers() {
        return RestAssured
                .given()
                .spec(requestSpec)
                .get("/users")
                .jsonPath()
                .getList("data");
    }

    private RequestSpecification givenWithSpec() {
        return RestAssured
                .given()
                .spec(requestSpec);
    }

    @BeforeEach
    public void setup() {
        RestAssured.baseURI =BASE_URI;
        requestSpec = new RequestSpecBuilder()
                .setBasePath(BASE_PATH)
                .addHeader("Authorization", TOKEN)
                .build();
    }

    @Test
    @Order(1)
    public void shouldReturnDefaultUsersWithValidGenders() {
        givenWithSpec()
                .when()
                .get("/users")
                .then()
                .log().body()
                .statusCode(200)
                .body("data.size()", is(10))
                .body("data.gender", everyItem(is(oneOf("male", "female"))));
    }

    @Test
    @Order(2)
    public void shouldReturnUsersWithCustomPerPageCount() {
        int randomValue = ThreadLocalRandom.current().nextInt(1, 201);
        int count = randomValue > 100 ? 10 : randomValue;
        givenWithSpec()
                .queryParam("per_page", count)
                .when()
                .get("/users")
                .then()
                .log().body()
                .statusCode(200)
                .body("data.size()", is(count));
    }

    @Test
    @Order(3)
    public void shouldDeleteRandomUserSuccessfully() {
        List<Object> users = getAllUsers();
        int userCount = users.size();
        int randomIndex = ThreadLocalRandom.current().nextInt(userCount);
        Response response = givenWithSpec()
                .when()
                .get("/users");
        userId = response.jsonPath().getInt("data[" + randomIndex + "].id");
        givenWithSpec()
                .when()
                .delete("/users/" + userId)
                .then()
                .log().body()
                .log().status()
                .statusCode(200);

    }

    @Test
    @Order(4)
    public void shouldReturnNotFoundForDeletedUser(){
        givenWithSpec()
                .when()
                .get("/users/" + userId)
                .then()
                .log().body()
                .log().status()
                .statusCode(200);
    }
}

