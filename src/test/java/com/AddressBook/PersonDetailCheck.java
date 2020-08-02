package com.AddressBook;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class PersonDetailCheck {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
    }

    public Response getPersonList() {
        Response response = RestAssured.get("/personDetail/list");
        return response;
    }
    @Test
    public void onCallingList_ReturnPersonDetail() {
        Response response = getPersonList();
        System.out.println("AT FIRST: " + response.asString());
        response.then().body("id", Matchers.hasItems(1, 2));
        response.then().body("FirstName", Matchers.hasItems("Kajal Tiwari"));
    }
}
