package dao;

import javax.persistence.EntityManager;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import java.util.Date;
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
    public Tuser linkGroup(String useremail, String groupcodestr) {

        EntityManager entityManager = entityManagerProvider.get();
        Tusergroup userGroup;
        Tuser user = (Tuser) userDao.getUserByEmail(useremail);
        Tgroup group = (Tgroup) groupDao.getGroupByHashedGroupCode(groupcodestr);

        try {

            if ((user != null) && (group != null)) {
                Date date = new Date();
                userGroup = new Tusergroup(group.getGroupcode(), user.getUserid());
                userGroup.setTuser(user);
                userGroup.setTgroup(group);
                userGroup.setUsertraitlastmodif(date);
                Short trait = new Short("0"); // Initial trait for user
                userGroup.setUsertrait(trait);
                entityManager.persist(userGroup);
            }

        } catch (PersistenceException e) {
            logger.get().info(this.toString() + " -- " + useremail + " -- " + groupcodestr + " -- Linking group failed!!");
            user = null;
        }

        return user;
    }

    @Transactional
    public Tuser unlinkGroup(String useremail, String groupcodestr) {

        EntityManager entityManager = entityManagerProvider.get();

        Tgroup group = (Tgroup) groupDao.getGroupByHashedGroupCode(groupcodestr);
        Tuser user = (Tuser) userDao.getUserByEmail(useremail);

        Tusergroup userGroup;

        try {

            userGroup = getUserGroup(useremail, group.getGroupcode());

            user.getTusergroupCollection().remove(userGroup);
            group.getTusergroupCollection().remove(userGroup);

            entityManager.persist(user);
            entityManager.persist(group);
            entityManager.remove(userGroup);

        } catch (PersistenceException e) {
            logger.get().info(this.toString() + " -- " + useremail + " -- " + groupcodestr + " -- UnLinking group failed!!");
            user = null;
        }

        return user;
    }

    @Transactional
    public Tuser updateUserTrait(String useremail, Integer groupcode, Short usertrait) {

        EntityManager entityManager = entityManagerProvider.get();

        Tusergroup userGroup;
        Tuser user = null;

        try {

            userGroup = getUserGroup(useremail, groupcode);

            if (userGroup != null) {
                userGroup.setUsertrait(usertrait);
                entityManager.persist(userGroup);

                user = userGroup.getTuser();
            }

        } catch (PersistenceException e) {
            logger.get().info(this.toString() + " -- " + useremail + " -- " + groupcode + " -- Update user trait failed!!");
            user = null;
        }

        return user;

    }

    @Transactional
    public Tusergroup getUserGroup(String useremail, Integer groupcode) {

        EntityManager entityManager = entityManagerProvider.get();

        Tuser user = (Tuser) userDao.getUserByEmail(useremail);
        Tgroup group = (Tgroup) groupDao.getGroupByGroupCode(groupcode);

        Tusergroup userGroup = null;

        try {

            TypedQuery q = entityManager.createNamedQuery("Tusergroup.findByUseridAndGroupcode", Tusergroup.class);
            q.setParameter("userid", user.getUserid());
            q.setParameter("groupcode", group.getGroupcode());
            userGroup = (Tusergroup) q.getSingleResult();

        } catch (PersistenceException e) {
            logger.get().info(this.toString() + " -- " + user.getUseremail() + " -- " + group.getGroupcode() + " -- Get UserGroup failed!!");
        }

        return userGroup;
    }

//    @Transactional
//    public Collection<Tusergroup> getGroupsByUser(Tuser user) {
//
//        EntityManager entityManager = entityManagerProvider.get();
//        Collection<Tusergroup> usergroupCollection = null;
//
//        try {
//
//            TypedQuery q = entityManager.createNamedQuery("Tusergroup.findByUserid", Tusergroup.class);
//            usergroupCollection = (Collection<Tusergroup>) q.setParameter("userid", user.getUserid()).getResultList();
//
//        } catch (NoResultException e) {
//            logger.get().info(this.toString() + " -- " + user.getUseremail() + " -- No groups by user found!!");
//        }
//
//        return usergroupCollection;
//    }
}
