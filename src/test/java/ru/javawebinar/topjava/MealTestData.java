package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final int USER_ID = 100000;
    public static final int ADMIN_ID = 100001;
    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final Meal BREAKFAST = new Meal(1,
            LocalDateTime.parse("2019-06-30 10:00:00", FORMATTER),
            "завтрак", 600);

    public static final Meal LUNCH = new Meal(2,
            LocalDateTime.parse("2019-06-30 14:00:00", FORMATTER),
            "обед", 700);

    public static final Meal DINNER = new Meal(3,
            LocalDateTime.parse("2019-06-30 19:00:00", FORMATTER),
            "ужин", 500);

    public static final Meal BREAKFAST_01_07 = new Meal(4,
            LocalDateTime.parse("2019-07-01 09:00:00", FORMATTER),
            "завтрак", 300);


    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

}
