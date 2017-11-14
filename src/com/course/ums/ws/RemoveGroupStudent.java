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
public class RemoveGroupStudent extends JSONRoute {

    JSONObject result = new JSONObject();

    @Override
    public JSONObject handleJSONRequest(JSONObject request) throws Exception {

        String token = request.getString("token");
        if(!DBManager.validateToken(token, AuthManager.ROLE_ADMIN)) {
            throw new RuntimeException("Unauthorized!");
        }

                PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement( Queries.SELECT_GROUP_STUDENT, Statement.RETURN_GENERATED_KEYS );
                preparedStatement.setInt( 1, request.getInt( "groups_id" ) );
                preparedStatement.setInt( 2, request.getInt( "students_id" ) );
                preparedStatement.setInt( 3, request.getInt( "semesters_id" ) );

                ResultSet rs = preparedStatement.executeQuery();
                int userId;
                if (!rs.next()) {
                    result.put( "error", "Group, student or semester does not exist!" );
                    return result;
                }else {
                    userId = rs.getInt( "id" );
                }


                PreparedStatement preparedStatement1 = DBManager.getConnection().prepareStatement( Queries.REMOVE_GROUP_STUDENT, Statement.RETURN_GENERATED_KEYS );
                preparedStatement1.setInt(1, request.getInt("groups_id"));
                preparedStatement1.setInt(2, request.getInt("students_id"));
                preparedStatement1.setInt( 3, request.getInt( "semesters_id" ) );
                preparedStatement1.execute();

                result.put( "id", userId );

                return result;



    }
}