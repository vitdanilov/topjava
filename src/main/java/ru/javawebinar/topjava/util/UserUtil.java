package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserUtil {
    public static final int NOT_EXIST_USER_ID = 0;
    private static AtomicInteger counter = new AtomicInteger(NOT_EXIST_USER_ID);

    public static final List<User> USERS = Arrays.asList(
            new User("Don Juan Garsia", "garsia@juan.com", "password"),
            new User("Don Pedro", "don@pedro.com", "password"),
            new User("Иван Петрофф", "ivan@petroff.com", "password"),
            new User("Abraham Scott", "abraham@scott.com", "password")
    );

    public static AtomicInteger getCounter() {
        return counter;
    }

    public static User findByEmail(Collection<User> users, String email) {
        return (User) users.stream().filter(s -> s.getEmail().equals(email));
    }

    public static List<User> getTos(Collection<User> users) {
        return users.stream().sorted().collect(Collectors.toList());
    }

}
