package com.j3s.helper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class APIHelper {
    public static RequestSpecification getNewRequestSpecification(User actor, String baseUri, String clientAndSecret)
    {
        RequestSpecification test = actor.prepareRequest("MmUxMDk1MGQzMWU5NGQ2NmFhOTQxZWM1NTY5ODJjOWM6MjFhMzI0MjVlYmNjNDVhMjg1ZTRkOWYzYWMzZjYwYTg=");
        test.baseUri(baseUri);
        test.contentType(ContentType.URLENC);
        test.formParam("grant_type","client_credentials");

        return test;

    }

    public static String getBearerToken(User user, String clientAndSecret){
        RequestSpecification bear = getNewRequestSpecification(new User(),"https://accounts.spotify.com", clientAndSecret);
        Response res = bear.post("/api/token");
        JSONObject jRes = res.getBody().as(JSONObject.class);
        return (String) jRes.get("access_token");
    }
}
