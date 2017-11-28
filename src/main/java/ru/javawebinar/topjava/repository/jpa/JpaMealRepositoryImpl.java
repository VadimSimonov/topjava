package ru.javawebinar.topjava.repository.jpa;

import com.sun.org.apache.xml.internal.security.signature.NodeFilter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
        meal.setUser(ref);
        if (meal.isNew())
        {
            em.persist(meal);
        }else {
            Meal currentMeal = em.find(Meal.class, meal.getId());
            if (ref == null || currentMeal.getUser().getId() != userId) {
                return null;
            }
        }
        return em.merge(meal);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        String hql = "delete FROM Meal m WHERE m.id=:id and m.user.id=:user_id ";
        int flag = em.createQuery(hql)
                .setParameter("id", id)
                .setParameter("user_id", userId)
                .executeUpdate();
        return flag == 1;

    }

    @Override
    public Meal get(int id, int userId) {
        User ref = em.getReference(User.class, userId);
        Meal meal=new Meal();
        meal.setUser(ref);
        String hql = "select m FROM Meal m WHERE m.id=:id and m.user.id=:user_id";
        try {
            meal= (Meal) em.createQuery(hql)
                    .setParameter("id", id)
                    .setParameter("user_id", userId)
                    .getSingleResult();
        }catch (NoResultException e)
        {
            return null;
        }
         return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        String hql = "select m FROM Meal m WHERE m.user.id=:user_id ORDER BY m.dateTime desc";
        return (List<Meal>) em.createQuery(hql)
                .setParameter("user_id", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        String hql = "select m FROM Meal m WHERE m.user.id=:user_id " +
                "and m.dateTime BETWEEN :startDate  and :endDate ORDER BY m.dateTime DESC";
        List<Meal> list = em.createQuery(hql)
                .setParameter("user_id", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
        return list;
    }
}