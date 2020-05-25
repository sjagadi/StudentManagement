package com.student.tests;

import com.student.requests.StudentsRestAPI;
import com.student.utils.Util;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
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
        courses = new JSONArray()
                .put("Java")
                .put("Python")
                .put("C++");
        payload = new StudentsRestAPI.StudentPayloadConstructor(firstName, lastName, email, programme, courses).build();
        student.studentAPI()
                .updateStudent(payload.toString(), Util.getStudentId(response, email))
                .then().statusCode(200)
                .body("msg", equalTo("Student Updated"));

        response = student.studentAPI().getStudent(Util.getStudentId(response, email));
        response.then().statusCode(200)
                .body("firstName", equalTo(firstName))
                .body("lastName", equalTo(lastName))
                .body("programme", equalTo(programme))
                .body("email", equalTo(email))
                .body("courses", equalTo(Util.convertJSONArrayToList(courses)));
    }
}
