package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.User;

public class UserDB {

    public User get(String email) {
        EntityManager em = UtilDB.getEmFactory().createEntityManager();

        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }

    public List<User> getAll() throws Exception {
        EntityManager em = UtilDB.getEmFactory().createEntityManager();

        try {
            List<User> userList = em.createNamedQuery("User.findAll", User.class).getResultList();
            return userList;
        } finally {
            em.close();
        }

    }

    public void delete(User user) throws Exception {
        EntityManager em = UtilDB.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(user));
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void insert(User user) throws Exception {
        EntityManager em = UtilDB.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(user);
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(User user) throws Exception {
        EntityManager em = UtilDB.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

}
