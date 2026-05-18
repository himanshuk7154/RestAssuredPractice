package fromRahulShetty;

import fromRahulShetty.files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ReusableFuncs;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class LibraryAPITests {

    @Test(dataProvider = "AddBookDataCreator")
    public void addBook(String isbn, int aisleNumber){

        ReusableFuncs reusable = new ReusableFuncs();

        RestAssured.baseURI = "http://216.10.245.166";

        String addBookPostResponse =  given().log().all().header("content-Type","application/json")
                .body(Payload.addBook(aisleNumber,isbn))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = reusable.getJSONPathObjectFromStringResponse(addBookPostResponse);
        System.out.println("ID of the book added is - "+js.getString("ID"));
        Assert.assertEquals(js.getString("ID"), (isbn + aisleNumber));

    }

    @DataProvider(name = "AddBookDataCreator")
    public Object[][] addBookData(){
        ReusableFuncs reusable = new ReusableFuncs();

        Object [][] aisleNbr = new Object[10][2];
        for(int i = 0; i<aisleNbr.length; i++){
            aisleNbr[i] = new Object[] {reusable.randomFourCharString(),reusable.randomFourDigitNumber()};
        }
        return aisleNbr;
    }
}
