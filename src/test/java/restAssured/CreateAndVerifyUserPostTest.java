package restAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import dto.PostDto;
import restAssured.helper.Data;


import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateAndVerifyUserPostTest {
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
    public void shouldCreatePostForRandomUserAndVerifyItExists() {

        List<PostDto> allPosts = givenWithSpec()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("", PostDto.class);

        int index = new Random().nextInt(allPosts.size());
        int randomUser_Id = allPosts.get(index).user_id();
        String title = "Test Post Title ";
        String body = "Test post body content";
        PostDto postToCreate = new PostDto(null, randomUser_Id, title, body);

        PostDto createdPost = givenWithSpec()
                .header("Authorization", Data.TOKEN)
                .contentType(ContentType.JSON)
                .body(postToCreate)
                .when()
                .post("/users/" + randomUser_Id + "/posts")
                .then()
                .statusCode(201)
                .log().body()
                .extract().as(PostDto.class);

        List<PostDto> userPosts = givenWithSpec()
                .header("Authorization", Data.TOKEN)
                .when()
                .get("/users/" + randomUser_Id + "/posts")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("", PostDto.class);

        assertTrue(
                userPosts.stream().anyMatch(p -> p.id().equals(createdPost.id())),
                "Newly created post should exist in the user's posts");
    }

}
