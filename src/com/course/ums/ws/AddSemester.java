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
public class AddSemester extends JSONRoute {

    JSONObject result = new JSONObject();

    @Override
    public JSONObject handleJSONRequest(JSONObject request) throws Exception {
        String token = request.getString("token");
        if(!DBManager.validateToken(token, AuthManager.ROLE_ADMIN)) {
            throw new RuntimeException("Unauthorized!");
        }

        PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement( Queries.INSERT_SEMESTER, Statement.RETURN_GENERATED_KEYS );
        preparedStatement.setInt( 1, request.getInt( "year" ) );
        preparedStatement.setInt( 2, request.getInt( "index" ) );

        preparedStatement.execute();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        result.put( "id", rs.getInt( 1 ) );

        return result;
    }

}
