package dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import models.Tgroup;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class GroupDao {
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    
    public String hashGroupCode(Integer groupcode) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
            
        String hashGroupCode = PasswordHash.createGroupHash(String.valueOf(groupcode));

        return hashGroupCode.toUpperCase();
    }
    
    @Transactional
    public Tgroup getGroupByGroupCode(Integer groupcode) {
        
        EntityManager entityManager = entityManagerProvider.get();
        Tgroup group;
        try{
            TypedQuery q = entityManager.createNamedQuery("Tgroup.findByGroupcode", Tgroup.class);
            group = (Tgroup) q.setParameter("groupcode", groupcode).getSingleResult();
        } catch (NoResultException e) {
            group = null;
        }

        return group;
    }
    
    public Tgroup getGroupByHashedGroupCode(String groupcodestr) {
        
        EntityManager entityManager = entityManagerProvider.get();
        Tgroup group;
        try{
            TypedQuery q = entityManager.createNamedQuery("Tgroup.findByGroupcodestr", Tgroup.class);
            group = (Tgroup) q.setParameter("groupcodestr", groupcodestr).getSingleResult();
        } catch (NoResultException e) {
            group = null;
        }

        return group;
    }
    
    @Transactional
    public Tgroup postNewGroup(String groupname, Short groupitemsnumber, Date groupdatecreation) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        
        EntityManager entityManager = entityManagerProvider.get();
        
        Tgroup group = new Tgroup(groupname, groupitemsnumber, groupdatecreation);
        entityManager.persist(group);
        
        String groupcodestr = this.hashGroupCode(group.getGroupcode());
        
        group.setGroupcodestr(groupcodestr);
        entityManager.persist(group);
        
        return group;      
        
    }
    
    @Transactional
    public Tgroup postDeleteGroup(Integer groupcode) {
        
        EntityManager entityManager = entityManagerProvider.get();
        
        Tgroup group = (Tgroup)getGroupByGroupCode(groupcode);
        try{ 
            entityManager.remove(group);
        } catch (NoResultException e) {
            group = null;
        }
        
        return group;      
        
    }

}
