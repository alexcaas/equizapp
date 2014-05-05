package dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import java.util.Collection;
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
            item.setItemlastmodif(lastmodif);
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
            item.setItemlastmodif(lastmodif);
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
          
            Query q = entityManager.createQuery("DELETE FROM Titem t WHERE itemid = :itemid");  
            q.setParameter("itemid", itemid);  

            q.executeUpdate(); 

            ok = true;
            
        } catch (PersistenceException e) {
            logger.get().info(this.toString() + " -- " + itemid + " -- Delete item failed!!");
        }

        return ok;

    }

//    @Transactional
//    public Collection<Titem> getChanges(Tgroup group, Date lastmodif) {
//
//        Collection<Titem> itemsCollection = group.getTitemCollection();
//
//        try {
//
//            for (Titem item : itemsCollection) {
//                if (item.getItemlastmodif().compareTo(lastmodif) > 0) {
//                    item.setChanges(Boolean.TRUE);
//                } else {
//                    item.setChanges(Boolean.FALSE);
//                }
//            }
//
//        } catch (NoResultException e) {
//            logger.get().info(this.toString() + " No items by group found!!");
//        }
//
//        return itemsCollection;
//    }

}
