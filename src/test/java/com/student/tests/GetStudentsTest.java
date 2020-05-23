package com.student.tests;

import com.student.client.CommonAPI;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@Epic("Student Management")
@Story("Get student(s) using GET method")
public class GetStudentsTest extends CommonAPI {
    @Feature("Get all students list")
    @Test
    public void Test001_GetStudentsListTest() {
        student.studentAPI().getAllStudents()
                .then().statusCode(200)
                .body("size()", greaterThan(1));
    }

    @Feature("Get student with specific ID")
    @Test
    public void Test002_GetStudentById() {
        student.studentAPI().getStudent(1)
                .then().statusCode(200);
    }

    @Feature("Get student with wrong ID")
    @Test
    public void Test003_GetStudentWithWrongId() {
        student.studentAPI().getStudent(1000)
                .then().statusCode(404)
                .body("error", equalTo("Not Found!"));
    }
}
