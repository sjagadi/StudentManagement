package com.student.client;

import com.student.requests.StudentsRestAPI;

public class StudentClient {
    private static StudentClient studentClient;
    private StudentsRestAPI studentAPI;
    private StudentClient() {
    }

    public static StudentClient getInstance() {
        if(studentClient == null) {
            synchronized (StudentClient.class) {
                studentClient = new StudentClient();
            }
        }
        return studentClient;
    }

    public StudentsRestAPI studentAPI() {
        if(studentAPI == null) {
            studentAPI = new StudentsRestAPI();
        }
        return studentAPI;
    }
}
