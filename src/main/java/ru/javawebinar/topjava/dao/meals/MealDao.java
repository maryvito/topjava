package ru.javawebinar.topjava.dao.meals;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.Collection;
import java.util.List;

public interface MealDao {
    void createMeal(Meal meal);
    void updateMeal(Meal meal);
    void removeMeal(Integer id);
    Meal getMeal(Integer id);
    Collection<Meal> getAllMeals();
}
