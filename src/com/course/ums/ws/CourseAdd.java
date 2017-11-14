package com.course.ums.ws;

import com.course.ums.Queries.Queries;
import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Darius on 11/6/2017.
 */
public class CourseAdd extends JSONRoute {

    int lastId;
    JSONObject result = new JSONObject();

    @Override
    public JSONObject handleJSONRequest(JSONObject request) throws Exception {

        String token = request.getString("token");
        if(!DBManager.validateToken(token, AuthManager.ROLE_ADMIN)) {
            throw new RuntimeException("Unauthorized!");
        }

        PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement( Queries.INSERT_COURSE, Statement.RETURN_GENERATED_KEYS );
        preparedStatement.setString( 1, request.getString( "course" ) );

        preparedStatement.execute();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        result.put( "id", rs.getInt( 1 ) );

            return result;
    }
}
