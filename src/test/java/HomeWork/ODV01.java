package HomeWork;

import baseUrl.RegresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class ODV01 extends RegresBaseUrl {
    /*
        Given
            https://reqres.in/api/users/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void odv01() {
        spec.pathParams("first","users","second",3);

        Response response = given(spec).when().get("{first}/{second}");
        assertEquals(200, response.statusCode());
        assertTrue(response.contentType().contains("application/json"));
        assertEquals("HTTP/1.1 200 OK",response.statusLine());

    }
}
