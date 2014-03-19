package dao;

import util.PasswordHash;
import javax.persistence.EntityManager;
import models.Tuser;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import ninja.utils.LoggerProvider;

public class UserDao {
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    @Inject
    LoggerProvider logger;
    @Inject
    UsergroupDao userGroupDao;
    
    @Transactional
    public Tuser getUserByEmail(String useremail) {
        
        EntityManager entityManager = entityManagerProvider.get();
        Tuser user = null;
        
        try{
            
            TypedQuery q = entityManager.createNamedQuery("Tuser.findByUseremail", Tuser.class);
            user = (Tuser) q.setParameter("useremail", useremail).getSingleResult();
            
        } catch (NoResultException e) {
            logger.get().info(this.toString() + " No user found!!");
        }

        return user;
    }
    
    @Transactional
    public Boolean isUserAndPasswordValid(String useremail, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        
        Boolean isValid = false;
        Boolean passOk;
        
        try{
            Tuser user = (Tuser)this.getUserByEmail(useremail);
            if (user != null){
                passOk = PasswordHash.validatePassword(password, user.getUserpassword());
                if (passOk){
                    isValid = true;
                }
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException | NoResultException e) {
            logger.get().info(this.toString() + " No user found or error validating password!!");
        }
        
        return isValid;
    }
    
    @Transactional
    public Tuser postRegisterUser(String useremail, String username, String userlastnames, String userpassword, Boolean useradmin) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        
        Tuser user = getUserByEmail(useremail);
        
        try {
            if (user == null) {
                EntityManager entityManager = entityManagerProvider.get();
                String hashPass = PasswordHash.createHash(userpassword);
                user = new Tuser(useremail, username, userlastnames, hashPass, useradmin);
                Date lastmodif = new Date();
                user.setUserlastmodif(lastmodif);
                entityManager.persist(user);
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException | EntityExistsException e) {
            logger.get().info(this.toString() + " -- " + useremail + " -- User registration failed!!");
        }
        
        return user;     
        
    }
    
    @Transactional
    public Tuser postUpdateUser(String useremail, String username, String userlastnames, String userpassword) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        
        Tuser user = getUserByEmail(useremail);
        
        try {
            
            if (user != null) {
                EntityManager entityManager = entityManagerProvider.get();
                if (!username.isEmpty()) {
                    user.setUsername(username);
                }
                if (!userlastnames.isEmpty()){
                    user.setUserlastname(userlastnames);
                }
                if (!userpassword.isEmpty()){
                    String hashPass = PasswordHash.createHash(userpassword);
                    user.setUserpassword(hashPass);
                }
                
                Date lastmodif = new Date();
                user.setUserlastmodif(lastmodif);

                entityManager.persist(user);
            }
        
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException | EntityNotFoundException e) {
            logger.get().info(this.toString() + " -- " + useremail + " -- User update failed!!");
        }
        
        return user;     
        
    }
    

}
