package PostRequests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Post02 extends JsonPlaceHolderBaseUrl {
      /*
    Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }
        When
            Kullanıcı URL'e bir POST request gönderir
        Then
            Status code 201 olmalı
        And
            Response şu şekilde olmalı:
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
            }
     */

    @Test
    public void post02() {
        //Set the Url
        spec.pathParam("first","todos");
        //Set the expected Data
        // PayLoad: Post işlemlerinde Body ile gönderdiğimiz datadır.
        // Expected Data: Response'tan gelmesini beklediğimiz datadır.
        String payload ="{ \n" +
                "             \"userId\": 55, \n" +
                "             \"title\": \"Tidy your room\", \n" +
                "             \"completed\": false \n" +
                "           } ";
        //Set the request and get the response
    Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();
        //Do Assertion
        JsonPath jsonPath = response.jsonPath();
        assertEquals(201, response.statusCode());
        assertEquals(55,jsonPath.getInt("userId"));
        assertEquals("Tidy your room",jsonPath.getString("title"));
        assertFalse(jsonPath.getBoolean("completed"));

    }
    @Test
    public void post02Map() {
        // Set the URL
        spec.pathParam("first", "todos");
        // Set the expected data (payLoad)
        Map<String, Object> payLoad = new HashMap<>();
        payLoad.put("userId", 55);
        payLoad.put("title", "Tidy your room");
        payLoad.put("completed", false);
        System.out.println(payLoad);
        // Send the request and get the response
        // Serialization: Java datalarının Json datalarına dönüştürülmesi. Bu işlemi Jackson Databind gibi dependencyler bizin için yapar.
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();
        // Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        // De-Serialization: Json datalarının Java datalarına dönüştürülmesi. Bu işlemi Jackson Databind gibi dependencyler bizin için yapar.
        assertEquals(201, response.statusCode());
        assertEquals(payLoad.get("userId"), actualData.get("userId"));
        assertEquals(payLoad.get("title"), actualData.get("title"));
        assertEquals(payLoad.get("completed"), actualData.get("completed"));
    }
}
