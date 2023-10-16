package get_requests;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends HerokuAppBaseUrl {
  /*
    Given
        https://restful-booker.herokuapp.com/booking/91
    When
        I send GET Request to the url
    Then
        Response body should be like that;
            {
                "firstname": "Jane",
                "lastname": "Doe",
                "totalprice": 111,
                "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2018-01-01",
                     "checkout": "2019-01-01"
                    },
                "additionalneeds": "Extra pillows please"
            }
     */

    @Test
    public void get10() {
        //Set the URL
        spec.pathParams("first", "booking", "second", "2726");

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Jane");
        expectedData.put("lastname", "Doe");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingDates);
        expectedData.put("additionalneeds", "Extra pillows please");
        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
        assertEquals(expectedData.get("checkin"), ((Map) actualData.get("bookingDates")).get("checkin"));
        assertEquals(expectedData.get("checkout"), ((Map) actualData.get("bookingDates")).get("checkout"));
        JsonPath json = response.jsonPath();
        assertEquals(expectedData.get("checkin"), json.getString("bookingDates.checkin"));
        assertEquals(expectedData.get("checkout"), json.getString("bookingDates.checkout"));


    }

    @Test
    public void get10methodlu() {
        //Set the URL
        spec.pathParams("first", "booking", "second", "1289");
        HerokuAppTestData obj = new HerokuAppTestData();
        Map<String, String> bookingDates = obj.getBookingDates("2018-01-01", "2019-01-01");
        Map<String, Object> expectedData = obj.getPayLoad("Jane", "Doe", 111, true, bookingDates, "Extra pillows please");


        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
//        assertEquals(expectedData.get("checkin"), ((Map) actualData.get("bookingDates")).get("checkin"));
//        assertEquals(expectedData.get("checkout"), ((Map) actualData.get("bookingDates")).get("checkout"));
        JsonPath json = response.jsonPath();
        assertEquals(expectedData.get("checkin"), json.getString("bookingDates.checkin"));
        assertEquals(expectedData.get("checkout"), json.getString("bookingDates.checkout"));


    }
}
