package api.interfaces;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface ICrud {

    Response getRequest(RequestSpecification requestSpec);

}
