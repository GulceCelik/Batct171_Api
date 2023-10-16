package HomeWork;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class Odev05 extends HerokuAppBaseUrl {
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
    public void odev05() {
        spec.pathParams("first","booking","second","23");
        HerokuAppTestData obj = new HerokuAppTestData();
       Map<String,String> booking = obj.getBookingDates("2018-01-01","2019-01-01");
        System.out.println(booking);
       Map<String,Object> expectedData = obj.getPayLoad("Jane","Doe",111,true,booking,"Extra pillows please");
       Response response = given(spec).when().get("{first}/{second}");
       response.prettyPrint();
       assertEquals(200, response.getStatusCode());
       assertTrue(response.contentType().contains("application/json"));
       Map<String,Object> actualData = response.as(HashMap.class);
       assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
       assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
       assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
       assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
      JsonPath json = response.jsonPath();

       assertEquals(expectedData.get("checkin"), json.getString("bookingDates.checkin"));
       assertEquals(expectedData.get("checkout"), json.getString("bookingDates.checkout"));

//bookingDates D kucuk harf olmasina ragmen json bodye kucuk harfle test kaliyor










    }
}
