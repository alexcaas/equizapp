/**
 */
package controllers;

import com.google.inject.Inject;
import models.SyncObject;
import ninja.Result;
import ninja.Results;
import com.google.inject.Singleton;
import dao.GroupDao;
import dao.UserDao;
import dao.UsergroupDao;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import models.SyncGroup;
import models.SyncObjectServer;
import models.Tgroup;
import models.Tuser;
import models.Tusergroup;
import ninja.utils.LoggerProvider;

@Singleton
public class ApiSyncController extends BaseController {

    @Inject
    UserDao userDao;
    @Inject
    UsergroupDao userGroupDao;
    @Inject
    GroupDao groupDao;
    @Inject
    LoggerProvider logger;

    public Result postUserSyncJson(SyncObject syncObject) throws ParseException {

        SyncObjectServer syncObjServer = new SyncObjectServer();

        try {
        
            Date lastSyncDateStr = syncObject.getLastsyncdate();
            Date nowDate = new Date();
            List<SyncGroup> syncGroups;
            syncGroups = syncObject.getSyncGroups();
            Boolean all = false;
            Tuser user = null;

            for (int i = 0; i < syncGroups.size(); i++) {
                SyncGroup syncgroup = (SyncGroup) syncGroups.get(i);

                String groupcodestr = syncgroup.getGroupcodestr();
                Tgroup group = groupDao.getGroupByHashedGroupCode(groupcodestr);
                String operation = syncgroup.getOperation();

                // Trait
                short usertrait = syncgroup.getUsertrait();
                if (usertrait > -1) {
                    userGroupDao.updateUserTrait(syncObject.getUseremail(), group.getGroupcode(), usertrait);
                }
                // link or unlink
                if (operation.equals("link")) {
                    userGroupDao.linkGroup(syncObject.getUseremail(), groupcodestr);
                    all = true;
                }
                if (operation.equals("unlink")) {
                    userGroupDao.unlinkGroup(syncObject.getUseremail(), groupcodestr);
                    all = true;
                }
            }

            user = userDao.getUserByEmail(syncObject.getUseremail());

            Collection<Tusergroup> usergroupCollection = user.getTusergroupCollection();

            List<Tusergroup> usergroups = new ArrayList<>();

            for (Tusergroup usergroup : usergroupCollection) {
                Tgroup group = usergroup.getTgroup();
                usergroups.add(usergroup);
            }

            syncObjServer.setUseremail(syncObject.getUseremail());
            syncObjServer.setLastsyncdate(nowDate);
            syncObjServer.setSyncGroups(usergroups);
            logger.get().error("AAAAA PASO 4");

        } catch (Exception e) {
            logger.get().info(this.toString() + " -- Error synchronization!!");
        }

        return Results.json().render(syncObjServer);
    }

}
