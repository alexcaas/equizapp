package dao;

import util.PasswordHash;
import javax.persistence.EntityManager;
import models.Tgroup;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import ninja.utils.LoggerProvider;

public class GroupDao {

    @Inject
    Provider<EntityManager> entityManagerProvider;
    @Inject
    LoggerProvider logger;
    @Inject
    UserDao userDao;
    @Inject
    ItemDao itemDao;

    public String hashGroupCode(Integer groupcode) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {

        String hashGroupCode = null;

        try {

            hashGroupCode = PasswordHash.createGroupHash(String.valueOf(groupcode));

        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e) {
            logger.get().info(this.toString() + " -- Error calculating hash group code!!");
        }

        return hashGroupCode.toUpperCase();
    }

    @Transactional
    public Tgroup getGroupByGroupCode(Integer groupcode) {

        EntityManager entityManager = entityManagerProvider.get();
        Tgroup group = null;
        try {
            TypedQuery q = entityManager.createNamedQuery("Tgroup.findByGroupcode", Tgroup.class);
            group = (Tgroup) q.setParameter("groupcode", groupcode).getSingleResult();
        } catch (NoResultException e) {
            logger.get().info(this.toString() + " No group found!!");
        }

        return group;
    }

    @Transactional
    public Tgroup getGroupByHashedGroupCode(String groupcodestr) {

        EntityManager entityManager = entityManagerProvider.get();
        Tgroup group = null;
        try {
            TypedQuery q = entityManager.createNamedQuery("Tgroup.findByGroupcodestr", Tgroup.class);
            group = (Tgroup) q.setParameter("groupcodestr", groupcodestr).getSingleResult();
        } catch (NoResultException e) {
            logger.get().info(this.toString() + " No group found!!");
        }
        return group;
    }

    @Transactional
    public Tgroup postNewGroup(String groupname, Short groupitemsnumber) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {

        EntityManager entityManager = entityManagerProvider.get();
        Tgroup group = null;

        try {

            Date date = new Date();
            group = new Tgroup(groupname, groupitemsnumber, date, date);
            String groupcodestr = this.hashGroupCode(group.getGroupcode());
            group.setGroupcodestr(groupcodestr);
            entityManager.persist(group);

        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException | PersistenceException e) {
            logger.get().info(this.toString() + " -- " + groupname + " -- New group failed!!");
        }

        return group;

    }

    @Transactional
    public Boolean deleteGroup(String groupcode) {

        EntityManager entityManager = entityManagerProvider.get();
        Boolean ok = false;

        try {

            Tgroup group = (Tgroup) getGroupByGroupCode(Integer.parseInt(groupcode));
            entityManager.remove(group);
            ok = true;
        } catch (PersistenceException e) {
            logger.get().info(this.toString() + " -- " + groupcode + " -- Delete group failed!!");
        }

        return ok;

    }

}
