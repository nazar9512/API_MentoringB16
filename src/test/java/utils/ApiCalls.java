package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiCalls {
    public static Response GET(){
       return RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(200).extract().response();
    }
    public static Response GET(String endpoint){
        return RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(200).extract().response();
    }
}
