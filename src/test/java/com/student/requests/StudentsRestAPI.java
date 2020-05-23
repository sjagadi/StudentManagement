package com.student.requests;

import com.student.client.CommonAPI;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class StudentsRestAPI extends CommonAPI {
    public Response getAllStudents() {
        return getSpecification().get(StudentApiUrl.getStudentApiUrl());
    }

    public Response getStudent(int studentId) {
        return getSpecification().get(StudentApiUrl.getStudentByIdApiUrl(studentId));
    }

    public Response createStudent(String payload) {
        return getSpecification().body(payload)
                .post(StudentApiUrl.postStudentApiUrl());
    }

    public Response updateStudent(String payload, int studentId) {
        return getSpecification().body(payload)
                .put(StudentApiUrl.getStudentByIdApiUrl(studentId));
    }

    public Response deleteStudent(int studentId) {
        return getSpecification()
                .delete(StudentApiUrl.getStudentByIdApiUrl(studentId));
    }

    private static class StudentApiUrl {
        static String getStudentApiUrl() {
            return "/student/list";
        }

        static String getStudentByIdApiUrl(int studentId) {
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
