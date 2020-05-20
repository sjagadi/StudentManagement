package com.student.requests;

import com.student.specs.SpecificationFactory;
import com.student.utils.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class StudentsRestAPI implements BaseTest {
    public Response getAllStudents() {
        return RestAssured.given()
                .spec(SpecificationFactory.logPayloadResponseInfo())
                .get(StudentApiUrl.getStudentApiUrl());
    }

    public Response getStudent(int studentId) {
        return RestAssured.given()
                .spec(SpecificationFactory.logPayloadResponseInfo())
                .get(StudentApiUrl.getSpecificStudentApiUrl(studentId));
    }

    public Response createStudent(String payload) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .spec(SpecificationFactory.logPayloadResponseInfo())
                .body(payload)
                .post(StudentApiUrl.postStudentApiUrl());
    }

    public Response updateStudent(String payload, int studentId) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .spec(SpecificationFactory.logPayloadResponseInfo())
                .body(payload)
                .put(StudentApiUrl.getSpecificStudentApiUrl(studentId));
    }

    public Response deleteStudent(int studentId) {
        return RestAssured.given()
                .spec(SpecificationFactory.logPayloadResponseInfo())
                .delete(StudentApiUrl.getSpecificStudentApiUrl(studentId));
    }

    private static class StudentApiUrl {
        static String getStudentApiUrl() {
            return "/student/list";
        }

        static String getSpecificStudentApiUrl(int studentId) {
            return "/student/" + studentId;
        }

        static String postStudentApiUrl() {
            return "/student";
        }
    }

    public static class StudentPayloadConstructor {
        String firstName;
        String lastName;
        String email;
        String programme;
        JSONArray courses;

        public StudentPayloadConstructor(String firstName, String lastName, String email, String programme, JSONArray courses) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.programme = programme;
            this.courses = courses;
        }

        public JSONObject build() {
            return new JSONObject()
                    .put("firstName", firstName)
                    .put("lastName", lastName)
                    .put("email", email)
                    .put("programme", programme)
                    .put("courses", courses);
        }
    }
}
