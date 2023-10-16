package get_requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get08 extends JsonPlaceHolderBaseUrl {
       /*
        Given
           https://jsonplaceholder.typicode.com/todos
        When
           Kullanıcı URL'e bir GET request gönderir
        Then
           1) Status code 200 olmalı
           2) "Id"leri 190 dan büyük olanları konsola yazdırın
              "Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
           3) "Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
              "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
           4) "Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
              "delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
     */

    @Test
    public void get08() {
        //Set The URL
        spec.pathParam("fisrt","todos");
        //Set the expected Data
        //Send the request and get the response
       Response response = given(spec).when().get("{fisrt}");
     //  response.prettyPrint();
       //Do Assertion
     assertEquals(200, response.getStatusCode());
        JsonPath json = response.jsonPath();
        //2) "Id"leri 190 dan büyük olanları konsola yazdırın
        //"Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
      List<Object> idList = json.getList("findAll{it.id>190}");
        System.out.println(idList);
        assertEquals(10,idList.size());
        //3) "Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
        //"Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
       List<Integer> idListUserId=json.getList("findAll{it.id<5}.userId");
        assertEquals(4,idListUserId.size());
        System.out.println(idList.size());
        // 4) "Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
        //"delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
     List<String>  idListTitle =json.getList("findAll{it.id<5}.title");
        System.out.println(idListTitle);
        assertTrue(idListTitle.contains("delectus aut autem"));
        //Ornek uygulama unoqie bir degeri kullarak id degerine ulasma
       List<Integer> a = json.getList("findAll{it.title=='delectus aut autem'}.id");
        System.out.println("ID : " +a.get(0));
//        List<Integer>idDelectus=json.getList("findAll{it.title=='delectus aut autem'}.id");
//        System.out.println(idDelectus.get(0));




    }
}
