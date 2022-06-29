package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import models.Category;

public class CategoryDB {

    public List<Category> getAll() throws Exception {
        EntityManager em = UtilDB.getEmFactory().createEntityManager();

        try {
            List<Category> categoryList = em.createNamedQuery("Category.findAll", Category.class).getResultList();
            return categoryList;
        } finally {
            em.close();
        }

    }

    public Category get(int categoryId) throws Exception {
        EntityManager em = UtilDB.getEmFactory().createEntityManager();

        try {
            Category category = em.find(Category.class, categoryId);
            return category;
        } finally {
            em.close();
        }
    }
}
