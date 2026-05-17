package day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class HTTPRequests {

    @Test
    void getUsers() {

        given()
                .log().all()

                .when()
                .get("https://jsonplaceholder.typicode.com/users")

                .then()
                .statusCode(200)
                .log().all();
    }
}
