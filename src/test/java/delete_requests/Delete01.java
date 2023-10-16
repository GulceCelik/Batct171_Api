package delete_requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
            I send DELETE Request to the Url
        Then
            Status code is 200
        And Response body is { }
    */

    @Test
    public void delete01() {
        //set the Url
        spec.pathParams("first", "todos", "second", "198");
        //Set the exxpected Data
        Map<String, String> expectedData = new HashMap<String, String>();
        //send the request and get the response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();
        //Do assertion
        Map<String, String> actualData = response.as(HashMap.class);
        assertEquals(200, response.statusCode());
        //1.yol
        assertEquals(expectedData, actualData);
        //2.yol
        assertTrue(actualData.isEmpty());
        //3.yol
        assertEquals(0, actualData.size());


    }
}
