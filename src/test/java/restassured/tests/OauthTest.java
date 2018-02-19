package restassured.tests;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class OauthTest {

//    @Test
    public void test_APIWithOAuth2Authentication_ShouldBeGivenAccess() {

        given().
                auth().
                oauth("0f2a90bed0eb8a092da8fe6e567594ad", "084dcd13d3bafa80c0a858c0891f1d22a928ecf4c2c50e9b645524d8ebcb3b01","", "")
                .contentType(ContentType.JSON).
                when().
                get("https://api.trello.com/1/members/me/boards").
                then().
                body(Matchers.containsString("tablica")).
                statusCode(200);
    }

    @Test
    public void trello() {
        System.out.println("jebac");
        Response response = (Response) given().
                auth().
                oauth("0f2a90bed0eb8a092da8fe6e567594ad","consumerSecret", "084dcd13d3bafa80c0a858c0891f1d22a928ecf4c2c50e9b645524d8ebcb3b01", "")
                .contentType(ContentType.JSON).
                when().
//                get("https://api.trello.com/1/members/me/boards");
                delete("https://api.trello.com/1/boards/5a8b34f280019a96a0e92a98");
//                body(Matchers.containsString("tablica")).
//                statusCode(200).log().all();
        JsonPath json = new JsonPath(response.asString());
        System.out.println("aha");
        System.out.println(json.getString("name"));
        System.out.println(json.getString("id"));

    }

}
