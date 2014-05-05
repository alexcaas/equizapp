/**
 */
package controllers;

import com.google.inject.Inject;
import models.SyncObject;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;
import dao.UserDao;
import dao.UsergroupDao;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import models.SyncGroup;
import models.Tuser;
import models.Tusergroup;

@Singleton
public class ApiSyncController extends BaseController {

    @Inject
    UserDao userDao;
    @Inject
    UsergroupDao userGroupDao;

    public Result postUserSyncJson(SyncObject syncObject) throws ParseException {
 

//        Date lastSyncDateStr = syncObject.getLastsyncdate();
//        //Date lastSyncDate = parseDate(lastSyncDateStr);
//        Date ceroDate = new Date();
//        ceroDate.setTime(0);
//        Date nowDate = new Date();

        Tuser user = userDao.getUserByEmail(syncObject.getUseremail());
        Collection<Tusergroup> usergroupCollection = user.getTusergroupCollection();

        List<SyncGroup> syncGroups;
        syncGroups = syncObject.getSyncGroups();
        
        for (int i=0; i<syncGroups.size(); i++){
            SyncGroup syncgroup = (SyncGroup) syncGroups.get(i);
            int groupcode = syncgroup.getGroupcode();
            short usertrait = syncgroup.getUsertrait();
            userGroupDao.updateUserTrait(syncObject.getUseremail(), groupcode, usertrait); 
        }
        
//        Boolean exists = false;
//        
//        for (Tusergroup usergroup : usergroupCollection) {
//            
//            for (int i=0; i<syncGroups.size(); i++){
//               SyncGroup syncgroup = (SyncGroup) syncGroups.get(i);
//               if (usergroup.getTgroup().getGroupcode() == syncgroup.getGroupcode()){
//                    exists = true;
//                }
//            }
//            if (!exists){
//                usergroupCollection.remove(usergroup);
//            } else {
//            exists = false;}
//        }

//        if (lastSyncDate.equals(ceroDate)){
//            
//        }
        return Results.json().render(usergroupCollection);
    }

//    public Result postUserChangesJson(@Param("useremail") String useremail,
//            @Param("lastmodif") String lastmodif,
//            Context context) throws ParseException {
//
//        Tuser user = userDao.getChanges(useremail, this.parseDate(lastmodif));
//        
//        if (user == null) {
//            context.getFlashScope().error("postuserchangesfail");
//        } else {
//            user.setUserpassword(""); // No password sent to client
//        }
//
//        return Results.json().render(this.parseUser(user));
//    }
//
//    public Result postGroupChangesJson(@Param("groupcode") String groupcode,
//            @Param("lastmodif") String lastmodif,
//            Context context) throws ParseException {
//
//        Tgroup group = groupDao.getChanges(groupcode, this.parseDate(lastmodif));
//        
//        if (group == null) {
//            context.getFlashScope().error("postgroupchangesfail");
//        } 
//        
//        return Results.json().render(group);        
//        
//    }
//    
//    public Result postUpdateUserTrait(@Param("useremail") String useremail,
//            @Param("groupcode") String groupcode,
//            @Param("usertrait") String usertrait,
//            Context context) throws ParseException {
//        
//        Tuser user = userGroupDao.updateUserTrait(useremail, Integer.parseInt(groupcode), Short.parseShort(usertrait));
//        
//        if (user == null) {
//            context.getFlashScope().error("postupdateusertraitfail");
//            return Results.text().renderRaw(this.getMsg("user.postUpdateUserTraitFail", context));
//        } else {
//            user.setUserpassword(""); // No password sent to client
//            return Results.json().render(this.parseUser(user));
//        }
//    }
}
