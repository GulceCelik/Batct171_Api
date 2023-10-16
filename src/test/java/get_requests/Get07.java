package get_requests;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get07 extends HerokuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/23
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
      {
            "firstname": "Bradley",
            "lastname": "Pearson",
            "totalprice": 132,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2022-10-27",
                "checkout": "2022-11-07"
            },
            "additionalneeds": "None"
        }
     */

    @Test
    public void get07() {
        //Set the URL
        spec.pathParams("first","booking","second",23).when();
        //Set the expected Data
        //Send the request and get the response
       Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
//        response.
//                then().
//                statusCode(200).
//                contentType(ContentType.JSON).
//                body("firstname",equalTo("Jane")).
//                body("lastname",equalTo("Doe")).
//                body("totalprice",equalTo(111)).
//                body("depositpaid",equalTo(true)).
//                body("bookingdates.checkin",equalTo("2018-01-01")).
//                body("bookingdates.checkout",equalTo("2019-01-01")).
//                body("additionalneeds",equalTo("Extra pillows please"));

        //JsonPath Alistirmalari
        // NOT: JsonPath ile Json datadaki değerleri bir değişken içerisine koyabiliriz.
        JsonPath json = response.jsonPath();
     String firstName =   json.getString("firstname");
     String chechIn =   json.getString("bookingdates.checkin");
     int totalPrice =   json.getInt("totalprice");

        System.out.println("firstName = " + firstName);
        System.out.println("chechIn = " + chechIn);
        //2.Yol
        assertEquals(200,response.statusCode());
       assertTrue(response.contentType().contains("application/json"));
        assertEquals("John",json.getString("firstname"));
        assertEquals("Smith",json.getString("lastname"));
        assertEquals(111,json.getInt("totalprice"));
        assertTrue(json.getBoolean("depositpaid"));
        assertEquals("2018-01-01",json.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",json.get("bookingdates.checkout"));
        assertEquals("Breakfast",json.get("additionalneeds"));


    }
}
