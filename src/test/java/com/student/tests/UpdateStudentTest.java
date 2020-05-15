package com.student.tests;

import com.student.utils.Payloads;
import com.student.utils.Util;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;

@Epic("Student Management")
@Story("Delete students from the students database")
public class UpdateStudentTest extends AbstractStudentApi {
    @Feature("Update the student")
    @Test
    public void Test007_UpdateStudent() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        programme = "Financial Analysis";
        courses = new ArrayList<>();
        courses.add("Java");
        courses.add("Python");
        courses.add("C++");
        payload = Payloads.createStudentPayload(firstName, lastName, email, programme, courses);
        student.studentAPI()
                .updateStudent(payload.toString(), Util.getStudentId(response, email))
                .then().statusCode(200)
                .body("msg", equalTo("Student Updated"));
    }
}
