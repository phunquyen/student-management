package com.xtel.tranning.student_management.model;

import com.xtel.tranning.student_management.entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public static void insert(Student std) throws SQLException {
        if(isExistedName(std.getName())){
            System.out.println("ten da ton tai !!");
            return;
        };

        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into student(name,age,gender,address) values(?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, std.getName());
            ps.setInt(2, std.getOld());
            ps.setInt(3, std.getGender());
            ps.setString(4, std.getAddress_birth());
            ps.execute();
        } finally {
            close(ps);
            close(connection);
        }
    }

    //Lay thong tin sinh vien
    //==
//    public static boolean isExistedName(String name) throws SQLException {
//        Connection connection = ConnectionFactory.createConnection();
//        Statement statement = connection.createStatement();
//        ResultSet rs = null;
//        try {
//            String sql = "select * from student";
//            rs = statement.executeQuery(sql);
//            while (rs.next()) {
//                if(name.equals(rs.getString(2))){
//                    return true;
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            close(rs);
//            close(connection);
//        }
//        return false;
//    }
    //

    public static boolean isExistedName(String name) throws SQLException{
        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select count(1) c from student where name = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                if(rs.getInt("c") > 0){
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(rs);
            close(connection);
        }
        return false;
    }

    public static void close(AutoCloseable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
