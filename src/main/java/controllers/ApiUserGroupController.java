/**
 */
package controllers;

import ninja.Result;
import ninja.Results;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.GroupDao;
import dao.UsergroupDao;
import models.Tuser;
import models.Tusergroup;
import ninja.Context;
import ninja.i18n.Messages;
import ninja.params.Param;

@Singleton
public class ApiUserGroupController extends BaseController {

    @Inject
    ApiUserGroupController(Messages msg) {
        this.msg = msg;
    }

    @Inject
    UsergroupDao usergroupDao;
    @Inject
    GroupDao groupDao;

    public Result postJoinGroupJson(@Param("useremail") String useremail,
            @Param("codestr") String groupcodestr,
            Context context) {

        Tusergroup usergroup = usergroupDao.joinGroup(useremail, groupcodestr);

        if (usergroup != null) {
            Tuser user = usergroup.getTuser();
            user.setUserpassword(""); // No password sent to client
            context.getFlashScope().success("postjoingroupok");
            return Results.json().render(user);
        } else {
            context.getFlashScope().error("postjoingroupfail");
            return Results.text().renderRaw(this.getMsg("usergroup.postJoinGroupFail", context));
        }
    }

}
