package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
        meal.setUser(ref);

        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            if(get(meal.getId(), userId) != null)
                return em.merge(meal);
            else return null;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createQuery("delete FROM Meal m " +
                "where m.id = :id AND m.user.id = :userId")
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
         List<Meal> meals = em.createNamedQuery(Meal.BY_ID, Meal.class)
                .setParameter(1, id)
                .setParameter(2, userId)
                .getResultList();
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate,
                                 LocalDateTime endDate,
                                 int userId) {
        TypedQuery<Meal> query = em.createQuery("select m FROM Meal m " +
                "where m.user.id = :userId and " +
                "m.dateTime between :startDate and :endDate " +
                "order by m.dateTime desc", Meal.class);
        List<Meal> meals = query.setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("userId", userId).getResultList();
        return meals;
    }
}