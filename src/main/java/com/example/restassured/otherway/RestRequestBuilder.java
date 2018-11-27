package com.example.restassured.otherway;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.MediaType;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

public class RestRequestBuilder {

    RequestSpecification requestSpecification;

    public RestRequestBuilder() {
        configureRequest();
    }

    private void configureRequest() {
        requestSpecification = RestAssured.given().relaxedHTTPSValidation().log().all();
    }

    public RestRequestBuilder setRestRequestHeader(Header header) {
        createRequestwithHeaders().setHeader(header);
        return this;
    }

    public RestRequestBuilder setRestRequestHeaders(List<Header> headerList) {
        createRequestwithHeaders().setHeaders(headerList);
        return this;
    }

    public RestRequestBuilder setRestRequestHeaders(Map<String, String> headerMap) {
        createRequestwithHeaders().setHeaders(headerMap);
        return this;
    }

    private RestRequestHeaders createRequestwithHeaders() {
        return new RestRequestHeaders(requestSpecification);
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public RestRequestBuilder setBaseUrl(String url) {
        requestSpecification.basePath(url);
        return this;
    }

    public RestRequestBuilder setDefaultAuthentication() {
        requestSpecification.auth().none();
        return this;
    }

    public RestRequestBuilder setPreemptiveBasicAuthentication(String userId, String passwd) {
        requestSpecification.auth().preemptive().basic(userId, passwd);
        return this;
    }

    public RestRequestBuilder setHttpsRelaxedValidationWithString(String arg) {
        requestSpecification.relaxedHTTPSValidation(arg);
        return this;
    }

    public RestRequestBuilder setContentType(String contentType) {
        requestSpecification.contentType(contentType);
        return this;
    }

    public RestRequestBuilder setContentTypeAsJson() {
        requestSpecification.contentType(MediaType.APPLICATION_JSON);
        return this;
    }

    public RestRequestBuilder setContentTypeAsXml() {
        requestSpecification.contentType(MediaType.APPLICATION_XML);
        return this;
    }

    public RestRequestBuilder setContentTypeAsMultipartFormData() {
        requestSpecification.contentType(MediaType.MULTIPART_FORM_DATA);
        return this;
    }

    public RestRequestBuilder setAcceptAsMultipartFormData() {
        requestSpecification.accept(MediaType.MULTIPART_FORM_DATA);
        return this;
    }

    public RestRequestBuilder setAcceptasJson() {
        requestSpecification.accept(MediaType.APPLICATION_JSON);
        return this;
    }

    public RestRequestBuilder setAcceptasXml() {
        requestSpecification.accept(MediaType.APPLICATION_XML);
        return this;
    }

    public RestRequestBuilder setAccept(String accept) {
        requestSpecification.accept(accept);
        return this;
    }

    public RestRequestBuilder setBaseURI(String baseUri) {
        requestSpecification.baseUri(baseUri);
        return this;
    }

    public RestRequestBuilder getRequestBuilderWithNoAuthContentTyeAsJson() {
        return setDefaultAuthentication().setContentTypeAsJson().setAcceptasJson();
    }

    public RestRequestBuilder setParam(String key, String value) {
        requestSpecification.param(key, value);
        return this;
    }

    public RestRequestBuilder setFormParam(String key, String value) {
        requestSpecification.formParam(key, value);
        return this;
    }

    public RestRequestBuilder setFormParam(String key, Object obj) {
        requestSpecification.formParam(key, obj);
        return this;
    }

    public RestRequestBuilder setMultipartFile(String file, String fileName) {
        try {
            if (!fileName.isEmpty())
                requestSpecification.multiPart(file, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * You have to provide "," seperated "param:value" pairs to attach headers
     * For example: type:test,trade:spot
     *
     * @param headers
     * @return
     */
    public RestRequestBuilder setRequestHeaders(String headers) {
        try {
            Stream.of(headers.trim().split(",")).collect(Collectors.toList()).stream().map(headerString -> headerString.split(":")).forEach(header -> {
                requestSpecification.header(new Header(header[0], header[1]));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public RestRequestBuilder setAcceptAsApplicationOctetStream() {
        requestSpecification.accept(MediaType.APPLICATION_OCTET_STREAM);
        return this;
    }

}