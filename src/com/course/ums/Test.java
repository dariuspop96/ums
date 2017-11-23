package com.course.ums;

import com.course.ums.ws.GroupStudent.AddGroupStudent;
import com.course.ums.ws.GroupStudent.ListGroupStudent;
import com.course.ums.ws.GroupStudent.RemoveGroupStudent;
import com.course.ums.ws.GroupTeacher.AddGroupTeacher;
import com.course.ums.ws.GroupTeacher.ListGroupTeacher;
import com.course.ums.ws.GroupTeacher.RemoveGroupTeacher;
import com.course.ums.ws.TeacherCourse.AddTeacherCourse;
import com.course.ums.ws.TeacherCourse.ListTeacherCourse;
import com.course.ums.ws.TeacherCourse.RemoveTeacherCourse;
import com.course.ums.ws.course.AddCourse;
import com.course.ums.ws.course.ListCourses;
import com.course.ums.ws.group.AddGroup;
import com.course.ums.ws.group.ListGroups;
import com.course.ums.ws.semester.AddSemester;
import com.course.ums.ws.semester.ListSemesters;
import com.course.ums.ws.user.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by vh on 11/2/17.
 */
public class Test {
    public static void main(String[] args) throws Exception {

        Spark.port(8080);
        Spark.post("/hello", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                return "world";
            }
        });
        Spark.post("/user/authenticate", new Authenticate());
        Spark.post("/user/add", new AddUser());
        Spark.post("/user/list", new ListUsers());
        Spark.get("/user/list", new ListUsers());

        Spark.post("/user/student/add", new AddStudent());
        Spark.post("/user/teacher/add", new AddTeacher());
        Spark.post("/course/add", new AddCourse());
        Spark.post("/semester/add", new AddSemester());
        Spark.post("/group/add", new AddGroup());
        Spark.post("/group/student/add", new AddGroupStudent());
        Spark.post("/group/teacher/add", new AddGroupTeacher());
        Spark.post("/teacher/course/add", new AddTeacherCourse());

        Spark.post("user/student/list", new ListStudents());
        Spark.post("user/teacher/list", new ListTeachers());
        Spark.post("course/list", new ListCourses());
        Spark.post("semester/list", new ListSemesters());
        Spark.post("group/list", new ListGroups());
        Spark.post("/group/student/list", new ListGroupStudent());
        Spark.post("/group/teacher/list", new ListGroupTeacher());
        Spark.post("/teacher/course/list", new ListTeacherCourse());


        Spark.post("/teacher/course/remove", new RemoveTeacherCourse());
        Spark.post("/group/teacher/remove", new RemoveGroupTeacher());
        Spark.post("/group/student/remove", new RemoveGroupStudent());



    }
}
