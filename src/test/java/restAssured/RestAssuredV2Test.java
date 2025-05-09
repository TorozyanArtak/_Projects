package restAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestAssuredV2Test {
    private RequestSpecification requestSpec;
    private static int userId;
    private static final Logger logger = LoggerFactory.getLogger(RestAssuredV2Test.class);

    private static final String BASE_URI = "https://gorest.co.in";;
    private static final String BASE_PATH = "/public/v2";
    private static final String TOKEN = "Bearer 2b9bc29b3db818fc69f663f8c2af0720024db38d786387df66022f96b683fa77";

    private List<Object> getAllUsers() {
        return givenWithSpec()
                .get("/users")
                .jsonPath()
                .getList("$");
    }

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
    @Order(1)
    public void shouldReturnDefaultUsersWithValidGenders() {
        givenWithSpec()
                .when()
                .get("/users")
                .then()
                .log().body()
                .statusCode(200)
                .body("size()", is(10))
                .body("gender", everyItem(is(oneOf("male", "female"))));
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
                .body("size()", is(count));
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
        userId = response.jsonPath().getInt("[" + randomIndex + "].id");
        logger.info("Deleting user with ID: {}", userId);
        givenWithSpec()
                .header("Authorization", TOKEN)
                .when()
                .delete("/users/" + userId)
                .then()
                .log().body()
                .log().status()
                .statusCode(204);
    }

    @Test
    @Order(4)
    public void shouldReturnNotFoundForDeletedUser() {
        givenWithSpec()
                .when()
                .get("/users/" + userId)
                .then()
                .log().body()
                .log().status()
                .statusCode(404);
    }


}