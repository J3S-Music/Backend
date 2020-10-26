package com.j3s.helper;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class User {
    public RequestSpecification prepareRequest(String clientAndSecret)
    {
        RequestSpecification httpsRequest = SerenityRest.given().header("Authorization","Basic "+clientAndSecret/*TODO Bearer*/).relaxedHTTPSValidation();
        return httpsRequest;
    }

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
