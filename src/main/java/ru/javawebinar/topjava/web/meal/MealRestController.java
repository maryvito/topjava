package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealTo> getAll() {
        log.info("getAll");
        List<Meal> meals = service.getAll(authUserId());
        return MealsUtil.getWithExcess(meals, MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealTo> getAllFiltered(LocalTime startTime, LocalTime endTime,
                                       LocalDate startDate, LocalDate endDate) {
        log.info("getAllFiltered");
        List<Meal> meals = service.getAll(authUserId());
        return MealsUtil.getFilteredWithExcess(meals, MealsUtil.DEFAULT_CALORIES_PER_DAY,
                startTime, endTime, startDate, endDate);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        /*Meal meal = */return service.get(authUserId(), id);
//        return MealsUtil.createWithExcess(meal,
//                meal.getCalories() > MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(authUserId(), id);
    }

    public void save(Meal meal) {
        log.info("save {} with id={}", meal, meal.getId());
        service.save(authUserId(), meal);
    }




}