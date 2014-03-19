package dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import models.Titem;
import ninja.utils.LoggerProvider;


public class ItemDao {
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    @Inject
    LoggerProvider logger;
    @Inject
    AnswerDao answerDao;
    @Inject
    GroupDao groupDao;
    
    
    @Transactional
    public Titem getItemById(Long itemid) {
        
        EntityManager entityManager = entityManagerProvider.get();
        Titem item = null;
        
        try{
            TypedQuery q = entityManager.createNamedQuery("Titem.findByItemid", Titem.class);
            item = (Titem) q.setParameter("itemid", itemid).getSingleResult();
        } catch (NoResultException e) {
            logger.get().info(this.toString() + " No item found!!");
        }

        return item;
    }
    
//    @Transactional
//    public Collection<Titem> getItemsByGroupCode(Integer groupcode) {
//        
//        Collection <Titem> items= null;
//        try{
//            EntityManager entityManager = entityManagerProvider.get();
//            TypedQuery q = entityManager.createNamedQuery("Titem.findByGroupcode", Titem.class);
//            items = (List) q.setParameter("groupcode", groupcode).getResultList();
//            for (Titem item : items) {
//                item.setTanswerCollection(answerDao.getAnswersByItemId(item.getItemid()));
//            }
//        } catch (NoResultException e) {
//            logger.get().info(this.toString() + " No items found!!");
//        }
//
//        return items;
//    }
    
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
    
//    @Transactional
//    public Titem postUpdateItem(Long itemid, String itemstring, short itemdifficulty) {
//        
//        EntityManager entityManager = entityManagerProvider.get();
//        Titem item = null;
//                
//        try {
//            
//            item = this.getItemById(itemid);       
//            item.setItemstring(itemstring);
//            item.setItemdifficulty(itemdifficulty);  
//            Date lastmodif = new Date();
//            item.setItemlastmodif(lastmodif);  
//            entityManager.persist(item);
//        
//        } catch (PersistenceException e) {
//            logger.get().info(this.toString() + " -- " + itemstring + " -- Update item failed!!");
//        }
//        
//        return item;      
//        
//    }
    
    @Transactional
    public Boolean deleteItem(Long itemid) {
        
        EntityManager entityManager = entityManagerProvider.get();
        Boolean ok = false;
        
        try {
            Titem item = (Titem) getItemById(itemid); 
            entityManager.remove(item);
            ok = true;
        } catch (PersistenceException e) {
            logger.get().info(this.toString() + " -- " + itemid + " -- Delete item failed!!");
        }
        
        return ok;      
        
    }

}
