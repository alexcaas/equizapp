package dao;

import javax.persistence.EntityManager;

import models.Tuser;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import javax.persistence.TypedQuery;

public class UserDao {
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    
    @Transactional
    public Tuser getUser() {
        
        EntityManager entityManager = entityManagerProvider.get();
            
        TypedQuery q = entityManager.createNamedQuery("Tuser.findAll", Tuser.class);
        Tuser user = (Tuser) q.getSingleResult();   

        return user;
    }

}
