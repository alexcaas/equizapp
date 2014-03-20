package dao;

import javax.persistence.EntityManager;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import java.util.Collection;
import java.util.Date;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import models.Tgroup;
import models.Tuser;
import models.Tusergroup;
import ninja.utils.LoggerProvider;

public class UsergroupDao {

    @Inject
    Provider<EntityManager> entityManagerProvider;
    @Inject
    LoggerProvider logger;
    @Inject
    GroupDao groupDao;
    @Inject
    UserDao userDao;

    @Transactional
    public Tusergroup joinGroup(String useremail, String groupcodestr) {

        EntityManager entityManager = entityManagerProvider.get();
        Tusergroup userGroup = null;
        Tuser user = (Tuser) userDao.getUserByEmail(useremail);
        Tgroup group = (Tgroup) groupDao.getGroupByHashedGroupCode(groupcodestr);

        try {

            if ((user == null) || (group == null)) {
                userGroup = null;
            } else {
                Date date = new Date();
                userGroup = new Tusergroup(group.getGroupcode(), user.getUserid());
                userGroup.setTuser(user);
                userGroup.setTgroup(group);
                userGroup.setUsertreatlastmodif(date);
                Short treat = new Short("0"); // Initial treat for user
                userGroup.setUsertreat(treat);
                entityManager.persist(userGroup);
            }

        } catch (PersistenceException e) {
            logger.get().info(this.toString() + " -- " + useremail + " -- " + groupcodestr + " -- Joinnig group failed!!");
        }

        return userGroup;
    }

    @Transactional
    public Collection<Tusergroup> getUserGroupsByUser(Tuser user) {

        EntityManager entityManager = entityManagerProvider.get();
        Collection<Tusergroup> usergroupCollection = null;

        try {

            TypedQuery q = entityManager.createNamedQuery("Tusergroup.findByUserid", Tusergroup.class);
            usergroupCollection = (Collection<Tusergroup>) q.setParameter("userid", user.getUserid()).getResultList();

        } catch (NoResultException e) {
            logger.get().info(this.toString() + " -- " + user.getUseremail() + " -- No groups by user found!!");
        }

        return usergroupCollection;
    }

}
