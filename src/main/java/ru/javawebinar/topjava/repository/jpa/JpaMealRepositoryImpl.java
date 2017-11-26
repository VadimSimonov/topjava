package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
        return null;
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
         meal= (Meal) em.createQuery(hql)
                .setParameter("id", id)
                .setParameter("user_id", userId)
                .getSingleResult();
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        String hql = "select m FROM Meal m WHERE m.user.id=:user_id ORDER BY m.dateTime desc ";
        return (List<Meal>) em.createQuery(hql)
                .setParameter("user_id", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}