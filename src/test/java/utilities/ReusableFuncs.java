package utilities;

import io.restassured.path.json.JsonPath;

public class ReusableFuncs {

    public JsonPath getJSONPathObjectFromStringResponse(String response){
        return new JsonPath(response);
    }
}
