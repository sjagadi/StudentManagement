package com.student.tests;

import com.student.utils.Payloads;
import com.student.utils.Util;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;

@Epic("Student Management")
@Story("Add students to the students database")
public class CreateStudentTest extends AbstractStudentApi {
    @Feature("Verify the details of newly added student")
    @Test
    public void Test004_VerifyNewStudentCreated() {
        response = student.studentAPI().getStudent(Util.getStudentId(response, email));
        response.then().statusCode(200)
                .body("firstName", equalTo(firstName))
                .body("lastName", equalTo(lastName))
                .body("programme", equalTo(programme))
                .body("email", equalTo(email))
                .body("courses", equalTo(courses));
    }

    @Feature("System rejects same student detail twice")
    @Test
    public void Test005_SystemRejectsAlreadyExistingStudent() {
        payload = Payloads.createStudentPayload(firstName, lastName, email, programme, courses);
        response = student.studentAPI().createStudent(payload.toString());
        response.then().statusCode(500)
                .body("error", equalTo("Email address already exists"));
    }
}
