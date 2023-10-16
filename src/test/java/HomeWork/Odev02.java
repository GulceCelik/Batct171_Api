package HomeWork;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Odev02 extends HerokuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/0
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status code 404 olmalı
        And
            Status Line "HTTP/1.1 404 Not Found" olmalı
        And
            Response body "Not Found" içermeli
        And
            Response body "TechProEd" içermemeli
        And
            Server değeri "Cowboy" olmalı
    */

        @Test
        public void Odev02() {
            //set the Url
            spec.pathParams("first", "booking", "second", 0);
            //set the expected data


           // Send the request and the get response

           Response response =  given(spec).when().get("{first}/{second}");
           response.prettyPrint();

           //Do Assertion
            assertEquals(404,response.statusCode());
            assertEquals("HTTP/1.1 404 Not Found",response.statusLine());
            assertEquals("HTTP/1.1 404 Not Found",response.statusLine());
            response.then().body(containsString("Not Found"));
            response.then().body(not(containsString("TechProEd")));
            response.then().header("Server","Cowboy");





        }

}
