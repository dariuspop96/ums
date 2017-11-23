package com.course.ums.ws.group;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.EntityRoute;
import org.json.JSONObject;

/**
 * Created by vh on 11/16/17.
 */
public class AddGroup extends EntityRoute {

    @Override
    public String[] getAuthorizedRoles() {
        return new String[]{AuthManager.ROLE_ADMIN};
    }

    @Override
    public int Entity(JSONObject request) throws Exception {
        int id = DBManager.addGroup(request);

        return id;
    }
}
