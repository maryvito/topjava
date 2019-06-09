package ru.javawebinar.topjava.dao.meals;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MealMapDao implements MealDao{
    public static Map<Integer, Meal> mealsMap = MealsUtil.meals.stream()
            .collect(Collectors.toMap(e -> e.getId(), e -> e));

    @Override
    public void createMeal(Meal meal) {
        Optional<Integer> previousId = mealsMap.keySet().stream().max(Integer::compareTo);
        Meal newMeal = meal.addId(previousId.get());
        mealsMap.put(previousId.get(), newMeal);
    }

    @Override
    public void updateMeal(Meal meal) {
        mealsMap.put(meal.getId(), meal);
    }

    @Override
    public void removeMeal(Integer id) {
        mealsMap.remove(id);
   }

    @Override
    public Meal getMeal(Integer id) {
        return mealsMap.get(id);
    }

    @Override
    public Collection<Meal> getAllMeals() {
        return mealsMap.values();
    }
}

