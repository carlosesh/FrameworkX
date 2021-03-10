package api;

import static io.restassured.RestAssured.given;

import api.interfaces.ICrud;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ServiceImp implements ICrud {

    public Response getRequest(RequestSpecification requestSpec) {

        return given()
                .spec(requestSpec)
                .relaxedHTTPSValidation()
                .log()
                .all()
                .when()
                .get();
    }

}