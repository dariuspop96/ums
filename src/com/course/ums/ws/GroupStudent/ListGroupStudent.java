package com.course.ums.ws.GroupStudent;

import com.course.ums.auth.AuthManager;
import com.course.ums.ws.ListEntityRoute;

/**
 * Created by vh on 11/16/17.
 */
public class ListGroupStudent extends ListEntityRoute {

    @Override
    public String[] getAuthorizedRoles() {
        return new String[] {AuthManager.ROLE_ADMIN, AuthManager.ROLE_STUDENT, AuthManager.ROLE_TEACHER};
    }

    @Override
    public String getTableName() {
        return "group_students";
    }
}
