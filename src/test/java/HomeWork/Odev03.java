package HomeWork;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Odev03 extends JsonPlaceHolderBaseUrl {


    @Test
    public void odev03() {
        //Set the URL
        spec.pathParams("first", "todos", "second", 23);

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertTrue(response.contentType().contains("application/json"));
        JsonPlaceHolderTestData jsondata = new JsonPlaceHolderTestData();
      Map<String,Object> payLoad =  jsondata.getPayLoad(2,"et itaque necessitatibus maxime molestiae qui quas velit",false);
        Map<String,Object> actualData  = response.as(HashMap.class);
        assertEquals(payLoad.get("userId"),actualData.get("userId"));
        assertEquals(payLoad.get("completed"),actualData.get("completed"));
        assertEquals(payLoad.get("title"),actualData.get("title"));





    }
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status Code 200 olmalı
        And
            Content Type “application/json” olmalı
        And
            “title” şu metni içermeli: “et itaque necessitatibus maxime molestiae qui quas velit”,
        And
            “completed” değeri false olmalı
        And
            “userId” değeri 2 olmalı
     */
}
