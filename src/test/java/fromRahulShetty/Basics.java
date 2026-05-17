package fromRahulShetty;

import fromRahulShetty.files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import utilities.ReusableFuncs;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Basics {

    static void main(String[] args) {

        //setup base URI
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //post method to create new place
        String response = given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.addPlaceBody())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        JsonPath js = new JsonPath(response);
        String placeId =  js.getString("place_id");
        System.out.println(placeId);

        //put method to modify address for a place id
        String updatedAddress = "8 Robinson Rd";

        given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\r\n" +
                        "\"place_id\":\""+placeId+"\",\r\n" +
                        "\"address\":\""+updatedAddress+"\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        //get method to get place using place id
        String getPlaceResponse = given().log().all()
                .queryParam("key", "qaclick123")
                .queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        System.out.println("get call response is \n"+getPlaceResponse);

        ReusableFuncs reusable = new ReusableFuncs();
        js = reusable.getJSONPathObjectFromStringResponse(getPlaceResponse);
        String actualAddress = js.getString("address");
        js = reusable.getJSONPathObjectFromStringResponse(Payload.addPlaceBody());
        String expectedAddress = js.getString("address");
        if(actualAddress.equals(updatedAddress)){
            System.out.println("Address correctly saved");
        }
        else {
            System.out.println(String.format("Actual address {} and expected address {} don't match",actualAddress,expectedAddress));
        }
    }
}
