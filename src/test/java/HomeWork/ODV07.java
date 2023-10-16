package HomeWork;

import baseUrl.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.petstore.PetStorePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ODV07 extends PetStoreBaseUrl {
    /*
        Given
        https://petstore.swagger.io/v2/user
        When
            User sends a POST Request to the url
                    {
  "id": 0,
  "username": "john123",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john123@hotmail.com",
  "password": "pwd",
  "phone": "12345",
  "userStatus": 14
}
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
        And
            response body is like
		{
    			"code": 200,
    			"type": "unknown",
    			"message": "55555"
		}

     */

    @Test
    public void odv07() {
        spec.pathParam("first", "user");
        //Set the expected Data
        PetStorePojo payLoad = new PetStorePojo("john123","John","Doe","john123@hotmail.com","pwd","12345",14);
        //Send the request and get the response
       Response response = given(spec).when().body(payLoad).post("{first}");
       response.prettyPrint();
       assertEquals(200, response.statusCode());
       assertTrue(response.contentType().contains("application/json"));
     JsonPath json =  response.jsonPath();
       assertEquals("200",json.getString("code"));
       assertEquals("unknown",json.getString("type"));
       assertEquals("9223372036854775807",json.getString("message"));

    }
}
