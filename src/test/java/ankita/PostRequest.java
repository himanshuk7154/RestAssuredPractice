package ankita;
import fromRahulShetty.files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PostRequest
{
    @Test
    public void PostTC()
    {
        //setup base URI
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //post method to create new place
        String response = given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.addPlaceBody())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        JsonPath js = new JsonPath(response);
        String placeId =  js.getString("place_id");
        System.out.println(placeId);
    }

    @Test
    public void PostSwaggerTC()
    {
        given().log().all().contentType("application/json")
                .body(PostBody.addSwaggerBody())
                .when().post("https://petstore.swagger.io/v2/user")
                .then().statusCode(200)
                .log().all();

    }



}
