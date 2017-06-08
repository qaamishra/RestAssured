package com.example.restassured.common;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;

/**
 * Created by Ashutosh on 13-03-2017.
 */
public class WithHeaders {

    protected RequestSpecification requestSpecification;
    public static final String value1 = "val1";
    public static final String value2 = "val2";

    public void setUp() {

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setPort(9009);
        builder.setBaseUri("https://jsonplaceholder.typicode.com");
        builder.addHeader("X-API-KEY", "encryptedkey");

        requestSpecification = builder.build();
    }

    public void getMethod() {

                given()
                    .spec(requestSpecification)
                    .param("Variable1", value1)
                    .param("Variable2", value2)
                .when()
                    .get("/users")
                .then()
                    .statusCode(200)
                    .body("[staus]", equalTo("hello"))
                    .body("unit", equalTo(""))
                    .body(containsString("sometext"));

    }

    public void getParameter(String count){

        given()
                    .spec(requestSpecification)
                .when()
                    .get(String.format("/users/%s",count))
                .then()
                    .log().all()
                    .statusCode(200)
                    .body(matchesJsonSchema("\\src\\main\\resources\\JsonToPost.json"));
            }

    public void getUserData(String userId) {
        Response response =
                given().log().all().
                        contentType("application/json").
                        when().
                        get("/user/" + userId).
                        then().
                        statusCode(200).
                        extract().response();

        String value= (String) response.path("login");
    }

}
