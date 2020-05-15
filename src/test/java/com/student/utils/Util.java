package com.student.utils;

import io.restassured.response.Response;

import java.util.List;

public class Util {
    public static int getStudentId(Response response, String expectedEmail) {
        List<Object> objects = response.jsonPath().getList("$");
        String studentId = "";
        for (int i = 0; i < objects.size(); i++) {
            String actualEmail = response.jsonPath().getString("email[" + i + "]");
            if (actualEmail.equals(expectedEmail)) {
                studentId = response.jsonPath().getString("id[" + i + "]");
            }
        }
        return Integer.parseInt(studentId);
    }
}
