package com.course.ums.db;

import com.course.ums.Queries.Queries;
import org.json.JSONObject;

import java.sql.*;

/**
 * Created by vh on 11/2/17.
 */
public class DBManager {

    private static final String DB_URL = "jdbc:mysql://Svhomework:123456@127.0.0.1:3306/ums?useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static boolean validateToken(String token, String role) {
        String table = role + "s";

        PreparedStatement ps = null;
        try {
            ps = DBManager.getConnection().prepareStatement("SELECT id FROM " + table + " WHERE id=?");
            ps.setInt(1, Integer.parseInt(token));
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int addUser(JSONObject request) throws SQLException {
        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO users(first_name, last_name, email, password) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, request.getString("first_name"));
        ps.setString(2, request.getString("last_name"));
        ps.setString(3, request.getString("email"));
        ps.setString(4, request.getString("password"));
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }

    public static int addCourse(JSONObject request) throws SQLException {
        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO courses(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, request.getString("name"));
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }

    public static int addGroup(JSONObject request) throws SQLException {
        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO groups(semesters_id, name) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, request.getInt("semesters_id"));
        ps.setString(2, request.getString("name"));
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }

    public static int addSemester(JSONObject request) throws SQLException {
        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO semesters(year, `index`) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, request.getInt("year"));
        ps.setString(2, request.getString("index"));
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }

    public static int addGroupStudent(JSONObject request) throws SQLException {
        PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement( Queries.INSERT_GROUP_STUDENT, Statement.RETURN_GENERATED_KEYS );
        preparedStatement.setInt( 1, request.getInt( "groups_id" ) );
        preparedStatement.setInt( 2, request.getInt( "students_id" ) );
        preparedStatement.setInt( 3, request.getInt( "semesters_id" ) );
        preparedStatement.execute();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();


        return rs.getInt(1);
    }


    public static int addGroupTeacher(JSONObject request) throws SQLException {

        PreparedStatement ps = DBManager.getConnection().prepareStatement( Queries.INSERT_GROUP_TEACHER, Statement.RETURN_GENERATED_KEYS );
        ps.setInt( 1, request.getInt( "groups_id" ) );
        ps.setInt( 2, request.getInt( "teachers_courses_id" ) );
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }

    public static int addTeacherCourse(JSONObject request) throws SQLException {

        PreparedStatement ps = DBManager.getConnection().prepareStatement( Queries.INSERT_TEACHER_COURSE, Statement.RETURN_GENERATED_KEYS );
        ps.setInt( 1, request.getInt( "teachers_id" ) );
        ps.setInt( 2, request.getInt( "courses_id" ) );
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }

    public static int removeGroupStudent(JSONObject request) throws SQLException {

        int groupsId = request.getInt( "groups_id" );
        int studentId = request.getInt( "students_id" );
        int semesterId = request.getInt( "semesters_id" );

        PreparedStatement ps = DBManager.getConnection().prepareStatement( Queries.SELECT_GROUP_STUDENT, Statement.RETURN_GENERATED_KEYS );
        ps.setInt( 1, groupsId );
        ps.setInt( 2, studentId );
        ps.setInt( 3, semesterId);

        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            throw new RuntimeException( "Group, student or semester does not exist!" );
        }

        ps = DBManager.getConnection().prepareStatement( Queries.REMOVE_GROUP_STUDENT, Statement.RETURN_GENERATED_KEYS );
        ps.setInt(1, groupsId);
        ps.setInt(2, studentId);
        ps.setInt( 3, semesterId);
        ps.execute();

        return rs.getInt( "id" );

    }

    public static int removeGroupTeacher(JSONObject request) throws SQLException {

        int groupsId = request.getInt( "groups_id" );
        int teacherCourseId = request.getInt( "teachers_courses_id" );

        PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(Queries.SELECT_GROUP_TEACHER, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, groupsId);
        preparedStatement.setInt(2, teacherCourseId);

        ResultSet rs = preparedStatement.executeQuery();

        if (!rs.next()) {
            throw new RuntimeException( "Group or teacher does not exist!" );
        }

        PreparedStatement preparedStatement1 = DBManager.getConnection().prepareStatement( Queries.REMOVE_GROUP_TEACHER );
        preparedStatement1.setInt( 1, groupsId );
        preparedStatement1.setInt( 2, teacherCourseId );
        preparedStatement1.execute();

        return rs.getInt( "id" );

    }

    public static int removeTeacherCourse(JSONObject request) throws SQLException {

        int teachersId = request.getInt( "teachers_id" );
        int coursesId = request.getInt( "courses_id" );

        PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(Queries.SELECT_TEACHER_COURSE, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, teachersId);
        preparedStatement.setInt(2, coursesId);

        ResultSet rs = preparedStatement.executeQuery();


        if (!rs.next()) {
            throw new RuntimeException( "Teacher or course does not exist!" );
        }

        PreparedStatement preparedStatement1 = DBManager.getConnection().prepareStatement( Queries.REMOVE_TEACHER_COURSE, Statement.RETURN_GENERATED_KEYS );
        preparedStatement1.setInt( 1, teachersId );
        preparedStatement1.setInt( 2, coursesId );
        preparedStatement1.execute();


        return rs.getInt( "id" );

    }

}
