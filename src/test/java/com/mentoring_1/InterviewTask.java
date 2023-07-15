package com.mentoring_1;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utils.ApiCalls;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class InterviewTask {
    @Test
    public void capitalTest() {
        RestAssured.baseURI="https://restcountries.com";
        RestAssured.basePath="v3.1/all";
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get().then().statusCode(200).extract().response();

        List<Map<String,Object>>parsedResp = response.as(new TypeRef<List<Map<String, Object>>>() {});

            String actCapital="";
            String expCapital="Kyiv";
        for (int i=0;i<parsedResp.size();i++){
            Map<String,Object>outerMap = parsedResp.get(i);
            Map<String,Object>innerMap = (Map<String, Object>) outerMap.get("name");
            if (innerMap.get("common").equals("Ukraine")){
                List<String>capital = (List<String>) outerMap.get("capital");
                actCapital+=capital.get(0);
            }
        }
        Assert.assertEquals(expCapital,actCapital);
    }

    @Test
    public void validateBreakingBadQuotes() {
        RestAssured.baseURI="https://api.breakingbadquotes.xyz";
        RestAssured.basePath="v1/quotes";

        Response response = ApiCalls.GET("/10");

        List<Map<String,Object>>parsed = response.as(new TypeRef<List<Map<String, Object>>>() {});
        for (int i=0;i< parsed.size();i++){
            Map<String,Object>map = parsed.get(i);
            if (map.get("author").equals("Jesse Pinkman")){
                System.out.println(map.get("quote"));
            }
        }
    }
}














