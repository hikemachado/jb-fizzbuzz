package io.hike;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
public class JurosBaixoResourceTest {


    @Test
    public void testResetTreasureEndpoint(){
        given().when().get("/jb/resettreasure").then().statusCode(200).body(is("reset"));

    }

    @Test
    public void testFindTreasureEndpoint(){
        given().when().get("/jb/findtreasure").then().statusCode(200).body(is("found"));

    }
    
    
}
