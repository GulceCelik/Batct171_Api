package put_requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Put01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            1) https://jsonplaceholder.typicode.com/todos/198
            2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
            Kullanıcı URL'e bir PUT request gönderir
        Then
           Status code 200 olmalı
           Response şu şekilde olmalı:
           {
                "userId": 21,
                "title": "Wash the dishes",
                "completed": false
                "id": 198
           }
     */

    @Test
    public void put01() {
        //Set the URL
        spec.pathParams("first","todos","second","198");
        //Set the expected Data
        Map<String,Object> payload = new HashMap<>();
        payload.put("userId",21);
        payload.put("title","Wash the dishes");
        payload.put("completed",false);
        //Send the request and get the response
       Response response = given(spec).body(payload).when().put("{first}/{second}");
        response.prettyPrint();
       Map<String,Object> actualData= response.as(HashMap.class);
       assertEquals(200,response.statusCode());
       assertEquals(payload.get("userId"),actualData.get("userId"));
       assertEquals(payload.get("title"),actualData.get("title"));
       assertEquals(payload.get("completed"),actualData.get("completed"));

    }
    @Test
    public void put01Methodlu() {
        //Set the URL
        spec.pathParams("first","todos","second","198");
        //Set the expected Data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> payLoad = obj.getPayLoad(21, "Wash the dishes", false);

        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().put("{first}/{second}");
        response.prettyPrint();
        payLoad.put("id",198);
        Map<String,Object> actualData= response.as(HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(payLoad.get("userId"),actualData.get("userId"));
        assertEquals(payLoad.get("title"),actualData.get("title"));
        assertEquals(payLoad.get("completed"),actualData.get("completed"));
        assertEquals(payLoad.get("id"),actualData.get("id"));

    }
}
