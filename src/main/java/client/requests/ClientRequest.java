package client.requests;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class ClientRequest {

    public <T> T createPostRequest(String endPoint, Object postBody, Class<T> responseClassName, int expectedStatusCode) {
        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).body(postBody);
        return request.post(endPoint).then().statusCode(expectedStatusCode).extract().as(responseClassName);
    }

    public <T> T createPostRequest(String endPoint, Object postBody, Class<T> responseClassName) {
        return createPostRequest(endPoint, postBody, responseClassName, 201);
    }

    public <T> T createPutRequest(String endPoint, Object putBody, Map<String, String> queryParams, Class<T> responseClassName) {
        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).queryParams(queryParams).body(putBody);
        return request.put(endPoint).as(responseClassName);
    }

    public <T> T createPatchRequest(String endPoint, Object patchBody, Map<String, String> queryParams, Class<T> responseClassName, int expectedStatusCode) {
        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).queryParams(queryParams).body(patchBody);
        return request.patch(endPoint).then().statusCode(expectedStatusCode).extract().as(responseClassName);
    }

    public <T> T createPatchRequest(String endPoint, Object patchBody, Map<String, String> queryParams, Class<T> responseClassName) {
       return createPatchRequest(endPoint,patchBody,queryParams,responseClassName,200);
    }

    public <T> T createGetRequest(String endPoint, Map<String, String> queryParams, Class<T> responseClassName) {
        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).queryParams(queryParams);
        return request.get(endPoint).as(responseClassName);
    }

    public void createDeleteRequest(String endPoint, Map<String, String> queryParams, int expectedStatusCode) {
        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).queryParams(queryParams);
        request.get(endPoint).then().statusCode(expectedStatusCode);
    }

}

