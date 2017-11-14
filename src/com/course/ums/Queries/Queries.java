package com.course.ums.Queries;

/**
 * Created by Darius on 11/7/2017.
 */
public class Queries {

    public static final String INSERT_USER = "INSERT INTO users(first_name, last_name, email, password) VALUES(?, ?, ?, ?)";
    public static final String INSERT_STUDENT = "INSERT INTO students(id, gender, birth_date) VALUES(?, ?, ?)";
    public static final String INSERT_TEACHER = "INSERT INTO teachers(id, level) VALUES(?, ?)";
    public static final String INSERT_COURSE = "INSERT INTO courses(name) VALUES(?)";
    public static final String INSERT_SEMESTER = "INSERT INTO semesters(year, index) VALUES(?, ?)";
    public static final String INSERT_GROUP = "INSERT INTO groups(semesters_id) VALUES(?)";
    public static final String INSERT_TEACHER_COURSE = "INSERT INTO teachers_courses(teachers_id, courses_id) VALUES(?, ?)";
    public static final String INSERT_GROUP_TEACHER = "INSERT INTO group_teacher_courses(groups_id, teachers_courses_id) VALUES(?, ?)";
    public static final String INSERT_GROUP_STUDENT = "INSERT INTO group_students(groups_id, students_id, semesters_id) VALUES(?, ?, ?)";

    public static final String SELECT_USER = "SELECT id FROM users WHERE users.email = ? AND users.password = ?";
    public static final String SELECT_TEACHER_COURSE = "SELECT id FROM teachers_courses WHERE teachers_id = ? AND courses_id = ?;";
    public static final String SELECT_GROUP_TEACHER = "SELECT id FROM group_teacher_courses WHERE groups_id = ? AND teachers_courses_id = ?;";
    public static final String SELECT_GROUP_STUDENT = "SELECT id FROM group_students WHERE groups_id = ? AND students_id = ? AND semesters_id = ?;";

    public static final String REMOVE_TEACHER_COURSE = "DELETE FROM teachers_courses where teachers_id = ? AND courses_id = ?;";
    public static final String REMOVE_GROUP_TEACHER = "DELETE FROM group_teacher_courses where groups_id = ? AND teachers_courses_id = ?;";
    public static final String REMOVE_GROUP_STUDENT = "DELETE FROM group_students where groups_id = ? AND students_id = ? AND semesters_id = ?;";
}
