package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UserUtils {

    public static final List<User> USERS = Arrays.asList(
            new User(null, "Mary", "mary123", "mary@yandex.ru",
                    Role.ROLE_ADMIN, Role.values()),
            new User(null, "Mark", "mark123", "mark@yandex.ru",
                    Role.ROLE_USER, Role.values())
    );
}
