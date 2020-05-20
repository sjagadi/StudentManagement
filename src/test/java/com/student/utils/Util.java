package com.student.utils;

import io.restassured.response.Response;
import org.json.JSONArray;
import java.util.ArrayList;
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

    public static List<String> convertJSONArrayToList(JSONArray array) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < array.length(); i++) {
            list.add(array.get(i).toString());
        }
        return list;
    }
}
