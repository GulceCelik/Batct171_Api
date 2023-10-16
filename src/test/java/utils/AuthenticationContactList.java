package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationContactList {
    public static void main(String[] args) {
        System.out.println("Token : " + generateToken());
    }
    public static String generateToken() {
        String body = "{\n" +
                "    \"email\": \"cortney.karl@free2ducks.com\",\n" +
                "    \"password\": \"123456Aa\"\n" +
                "}";
        Response response = given().body(body).contentType(ContentType.JSON).when().post("https://thinking-tester-contact-list.herokuapp.com/users/login");

        String token = response.jsonPath().getString("token");
        return token;
    }
}
