package com.j3s.helper;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

/**
 * user class used by {@link APIHelper}
 */
public class User {
    /**
     * prepare a basic bearer request
     * @param clientAndSecret client and secret
     * @return new RequestSpecification
     */
    public RequestSpecification prepareRequest(String clientAndSecret)
    {
        RequestSpecification httpsRequest = SerenityRest.given().header("Authorization","Basic "+clientAndSecret/*TODO Bearer*/).relaxedHTTPSValidation();
        return httpsRequest;
    }

    /**
     * prepare a request with bearer token
     * @param bearerToken said bearer
     * @return new RequestSpecification with valid bearer auth
     */
    public RequestSpecification basicBearer(String bearerToken){
        RequestSpecification httpsRequest = SerenityRest.given().headers(
                "Authorization",
                "Bearer " + bearerToken,
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON).relaxedHTTPSValidation();
        return httpsRequest;
    }
}
