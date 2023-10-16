package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;

public class RequestResponse {
    public static void main(String[] args) {
        String url = "https://petstore.swagger.io/v2/pet/9898";
       Response response = given().when().get(url);
       response.prettyPrint();
        //Status Code nasil yazidrilir
        System.out.println("Status Code = " + response.statusCode());
        //Content Type nasil yazidrilir
        System.out.println("Content Type = " + response.contentType());
        //Status line nasil yazidrilir
        System.out.println("Status Line = " + response.statusLine());
        //Header Bolumunden bir baslik nasil yazdirilir
        System.out.println("Header = " + response.header("Server"));
        //Header Bolumunden tum basliklar nasil yazdirilir
        System.out.println("Headers = " + response.headers());
        //Time bilgisi nasil yazDIRILIR
        System.out.println("time = " + response.time());


    }

}
