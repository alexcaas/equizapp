/**
 */
package controllers;

import ninja.Result;
import ninja.Results;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Tgroup;
import dao.GroupDao;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import ninja.Context;
import ninja.i18n.Messages;
import ninja.params.Param;
import ninja.params.PathParam;

@Singleton
public class ApiGroupController extends BaseController {

    @Inject
    ApiGroupController(Messages msg) {
        this.msg = msg;
    }

    @Inject
    GroupDao groupDao;

    public Result getGroupByGroupCode(@PathParam("groupcode") String groupcode, Context context) {

        Tgroup group = (Tgroup) groupDao.getGroupByGroupCode(Integer.parseInt(groupcode));

        if (group == null) {
            context.getFlashScope().error("getgroupbygroupcodefail");
            return Results.text().renderRaw(this.getMsg("group.getGroupByGroupCodeFail", context));
        } else {
            return Results.json().render(group);
        }

    }
    
    public Result getGroupByHashedGroupCode(@PathParam("grouphashedcode") String grouphashedcode, Context context) {

        Tgroup group = (Tgroup) groupDao.getGroupByHashedGroupCode(grouphashedcode);

        if (group == null) {
            context.getFlashScope().error("getgroupbyhashedgroupcodefail");
            return Results.text().renderRaw(this.getMsg("group.getGroupByHashedGroupCodeFail", context));
        } else {
            return Results.json().render(group);
        }

    }

    public Result postNewGroupJson(@Param("groupname") String groupname,
            @Param("groupitemsnumber") String groupitemsnumber,
            Context context) throws ParseException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {

        Short grpItemsNumber = Short.parseShort(groupitemsnumber);

        Tgroup group = groupDao.postNewGroup(groupname, grpItemsNumber);

        if (group != null) {
            context.getFlashScope().success("postnewgroupok");
            return Results.json().render(group);
        } else {
            context.getFlashScope().error("postnewgroupfail");
            return Results.text().renderRaw(this.getMsg("group.postNewGroupFail", context));
        }
    }

    public Result deleteGroup(@PathParam("codestr") String groupcodestr,
            Context context) {

        Boolean ok = groupDao.deleteGroup(groupcodestr);

        if (ok) {
            context.getFlashScope().success("deletegroupok");
            return Results.text().renderRaw(this.getMsg("group.deleteGroupOk", context));
        } else {
            context.getFlashScope().success("deletegroupfail");
            return Results.text().renderRaw(this.getMsg("group.deleteGroupFail", context));
        }

    }

}
