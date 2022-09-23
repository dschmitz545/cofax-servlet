package questao6;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class WebApp {
    String uri = "https://run.mocky.io/v3/431da3f0-3981-4704-8b9a-1266842dff6c";

    @Test
    public void consultarAPI() {

        Response response =

        given()
                .contentType("application/json")
                .log().all()
            .when()
                .get(uri)
            .then()
                .log().all()
                .body("web-app.servlet.servlet-name", hasItem("cofaxTools"))
                .body("web-app.servlet.servlet-name", hasItem("cofaxAdmin"))
                .body("web-app.servlet.servlet-name", hasItem("cofaxEmail"))
                .body("web-app.servlet.servlet-name", hasItem("cofaxCDS"))
                .body("web-app.taglib.taglib-location", is("/WEB-inf/tlds/cofax.tld"))
                .body("web-app.servlet.init-param.betaServer", hasItem(true))
            .extract().response()
        ;

        JsonPath jsonPath = response.jsonPath();

        String taglibLocation = jsonPath.get("web-app.taglib.taglib-location");
        String betaServer = jsonPath.getString("web-app.servlet.init-param.betaServer");
        String servletName = jsonPath.getString("web-app.servlet.servlet-name");

        System.out.println("O valor do atributo taglib-location: " + taglibLocation);
        System.out.println("O valor do atributo betaServer: " + betaServer);
        System.out.println("O valor do atributo servlet-name: " + servletName);
    }
}