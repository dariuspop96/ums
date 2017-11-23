package com.course.ums.ws.user;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.EntityRoute;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vh on 11/9/17.
 */
public class AddStudent extends EntityRoute {

    @Override
    public String[] getAuthorizedRoles() {
        return new String[] {AuthManager.ROLE_ADMIN};
    }

    @Override
    public int Entity(JSONObject request) throws Exception {
        int id = DBManager.addUser(request);

        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO students(id, gender, birth_date) VALUES(?, ?, ?)");
        ps.setInt(1, id);
        ps.setString(2, request.getString("gender"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = df.parse(request.getString("birth_date"));
        ps.setDate(3, new java.sql.Date(birthDate.getTime()));
        ps.execute();

        return id;
    }
}
