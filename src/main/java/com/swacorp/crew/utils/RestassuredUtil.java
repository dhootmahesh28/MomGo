package com.swacorp.crew.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class RestassuredUtil {

    RequestSpecification req;
    private String res;
    private int statusCode;

    public String getResponseBody(){
        try {
            return res;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public int ExecuteREST(String method, String url, HashMap<String, String> heareds){
        System.out.println("Calling ExecuteGET");
        String temp = "";
        String completeUri = "";
        int i=0;

        for (Map.Entry<String, String> entry : heareds.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (i<1) {
                temp = temp + key + "=" + value;
                ++i;
            }else{
                temp = temp + "&"+key + "=" + value;
            }
        }
        completeUri = url+"?"+temp;
        Response response = null;
        try {
            response = RestAssured.given()
                    .when()
                    .get(completeUri);
            } catch (Exception e) {
            e.printStackTrace();
        }
        res= response.asString();
        return statusCode;
    }
}
