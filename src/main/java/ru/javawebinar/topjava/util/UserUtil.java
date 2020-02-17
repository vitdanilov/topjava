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
            new User(counter.incrementAndGet(), "Don Juan Garsia", "garsia@juan.com", "password", 2000, true, Collections.singleton(Role.ROLE_USER)),
            new User(counter.incrementAndGet(), "Don Pedro", "don@pedro.com", "password", 1500, true, Collections.singleton(Role.ROLE_USER)),
            new User(counter.incrementAndGet(), "Иван Петрофф", "ivan@petroff.com", "password", 2500, true, Collections.singleton(Role.ROLE_USER)),
            new User(counter.incrementAndGet(), "Abraham Scott", "abraham@scott.com", "password", 2500, false, Collections.singleton(Role.ROLE_USER))
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
