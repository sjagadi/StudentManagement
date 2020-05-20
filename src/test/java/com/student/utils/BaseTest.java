package com.student.utils;

import com.github.javafaker.Faker;
import com.student.client.StudentClient;

public interface BaseTest {
    PropertyReader prop = PropertyReader.getInstance();
    StudentClient student = StudentClient.getInstance();
    Faker faker = new Faker();
}
