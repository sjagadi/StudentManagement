package com.student.tests;

import com.student.utils.BaseTest;
import com.student.utils.Payloads;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;

public abstract class AbstractStudentApi implements BaseTest {
    static String firstName;
    static String lastName;
    static String email;
    static String programme;
    static List<String> courses;
    static JSONObject payload;
    static Response response;

    @BeforeEach
    public void createNewStudent() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        programme = "Financial Analysis";
        courses = new ArrayList<>();
        courses.add("Java");
        courses.add("Python");
        courses.add("C++");
        payload = Payloads.createStudentPayload(firstName, lastName, email, programme, courses);
        response = student.studentAPI().createStudent(payload.toString());
        response.then().statusCode(201)
                .body("msg", equalTo("Student added"));
        response = student.studentAPI().getAllStudents();
    }
}
