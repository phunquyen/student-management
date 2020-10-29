package com.xtel.tranning.student_management;

import com.xtel.tranning.student_management.manager.StudentManager;
import com.xtel.tranning.student_management.model.StudentDao;

import java.sql.SQLException;

public class MainApplication {
    public static void main(String[] args) throws SQLException {
        StudentManager studentManager = new StudentManager();
        studentManager.add(2);
    }
}
