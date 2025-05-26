package restAssured;

import dto.RestfulItemDto;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class RestfulApiDeserializationTest {


    private static final String BASE_URI = "https://api.restful-api.dev";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    public void shouldDeserializeObjectsWithFlexibleDataField() {
        File schemaFile = new File("C:\\Users\\User\\Desktop\\selenium_Lesson\\src\\test\\resources\\schema.json");
        List<RestfulItemDto> objects = RestAssured
                .when()
                .get("/objects")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(schemaFile))
                .extract()
                .jsonPath()
                .getList(".", RestfulItemDto.class);

        assertFalse(objects==null || objects.isEmpty(), "The list of deserialized items is null or empty!");

    }
}

