package dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import models.Tgroup;
import models.Titem;
import ninja.utils.LoggerProvider;

public class ItemDao {

    @Inject
    Provider<EntityManager> entityManagerProvider;
    @Inject
    LoggerProvider logger;
    @Inject
    GroupDao groupDao;

    @Transactional
    public Titem getItemById(Long itemid) {

        EntityManager entityManager = entityManagerProvider.get();
        Titem item = null;

        try {
            TypedQuery q = entityManager.createNamedQuery("Titem.findByItemid", Titem.class);
            item = (Titem) q.setParameter("itemid", itemid).getSingleResult();
        } catch (NoResultException e) {
            logger.get().info(this.toString() + " No item found!!");
        }

        return item;
    }

    @Transactional
    public Titem postNewItem(Titem item) {

        EntityManager entityManager = entityManagerProvider.get();

        try {

            Date lastmodif = new Date();
            Tgroup group = item.getGroup();
            group.setGrouplastmodif(lastmodif);
            entityManager.merge(group);
            entityManager.persist(item);

        } catch (PersistenceException e) {
            logger.get().info(this.toString() + " -- " + item.getItemstring() + " -- New item failed!!");
        }

        return item;

    }

    @Transactional
    public Titem postUpdateItem(Titem item) {

        EntityManager entityManager = entityManagerProvider.get();

        try {

            Date lastmodif = new Date();
            Tgroup group = item.getGroup();
            group.setGrouplastmodif(lastmodif);
            entityManager.merge(group);
            entityManager.merge(item);

        } catch (PersistenceException e) {
            logger.get().info(this.toString() + " -- " + item.getItemstring() + " -- Update item failed!!");
        }

        return item;

    }

    @Transactional
    public Boolean deleteItem(Long itemid) {

        EntityManager entityManager = entityManagerProvider.get();
        Boolean ok = false;

        try { 
            
            Titem item = this.getItemById(itemid);
            Date lastmodif = new Date();
            Tgroup group = item.getGroup();
            group.setGrouplastmodif(lastmodif);
            entityManager.persist(group);
          
            Query q = entityManager.createQuery("DELETE FROM Titem t WHERE itemid = :itemid");  
            q.setParameter("itemid", itemid);  

            q.executeUpdate(); 

            ok = true;
            
        } catch (PersistenceException e) {
            logger.get().info(this.toString() + " -- " + itemid + " -- Delete item failed!!");
        }

        return ok;

    }

}
