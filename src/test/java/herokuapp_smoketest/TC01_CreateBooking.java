package herokuapp_smoketest;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.herokuapp.BookingDatesPojo;
import pojos.herokuapp.BookingPojo;
import pojos.herokuapp.BookingResponsePojo;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class TC01_CreateBooking extends HerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
                {
            "firstname" : "Jim",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }
     When
        Kullanici POST request gonderir
     Then
        StatusCode : 200
     And
        Body should be like this

        {
          "bookingid": 1,
          "booking": {
              "firstname": "Jim",
              "lastname": "Brown",
              "totalprice": 111,
              "depositpaid": true,
              "bookingdates": {
                  "checkin": "2018-01-01",
                  "checkout": "2019-01-01"
              },
              "additionalneeds": "Breakfast"
          }
        }

     */
    public static int bookingId;
    @Test
    public void createBooking() {
        spec.pathParam("first", "booking");

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo payLoad = new BookingPojo("Jim", "Brown", 111, true, bookingDates, "Breakfast");
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();
      bookingId =response.jsonPath().getInt("bookingid");

        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);
        assertEquals(200, response.statusCode());
        assertEquals(payLoad.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(payLoad.getLastname(), actualData.getBooking().getLastname());
        assertEquals(payLoad.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(payLoad.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());


    }
}
