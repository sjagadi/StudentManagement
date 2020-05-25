package com.student.tests;

import com.student.requests.StudentsRestAPI;
import com.student.utils.BaseTest;
import com.student.utils.Util;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import static org.hamcrest.Matchers.equalTo;

public abstract class AbstractStudentApi implements BaseTest {
    static String firstName;
    static String lastName;
    static String email;
    static String programme;
    static JSONArray courses;
    static JSONObject payload;
    static Response response;

    @BeforeEach
    public void createNewStudent() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        programme = "Financial Analysis";
        courses = new JSONArray()
                .put("Java")
                .put("Python")
                .put("C++");
        payload = new StudentsRestAPI.StudentPayloadConstructor(firstName, lastName, email, programme, courses).build();
        response = student.studentAPI().createStudent(payload.toString());
        response.then().statusCode(201)
                .body("msg", equalTo("Student added"));
        response = student.studentAPI().getAllStudents();
    }
}
