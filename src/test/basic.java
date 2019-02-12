package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import files.resources;
import files.payLoad;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class basic {

    Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {


        FileInputStream fis = new FileInputStream("/Users/tluz/Documents/DemoProject/src/files/env.properties");
        prop.load(fis);

        //prop.get("HOST");
    }

    @Test
    public void TestGet(){

        RestAssured.baseURI = prop.getProperty("HOST");

        given().
                param("location", "-33.8670522,151.1957362").
                param("radius", "500").
                param("key", prop.getProperty("KEY")).
                when().
                get(resources.placeGetDataJSON()).
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
    }
    @Test
    public void TestPostJSON(){

         RestAssured.baseURI = prop.getProperty("HOST");

         Response res = given().
                    queryParam("key", prop.getProperty("KEY")).
                    body(payLoad.getPostDataJSON()).
                    when().
                    post(resources.placePostDataJSON()).
                    then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                    body("status", equalTo ("OK")).
                    extract().response();

         String responseString = res.asString();
         System.out.println(responseString);
         JsonPath js = new JsonPath(responseString);
         String placeid = js.get("place_id");
         System.out.println(placeid);
    }

    @Test
    public void TestPostXML() throws IOException{

        String postdata = payLoad.GenerateStringFromResource("/Users/tluz/Documents/DemoProject/src/files/postData.xml");

        RestAssured.baseURI = prop.getProperty("HOST");

        Response res = given().
                queryParam("key", prop.getProperty("KEY")).
                body(postdata).
                when().
                post(resources.placePostDataXML()).
                then().assertThat().statusCode(200).and().contentType(ContentType.XML).and().
                body("status", equalTo ("OK")).
                extract().response();
    }
}
