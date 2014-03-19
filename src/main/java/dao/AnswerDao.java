package dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import models.Tanswer;
import models.Titem;
import ninja.utils.LoggerProvider;

public class AnswerDao {
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    @Inject
    LoggerProvider logger;
    
//    @Transactional
//    public Tanswer getAnswerById(Long answerid) {
//        
//        EntityManager entityManager = entityManagerProvider.get();
//        Tanswer answer = null;
//        
//        try{
//            TypedQuery q = entityManager.createNamedQuery("Tanswer.findByAnswerid", Tanswer.class);
//            answer = (Tanswer) q.setParameter("answerid", answerid).getSingleResult();
//        } catch (NoResultException e) {
//            logger.get().info(this.toString() + " No answer found!!");
//        }
//
//        return answer;
//    }
//    
//    @Transactional
//    public Collection<Tanswer> getAnswersByItemId(Long itemid) {
//        
//        Collection <Tanswer> answers= null;
//        try{
//            EntityManager entityManager = entityManagerProvider.get();
//            TypedQuery q = entityManager.createNamedQuery("Tanswer.findByItemid", Tanswer.class);
//            answers = (List) q.setParameter("itemid", itemid).getResultList();
//        } catch (NoResultException e) {
//            logger.get().info(this.toString() + " No answers found!!");
//        }
//
//        return answers;
//    }
//    
//    @Transactional
//    public Tanswer postNewAnswer(Titem item, String answerstring, boolean answercorrect) {
//        
//        EntityManager entityManager = entityManagerProvider.get();
//        Tanswer answer = null;
//                
//        try {
//            answer = new Tanswer(item, answerstring, answercorrect);
//            entityManager.persist(answer);
//        } catch (PersistenceException e) {
//            logger.get().info(this.toString() + " -- " + answerstring + " -- New answer failed!!");
//        }
//        
//        return answer;      
//        
//    }
//    
//    @Transactional
//    public Tanswer postUpdateAnswer(Long answerid, String answerstring, boolean answercorrect) {
//        
//        EntityManager entityManager = entityManagerProvider.get();
//        Tanswer answer = null;
//                
//        //try {
//            answer = this.getAnswerById(answerid);
//            answer.setAnswerstring(answerstring);
//            answer.setAnswercorrect(answercorrect);
//            entityManager.persist(answer);
//        
//       // } catch (PersistenceException e) {
//            logger.get().info(this.toString() + " -- " + answerstring + " -- Update answer failed!!");
//       // }
//        
//        return answer;      
//        
//    }
//    
//    @Transactional
//    public Boolean deleteAnswer(Long answerid) {
//        
//        EntityManager entityManager = entityManagerProvider.get();
//        Boolean ok = false;
//        
//        try {
//            Tanswer answer = (Tanswer) getAnswerById(answerid); 
//            entityManager.remove(answer);
//            ok = true;
//        } catch (PersistenceException e) {
//            logger.get().info(this.toString() + " -- " + answerid + " -- Delete answer failed!!");
//        }
//    
//        return ok;
//    }
        
}
    
