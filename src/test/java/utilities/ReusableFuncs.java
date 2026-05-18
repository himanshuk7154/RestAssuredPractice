package utilities;

import io.restassured.path.json.JsonPath;

import java.util.Random;

public class ReusableFuncs {

    public JsonPath getJSONPathObjectFromStringResponse(String response){
        return new JsonPath(response);
    }

    public String randomFourCharString(){

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(4);

        for (int i = 0; i < 4; i++) {
            // Pick a random index from the characters string
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    public int randomFourDigitNumber(){

        return (int)(Math.random() * 900) + 100;
    }
}
