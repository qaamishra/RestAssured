package com.example.restassured.otherway;


import io.restassured.response.Response;

public class RestApiManager {


    private RestRequestBuilder requestBuilder;


    public RestApiManager(RestRequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    public Response sendGetRequest(String resourceUrl) {
        Response response = requestBuilder.getRequestSpecification().get(resourceUrl);
        //response.prettyPrint();
        return response;

    }



    public Response sendPostRequest(String resourceUrl, String requestBody) {
        Response response = requestBuilder.getRequestSpecification().body(requestBody).post(resourceUrl);
        response.prettyPrint();
        return response;
    }

    public Response sendPostRequest(String resourceUrl) {
        Response response = requestBuilder.getRequestSpecification().post(resourceUrl);
        response.prettyPrint();
        return response;
    }


    public Response sendPutRequest(String resourceUrl) {
        Response response = requestBuilder.getRequestSpecification().put(resourceUrl);
        response.prettyPrint();
        return response;
    }

}