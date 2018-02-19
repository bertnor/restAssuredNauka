package restassured.tests;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class Serwer {

    @BeforeClass
    public static void setup() {
        System.out.println("serwer");
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8090);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }


        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/rest-garage-sample/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;

    }

}