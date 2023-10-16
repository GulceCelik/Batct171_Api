package patch_request;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {
    /*
  Given
      1) https://jsonplaceholder.typicode.com/todos/198
      2) {
            "title": "Wash the dishes"
         }
  When
    I send PATCH Request to the Url
  Then
        Status code is 200
        And response body is like
            {
              "userId": 10,
              "title": "Wash the dishes",
              "completed": true,
              "id": 198
            }
   */
    @Test
    public void patch01() {
        //Set the URL
        spec.pathParams("first","todos","second","198");
        //Set the expectedData
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
      Map<String ,Object> payload = obj.getPayLoad(null,"wash the dishes",null);
      Map<String ,Object> expectedData = obj.getPayLoad(10,"wash the dishes",true);
     Response response = given(spec).body(payload).when().patch("{first}/{second}");
     response.prettyPrint();

     Map<String,Object> actualData = response.as(HashMap.class);
     assertEquals(200, response.statusCode());
     assertEquals(expectedData.get("userId"),actualData.get("userId"));
     assertEquals(expectedData.get("title"),actualData.get("title"));
     assertEquals(expectedData.get("completed"),actualData.get("completed"));


    }
}
