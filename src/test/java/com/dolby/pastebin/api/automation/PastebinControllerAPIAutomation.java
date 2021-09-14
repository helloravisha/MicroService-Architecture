package com.dolby.pastebin.api.automation;

import com.dolby.pastebin.DolbyPastebinApplication;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

/**
 * This is API automation test is used for validating the end points, rest assured is leveraged for the same.
 *  These tests brings in advantages to Dev and QA .
 *  <ul>
 *      <li> Helps in deploying the API end points without deploying the application to any physical server</li>
 *      <li> QA can add easily work on this test and can add more request and response specs to validate more
 *            use cases</li>
 *      <li> acts as an integration test and it brings in the entire application context, if we have any test to validate
 *           end to end , along with DB connectivity or any other integrations , this test helps</>
 *   </>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {DolbyPastebinApplication.class})
public class PastebinControllerAPIAutomation {

  private static final String PASTEBIN_API = "api/v1/bin/";
  private static final String HOST = "http://localhost:";
  private static final String ROOT_CONTEXT = "/dolby/";

  @LocalServerPort protected int port;
  protected RequestSpecification httpRequest;

  @Before
  public void setup() {
    RestAssured.baseURI = HOST + port + ROOT_CONTEXT;
    httpRequest = RestAssured.given();
  }

  @Test
  public void validateBinCreationAPI() throws Exception {
    RequestSpecification requestSpec = getRequestSpecForBinCreation();
    ResponseSpecification responseSpec = getResponseSpecForBinCreation();
    httpRequest
        .spec(requestSpec)
        .request(Method.POST, PASTEBIN_API)
        .then()
        .spec(responseSpec)
        .log();
  }

  private RequestSpecification getRequestSpecForBinCreation() {
    Map<String, Object> payload = new HashMap<>();
    payload.put("data", "This is sample test");
    payload.put("duration", 10);
    return new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .setAccept(ContentType.JSON)
            .setBody(payload)
        .build();
  }

  private ResponseSpecification getResponseSpecForBinCreation() {
    ResponseSpecification responseSpecification = new ResponseSpecBuilder().build().statusCode(200);
    return responseSpecification;
  }
}
