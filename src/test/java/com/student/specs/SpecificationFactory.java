package com.student.specs;

import com.student.utils.BaseTest;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.lessThan;

public class SpecificationFactory implements BaseTest {
    public static synchronized ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder()
                .expectHeader("Content-Type", "application/json;charset=UTF-8")
                .expectHeader("Transfer-Encoding", "chunked")
                .expectResponseTime(lessThan(5L), TimeUnit.SECONDS)
                .build();
    }

    public static RequestSpecification requestSpec() {
        RequestSpecBuilder requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(prop.getProperty("baseUrl"))
                .setPort(Integer.parseInt(prop.getProperty("port")))
                .addFilter(new AllureRestAssured());

        if(prop.getProperty("log").equalsIgnoreCase("enable")) {
            requestSpec.addFilter(new RequestLoggingFilter())
                    .addFilter(new ResponseLoggingFilter());
        }
        return requestSpec.build();
    }
}
