package com.example.restassured.otherway;

import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import java.util.Map;

import static org.codehaus.groovy.tools.shell.util.Logger.io;

public class RestRequestHeaders {

    RequestSpecification requestSpecification;


    public RestRequestHeaders(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public RestRequestHeaders setHeaders(List<Header> headerList) {
        headerList.forEach(header -> requestSpecification.header(header));
        return this;
    }

    public RestRequestHeaders setHeader(Header header) {
        requestSpecification.header(header);
        return this;
    }

    public RestRequestHeaders setHeaders(Map<String, String> headerMap) {
        requestSpecification.headers(headerMap);
        return this;
    }

}