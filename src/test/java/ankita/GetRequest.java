package ankita;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetRequest 
{
    @Test
    public void TestCase1()
    {
        given().contentType("application/json")
                .when().get("https://catfact.ninja/fact")
                .then().log().all().statusCode(200);

    }
    
}
