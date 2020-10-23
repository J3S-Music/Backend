package com.j3s.helper;

import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class User {
    public RequestSpecification prepareRequest()
    {
        RequestSpecification httpsRequest = SerenityRest.given().header("Authorization","Basic MmUxMDk1MGQzMWU5NGQ2NmFhOTQxZWM1NTY5ODJjOWM6MjFhMzI0MjVlYmNjNDVhMjg1ZTRkOWYzYWMzZjYwYTg="/*TODO Bearer*/).relaxedHTTPSValidation();
        return httpsRequest;
    }
}
