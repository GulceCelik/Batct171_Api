package HomeWork;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.*;


public class Odev04 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
	 	    Kullanıcı URL'e bir GET request gönderir
	    And
	        Accept type “application/json” olmalı
	    Then
	        HTTP Status Code 200 olmalı
	    And
	        Response format "application/json" olmalı
	    And
	        Listede 200 tane eleman olmalı
	    And
	        title içeriklerinden en az birinin değeri "quis eius est sint explicabo" olmalı
	    And
	        userIds değerleri içerisinde 2, 7, ve 9 bulunmalı
     */

    @Test
    public void odev04() {

        spec.pathParam("first", "todos");
        Response response = given(spec).when().get("{first}");
       // response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertTrue(response.contentType().contains("application/json"));
        JsonPath jsonPath = response.jsonPath();
        List<Object> list = jsonPath.getList("findAll");
        System.out.println(list.size());
        assertEquals(200, list.size());
        List<String> listTitle = jsonPath.getList("findAll{it.title=='quis eius est sint explicabo'}.title");
        assertTrue(listTitle.contains("quis eius est sint explicabo"));
//        List<String> listid = jsonPath.getList("findAll{it.id}.id");
//        System.out.println(listid);
        response.then().body("id",hasItems(2,7,9));



    }
}
