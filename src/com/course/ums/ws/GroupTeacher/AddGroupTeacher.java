package com.course.ums.ws.GroupTeacher;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.EntityRoute;
import org.json.JSONObject;

/**
 * Created by Darius on 11/6/2017.
 */
public class AddGroupTeacher extends EntityRoute {
    @Override
    public String[] getAuthorizedRoles() {
        return new String[]{AuthManager.ROLE_ADMIN};
    }

    @Override
    public int Entity(JSONObject request) throws Exception {
        int id = DBManager.addGroupTeacher( request );

        return id;
    }

}
