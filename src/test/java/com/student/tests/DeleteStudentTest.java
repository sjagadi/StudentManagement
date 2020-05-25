package com.student.tests;

import com.student.utils.Util;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

@Epic("Student Management")
@Story("Delete students from the students database")
public class DeleteStudentTest extends AbstractStudentApi {
    @Feature("Delete the newly added student")
    @Test
    public void Test006_SystemDeletesAnyExistingStudent() {
        student.studentAPI()
                .deleteStudent(Util.getStudentId(response, email))
                .then().statusCode(204);
    }
}
