package dao;

import javax.persistence.EntityManager;

import models.Tuser;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserDao {
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    
    @Transactional
    public Tuser getUserByEmail(String useremail) {
        
        EntityManager entityManager = entityManagerProvider.get();
        Tuser user;
        try{
            TypedQuery q = entityManager.createNamedQuery("Tuser.findByUseremail", Tuser.class);
            user = (Tuser) q.setParameter("useremail", useremail).getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }

        return user;
    }
    
    @Transactional
    public Boolean isUserAdmin(String useremail) {
        
        EntityManager entityManager = entityManagerProvider.get();
        Tuser user;
        Boolean isAdmin = false;
        try{
            TypedQuery q = entityManager.createNamedQuery("Tuser.findByUseremail", Tuser.class);
            user = (Tuser) q.setParameter("useremail", useremail).getSingleResult();
            isAdmin = user.getUseradmin();
        } catch (NoResultException e) {
            user = null;
        }

        return isAdmin;
    }
    
    @Transactional
    public Boolean isUserAndPasswordValid(String useremail, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        
        Boolean isValid = false;
        Tuser user = (Tuser)this.getUserByEmail(useremail);
        Boolean passOk;
         
        if (user != null){
            passOk = PasswordHash.validatePassword(password, user.getUserpassword());
            if (passOk){
                isValid = true;
            }
        }
        
        return isValid;
    }
    
    @Transactional
    public Tuser postRegisterUser(String useremail, String username, String userlastnames, String userpassword, Boolean useradmin) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {//throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        
        Tuser user = getUserByEmail(useremail);
        
        if (user == null) {
            EntityManager entityManager = entityManagerProvider.get();
            String hashPass = PasswordHash.createHash(userpassword);
            user = new Tuser(useremail, username, userlastnames, hashPass, useradmin);
            entityManager.persist(user);
        }
        
        return user;     
        
    }
    
    @Transactional
    public Tuser postUpdateUser(String useremail, String username, String userlastnames, String userpassword, Boolean useradmin) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        
        Tuser user = getUserByEmail(useremail);
        
        if (user != null) {
            EntityManager entityManager = entityManagerProvider.get();
            user.setUsername(username);
            user.setUserlastname(userlastnames);
            String hashPass = PasswordHash.createHash(userpassword);
            user.setUserpassword(hashPass);
            entityManager.persist(user);
        }
        
        return user;     
        
    }
    

}
