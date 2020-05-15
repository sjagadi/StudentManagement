package com.student.utils;

import com.github.javafaker.Faker;
import com.student.client.StudentClient;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

public interface BaseTest {
    PropertyReader prop = PropertyReader.getInstance();
    StudentClient student = StudentClient.getInstance();
    Faker faker = new Faker();
    @BeforeAll
    static void initURL() {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(prop.getProperty("port"));
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
