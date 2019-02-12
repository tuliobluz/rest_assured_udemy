package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import files.resources;

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
                get(resources.placeGetData()).
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
    }
    @Test
    public void TestPost(){

         String b = "{"+
                        "\"location\": {"+
                            "\"lat\": -33.8669710,"+
                            "\"lng\": 151.1958750"+
                        "},"+
                        "\"accuracy\": 50,"+
                        "\"name\": \"Google Shoes!\","+
                        "\"phone_number\": \"(02) 9374 4000\","+
                        "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
                        "\"types\": [\"shoe_store\"],"+
                        "\"website\": \"http://www.google.com.au/\","+
                        "\"language\": \"en-AU\""+
                    "}";

         RestAssured.baseURI = prop.getProperty("HOST");

         Response res = given().
                    queryParam("key", prop.getProperty("KEY")).
                    body(b).
                    when().
                    post(resources.placePostData()).
                    then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                    body("status", equalTo ("OK")).
                    extract().response();

         String responseString = res.asString();
         System.out.println(responseString);
         JsonPath js = new JsonPath(responseString);
         String placeid = js.get("place_id");
         System.out.println(placeid);
    }
}
