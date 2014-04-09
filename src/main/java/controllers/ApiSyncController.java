/**
 */
package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.GroupDao;
import dao.UserDao;
import java.text.ParseException;
import models.Tgroup;
import models.Tuser;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.i18n.Messages;
import ninja.params.Param;

@Singleton
public class ApiSyncController extends BaseController {

    @Inject
    ApiSyncController(Messages msg) {
        this.msg = msg;
    }

    @Inject
    UserDao userDao;
    @Inject
    GroupDao groupDao;

    public Result postUserChangesJson(@Param("useremail") String useremail,
            @Param("lastmodif") String lastmodif,
            Context context) throws ParseException {

        Tuser user = userDao.getChanges(useremail, this.parseDate(lastmodif));
        user.setUserpassword(""); // No password for client

        return Results.json().render(user);
    }

    public Result postGroupChangesJson(@Param("groupcode") String groupcode,
            @Param("lastmodif") String lastmodif,
            Context context) throws ParseException {

        Tgroup group = groupDao.getChanges(groupcode, this.parseDate(lastmodif));

        return Results.json().render(group);
    }

}
