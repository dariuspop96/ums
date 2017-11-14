package com.course.ums.ws.user;

import com.course.ums.Queries.Queries;
import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
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
public class TeacherAdd extends JSONRoute {

    @Override
    public JSONObject handleJSONRequest(JSONObject request) throws Exception {
        String token = request.getString("token");
        if(!DBManager.validateToken(token, AuthManager.ROLE_ADMIN)) {
            throw new RuntimeException("Unauthorized!");
        }

        int id = DBManager.addUser(request);

        PreparedStatement ps = DBManager.getConnection().prepareStatement(Queries.INSERT_TEACHER);
        ps.setInt(1, id);
        ps.setInt(2, request.getInt("level"));
        ps.execute();

        JSONObject result = new JSONObject();
        result.put("id", id);

        return result;    }
}
