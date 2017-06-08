package com.example.restassured.common;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Ashutosh on 13-03-2017.
 */
public class CommonFunctions {

    protected static String responseBody=null;
    private void setUpBaseEndPoint(){

        RestAssured.baseURI="https://jsonplaceholder.typicode.com";

    }

    /**
     *In this method we are loading the JSON and updating the variable value at runtime and converting it to string for further processing
     * @param value1 some value which you want to pass and modify at runtime
     * @param Value2 some value which you want to pass and modify at runtime
     * @return
     */
    public String loadingJsonToPost(String value1,String Value2){

        String jsonAsAString=null;
        String userName=value1;
        String password=value1;

        File filetopost= new File(".\\src\\main\\resources\\JsonToPost.json").getAbsoluteFile();
        String fileAbsPath= filetopost.getAbsolutePath();
        System.out.println(fileAbsPath);

        try{

            jsonAsAString= FileUtils.readFileToString(filetopost);
            //{Username} this pattern should be in json ,it will match and replace it
            jsonAsAString= StringUtils.replace(jsonAsAString,"{Username}",userName);
            //{Username} this pattern should be in json ,it will match and replace it
            jsonAsAString= StringUtils.replace(jsonAsAString,"{Password}",password);


        }catch(IOException e){

        }

        return jsonAsAString;
    }


    /**
     *
     * @param jsonRequestAsAString pass the json to be posted as a string
     * @return get back the response as string from the posted json
     */
    public String getPostResponseGeneric(String jsonRequestAsAString){

        setUpBaseEndPoint();

        Response response=
                given().contentType("application/json")
                .body(jsonRequestAsAString)
                .when()
                .post("/posts");

        responseBody=response.asString();

        return responseBody;
    }

    public boolean checkAReturnValue(String jsonResponse) {

        Boolean expectedValueFound=false;
        JsonPath jsonPath = new JsonPath(jsonResponse);

        List<List<String>> value = jsonPath.getList("returnValue");

        String expectedValueFixedPosition=value.get(3).get(0);

        if("123".equalsIgnoreCase(expectedValueFixedPosition)){

            expectedValueFound=true;
        }



        return expectedValueFound;
    }

/**
 * <!-- https://mvnrepository.com/artifact/org.json/json -->
 <dependency>
 <groupId>org.json</groupId>
 <artifactId>json</artifactId>
 <version>20160810</version>
 </dependency>
 */


    public void getRequestFindCapital() throws JSONException {

        //make get request to fetch capital of norway
        Response resp = get("http://restcountries.eu/rest/v1/name/norway");

        //Fetching response in JSON
        JSONArray jsonResponse = new JSONArray(resp.asString());

        //Fetching value of capital parameter
        String capital = jsonResponse.getJSONObject(0).getString("capital");

        //Asserting that capital of Norway is Oslo
        //Assert.assertEquals(capital, "Oslo");
    }


}
