package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                //new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        System.out.println("filteredByCycles");
        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(9, 0), LocalTime.of(15, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println("filteredByStreams");
        System.out.println(filteredByStreams(meals, LocalTime.of(9, 0), LocalTime.of(15, 0), 2000));
    }


    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        List<UserMealWithExcess> userMealWithExcessList = new ArrayList<>();

        HashMap<LocalDate, Integer> userMealDay = new HashMap<>();
        int tmp = 0;
        //LocalDate localDate = LocalDate.now();
        LocalDate localDate = LocalDate.of(2999, 1, 1);
        for (UserMeal userMeal : meals) {
            if (localDate.equals(userMeal.getDateTime().toLocalDate())) {
                tmp = tmp + userMeal.getCalories();
            } else {
                localDate = userMeal.getDateTime().toLocalDate();
                if (userMealDay.get(localDate) == null) {
                    tmp = userMeal.getCalories();
                } else {
                    tmp = userMeal.getCalories() + userMealDay.get(localDate);
                }

            }
            userMealDay.put(userMeal.getDateTime().toLocalDate(), tmp);
        }

        System.out.println("HashMapCycles");
        System.out.println(userMealDay.toString());

        meals.forEach(s -> {
            if (s.getDateTime().toLocalTime().isAfter(startTime) && s.getDateTime().toLocalTime().isBefore(endTime)) {
                userMealWithExcessList.add(new UserMealWithExcess(s.getDateTime(), s.getDescription(), s.getCalories(), userMealDay.get(s.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        });

        return userMealWithExcessList;
    }

    static HashMap<LocalDate, Integer> userMealDay = new HashMap<>();

    private static void funcForSream(UserMeal userMeal) {
        LocalDate localDate = LocalDate.of(2999, 1, 1);
        int tmp = 0;

        if (userMeal.getDateTime().toLocalDate().equals(localDate)) {
            tmp = tmp + userMeal.getCalories();
        } else {
            localDate = userMeal.getDateTime().toLocalDate();
            //tmp = s.getCalories();
            if (userMealDay.get(localDate) == null) {
                tmp = userMeal.getCalories();
            } else {
                tmp = userMeal.getCalories() + userMealDay.get(localDate);
            }
        }
        userMealDay.put(userMeal.getDateTime().toLocalDate(), tmp);
    }


    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams

        Stream<UserMeal> streamFromCollection = meals.stream();

        streamFromCollection.forEach(s -> funcForSream(s));


        List<UserMealWithExcess> userMealWithExcessList = new ArrayList<>();

        meals.forEach(s -> {
            if (s.getDateTime().toLocalTime().isAfter(startTime) && s.getDateTime().toLocalTime().isBefore(endTime)) {
                userMealWithExcessList.add(new UserMealWithExcess(s.getDateTime(), s.getDescription(), s.getCalories(), userMealDay.get(s.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        });

        System.out.println("HashMapStream");
        System.out.println(userMealDay.toString());

        return userMealWithExcessList;
    }
}
