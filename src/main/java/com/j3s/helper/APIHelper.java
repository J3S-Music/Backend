package com.j3s.helper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

/**
 * Basic API helper for the spotify api
 * Static helper class
 */
public abstract class APIHelper {
    /**
     * Prepare a request for the GET() Bearer token call
     * @param actor default actor, just use new Actor() on call
     * @param baseUri baseUri in, this case use https://accounts.spotify.com
     * @param clientAndSecret the client and secret string from the spotify DevBoard [Currently overwritten with my shit]
     * @return RequestSpecification for GET() Bearer
     */
    public static RequestSpecification getNewRequestSpecificationForBearer(User actor, String baseUri, String clientAndSecret)
    {
        RequestSpecification test = actor.prepareRequest("MmUxMDk1MGQzMWU5NGQ2NmFhOTQxZWM1NTY5ODJjOWM6MjFhMzI0MjVlYmNjNDVhMjg1ZTRkOWYzYWMzZjYwYTg=");
        test.baseUri(baseUri);
        test.contentType(ContentType.URLENC);
        test.formParam("grant_type","client_credentials");

        return test;

    }

    /**
     * The baseic searchQ RequestSpecification
     * @param actor default actor, just use new Actor() on call
     * @param baseUri base Uri, in this case use the Buildotron Url (see usage for example)
     * @param bearerToken auth Bearer token string
     * @return RequestSpecification for new SongQ
     */
    public static RequestSpecification getNewSongQ(User actor, String baseUri, String bearerToken)
    {
        RequestSpecification test = actor.basicBearer(bearerToken);
        test.baseUri(baseUri);
        test.contentType(ContentType.URLENC);
        test.formParam("grant_type","client_credentials");

        return test;

    }

    /**
     * Somewhat a simplified version of {@link #getNewRequestSpecificationForBearer}
     * @param clientAndSecret the client and secret string from the spotify DevBoard [Currently still overwritten with my shit]
     * @return RequestSpecification for GET() Bearer
     */
    public static String getBearerToken(String clientAndSecret){
        RequestSpecification bear = getNewRequestSpecificationForBearer(new User(),"https://accounts.spotify.com", clientAndSecret);
        Response res = bear.post("/api/token");
        JSONObject jRes = res.getBody().as(JSONObject.class);
        return (String) jRes.get("access_token");
    }
}
