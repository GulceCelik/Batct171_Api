package HomeWork;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Odev01 extends HerokuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/1
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status Code 200 olmalı
        And
            Content Type "application/json" olmalı
        And
            Status Line "HTTP/1.1 200 OK" olmalı
        And
            Body değerleri doğrulanmalı
    */

    @Test
    public void get01() {
        //set the URL
        spec.pathParams("first","booking","second",625);
        //Set the expectedData
        //Send the request and get the response
      Response response = given(spec).when().get("{first}/{second}");
      response.prettyPrint();
      //Do assertion
        assertEquals(200, response.statusCode());
        assertTrue(response.contentType().contains("application/json"));
        assertEquals("HTTP/1.1 200 OK",response.statusLine());
        Map<String,String> booking = new HashMap<>();
        booking.put("checkin", "2018-01-01");
        booking.put("checkout", "2019-01-01");
        Map<String,Object> body = new HashMap<>();
        body.put("firstname","Jane");
        body.put("lastname", "Doe");
        body.put("totalprice", 111);
        body.put("depositpaid", true);
        body.put("bookingdates",booking);
        body.put("additionalneeds", "Breakfast");
        Map<String,Object> actualData =response.as(HashMap.class);
      JsonPath jsonPath = response.jsonPath();
      assertEquals(booking.get("checkin"),jsonPath.getString("bookingdates.checkin"));
      assertEquals(booking.get("checkout"),jsonPath.getString("bookingdates.checkout"));
        assertEquals(body.get("fisrtname"),actualData.get("firstname"));
        assertEquals(body.get("lastname"),actualData.get("lastname"));
        assertEquals(body.get("totalprice"),actualData.get("totalprice"));
        assertEquals(body.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(body.get("additionalneeds"),actualData.get("additionalneeds"));









    }
}
