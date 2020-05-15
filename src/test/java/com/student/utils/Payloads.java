package com.student.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;

public class Payloads {
    public static JSONObject createStudentPayload(String firstName, String lastName, String email, String programme, List<String> courses) {
        JSONObject object = new JSONObject();
        object.put("firstName", firstName);
        object.put("lastName", lastName);
        object.put("email", email);
        object.put("programme", programme);
        JSONArray array = new JSONArray();
        for (String course : courses) {
            array.put(course);
        }
        object.put("courses", array);
        return object;
    }
}
