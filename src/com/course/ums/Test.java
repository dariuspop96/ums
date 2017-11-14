package com.course.ums;

import com.course.ums.ws.*;
import com.course.ums.ws.user.TeacherAdd;
import com.course.ums.ws.user.StudentAdd;
import com.course.ums.ws.user.Authenticate;
import org.json.JSONObject;
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
        Spark.post("/user/student/add", new StudentAdd());
        Spark.post("/user/teacher/add", new TeacherAdd());
        Spark.post("/course/add", new CourseAdd());
        Spark.post("/semester/add", new AddSemester());
        Spark.post("/group/add", new GroupAdd());
        Spark.post("/teacher/course/add", new AddTeacherCourse());
        Spark.post("/teacher/course/remove", new RemoveTeacherCourse());
        Spark.post("/group/teacher/add", new GroupTeacherAdd());
        Spark.post("/group/teacher/remove", new RemoveGroupTeacher());
        Spark.post("/group/student/add", new GroupStudentAdd());
        Spark.post("/group/student/remove", new RemoveGroupStudent());

        JSONObject test = new JSONObject();
        test.put("id", 0);
        test.put("text", "hello world");

        System.out.println(test);

        test = new JSONObject("{hello: world, bla : blabla}");
        System.out.println(test.get("hello"));
        System.out.println(test.get("bla"));
    }
}
