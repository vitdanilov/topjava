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
        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(0, 0), LocalTime.of(15, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println("filteredByStreams");
        System.out.println(filteredByStreams(meals, LocalTime.of(0, 0), LocalTime.of(15, 0), 2000));
    }


    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExcess> userMealWithExcessList = new ArrayList<>();

        Map<LocalDate, Integer> userMealDay = new HashMap<>();

        for (UserMeal userMeal : meals) {

            userMealDay.merge(userMeal.getDateTime().toLocalDate(),userMeal.getCalories(), (value, newValue) -> value + newValue);

        }

        meals.forEach(s -> {
            if (TimeUtil.isBetweenInclusive(s.getDateTime().toLocalTime(), startTime, endTime)){
                userMealWithExcessList.add(new UserMealWithExcess(s.getDateTime(), s.getDescription(), s.getCalories(),
                        userMealDay.get(s.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        });

        return userMealWithExcessList;
    }


    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {


        Map<LocalDate, Integer> caloriesSumByDate = meals.stream().collect(Collectors.groupingBy(s -> s.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        List<UserMealWithExcess> userMealWithExcess = meals.stream().filter(s -> TimeUtil.isBetweenInclusive(s.getDateTime().toLocalTime(), startTime, endTime))
                .map(s -> new UserMealWithExcess(s.getDateTime(), s.getDescription(), s.getCalories(), caloriesSumByDate.get(s.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());

        return userMealWithExcess;
    }
}
