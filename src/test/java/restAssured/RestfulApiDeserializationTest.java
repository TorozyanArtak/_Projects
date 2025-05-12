package restAssured;

import dto.RestfulItemDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class RestfulApiDeserializationTest {


    private static final String BASE_URI = "https://api.restful-api.dev";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    void shouldDeserializeObjectsWithFlexibleDataField() {

        List<RestfulItemDto> objects = RestAssured
                .when()
                .get("/objects")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .jsonPath()
                .getList(".", RestfulItemDto.class);

        System.out.println(objects.toString());

        assertFalse(objects.isEmpty());

    }
}

