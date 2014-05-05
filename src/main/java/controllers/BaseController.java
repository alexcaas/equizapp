/**
 */
package controllers;

import com.google.common.base.Optional;
import com.google.inject.Singleton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import models.Tuser;
import models.Tusergroup;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.i18n.Messages;

@Singleton
public class BaseController {

    Messages msg;

    public String getMsg(String key, Context context) {

        Optional<Result> optResult = Optional.absent();
        Object[] messageParameters = {""};

        Optional<String> amessage = msg.get(key, context, optResult, messageParameters);

        return amessage.get();
    }

    public Date parseDate(String date) throws ParseException {

        SimpleDateFormat formatoDeFecha;
        formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssX"); //2014-03-19 14:36:09+01
        Date modifdate;
        modifdate = formatoDeFecha.parse(date);

        return modifdate;
    }

    public Tuser parseUser(Tuser user) {

        List<Tusergroup> usergroups = (List<Tusergroup>) user.getTusergroupCollection();
        if (usergroups != null)  {

            for (Tusergroup usergroup : usergroups) {
                usergroup.getTgroup().setTitemCollection(null);
            }
        }

        return user;
    }

    public Result index() {

        return Results.ok();

    }

}
