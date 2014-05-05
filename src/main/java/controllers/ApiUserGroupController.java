/**
 */
package controllers;

import ninja.Result;
import ninja.Results;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.GroupDao;
import dao.UsergroupDao;
import models.Tgroup;
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

    public Result postLinkGroupJson(@Param("useremail") String useremail,
            @Param("codestr") String groupcodestr,
            Context context) {

            Tgroup group = (Tgroup) groupDao.getGroupByHashedGroupCode(groupcodestr);
            if (group != null) {
                Tusergroup userGroup = usergroupDao.getUserGroup(useremail, group.getGroupcode());

                if (userGroup != null) {
                    context.getFlashScope().error("postlinkgroupfail");
                    return Results.text().renderRaw(this.getMsg("usergroup.postLinkGroupAlreadyFail", context));
                }

                Tuser user = usergroupDao.linkGroup(useremail, groupcodestr);

                if (user != null) {
                    context.getFlashScope().success("postlinkgroupok");
                    return Results.text().renderRaw(this.getMsg("usergroup.postLinkGroupOk", context));
                } else {
                    context.getFlashScope().error("postlinkgroupfail");
                    return Results.text().renderRaw(this.getMsg("usergroup.postLinkGroupFail", context));
                }
            } else {
                context.getFlashScope().error("postlinkgroupfail");
                return Results.text().renderRaw(this.getMsg("usergroup.postLinkGroupFail", context));            
            }
    }

    public Result postUnLinkGroupJson(@Param("useremail") String useremail,
            @Param("codestr") String groupcodestr,
            Context context) {

        Tuser user = usergroupDao.unlinkGroup(useremail, groupcodestr);

        if (user != null) {
            context.getFlashScope().success("postunlinkgroupok");
            return Results.text().renderRaw(this.getMsg("usergroup.postUnLinkGroupOk", context));
        } else {
            context.getFlashScope().error("postunlinkgroupfail");
            return Results.text().renderRaw(this.getMsg("usergroup.postUnLinkGroupFail", context));
        }
    }

}
