package com.course.ums.ws.TeacherCourse;

import com.course.ums.Queries.Queries;
import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.EntityRoute;
import com.course.ums.ws.JSONRoute;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Darius on 11/6/2017.
 */
public class RemoveTeacherCourse extends EntityRoute {

    @Override
    public String[] getAuthorizedRoles() {
        return new String[]{AuthManager.ROLE_ADMIN};
    }

    @Override
    public int Entity(JSONObject request) throws Exception {
        int id = DBManager.removeTeacherCourse(request);

        return id;
    }
}
