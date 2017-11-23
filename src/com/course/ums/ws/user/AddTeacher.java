package com.course.ums.ws.user;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.EntityRoute;
import org.json.JSONObject;

import java.sql.PreparedStatement;

/**
 * Created by vh on 11/9/17.
 */
public class AddTeacher extends EntityRoute {

    @Override
    public String[] getAuthorizedRoles() {
        return new String[]{AuthManager.ROLE_ADMIN};
    }

    @Override
    public int Entity(JSONObject request) throws Exception {
        int id = DBManager.addUser(request);

        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO teachers(id, level) VALUES(?, ?)");
        ps.setInt(1, id);
        ps.setInt(2, request.getInt("level"));
        ps.execute();

        return id;
    }

}
