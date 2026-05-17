package fromRahulShetty;

import io.restassured.path.json.JsonPath;
import mockedJsonResponses.MockedCourseAPIResponses;
import org.testng.Assert;
import utilities.ReusableFuncs;

public class ParseComplexJson {
    static void main(String [] args){

        ReusableFuncs reusable = new ReusableFuncs();
        JsonPath js = reusable.getJSONPathObjectFromStringResponse(MockedCourseAPIResponses.getMockedCourseListWithPrice());

        //print number of courses returned
        int numberOfCourses = js.getInt("courses.size()");
        System.out.println("number of courses is - "+numberOfCourses);

        //print purchase amount by fetching from dashboard
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("purchase amount on dashboard is - "+purchaseAmount);

        //print title of first course
        String courseTitle = js.getString("courses[0].title");
        System.out.println("Title of the first course is - "+courseTitle);

        //retrieve every course and print title as well as price of each course
        int coursePrice;
        int numOfCopies;
        int totalSaleAmount =0;
        for(int i = 0; i<numberOfCourses; i++){
            courseTitle = js.getString("courses["+i+"].title");
            coursePrice = js.getInt("courses["+i+"].price");
            numOfCopies = js.getInt("courses["+i+"].copies");
            System.out.println("Details of course "+(i+1)+"\n title "+ courseTitle+ "\n price "+coursePrice+
                    "\n copies sold "+numOfCopies+"\n");
            totalSaleAmount = totalSaleAmount+(coursePrice*numOfCopies);
        }
        Assert.assertEquals(totalSaleAmount,purchaseAmount);
    }
}
