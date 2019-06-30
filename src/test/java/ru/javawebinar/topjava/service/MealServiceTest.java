package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1, ADMIN_ID);
    }

    @Test
    public void get() {
        Meal meal = service.get(1, USER_ID);
        assertMatch(meal, BREAKFAST);
    }

    @Test
    public void delete() {
        service.delete(1, USER_ID);
        assertMatch(service.getAll(USER_ID), BREAKFAST_01_07, DINNER, LUNCH);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(1, ADMIN_ID);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> all = service.getBetweenDates(
                LocalDate.parse("2019-06-30", DATE_FORMATTER),
                LocalDate.parse("2019-06-30", DATE_FORMATTER),
                USER_ID);
        assertMatch(all, DINNER, LUNCH, BREAKFAST);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> all = service.getBetweenDateTimes(
                LocalDateTime.parse("2019-06-30 11:00:00", FORMATTER),
                LocalDateTime.parse("2019-07-01 20:00:00", FORMATTER),
                USER_ID);
        assertMatch(all, BREAKFAST_01_07, DINNER, LUNCH);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, BREAKFAST_01_07, DINNER, LUNCH, BREAKFAST);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        Meal updated = new Meal(DINNER);
        updated.setCalories(800);
        service.update(updated, ADMIN_ID);
    }

    @Test
    public void update() {
        Meal updated = new Meal(DINNER);
        updated.setCalories(800);
        service.update(updated, USER_ID);
        assertMatch(service.get(updated.getId(), USER_ID), updated);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.parse("2019-07-01 14:00:00", FORMATTER),
                "обед", 500);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(USER_ID), newMeal,
                BREAKFAST_01_07, DINNER, LUNCH, BREAKFAST);
    }
}