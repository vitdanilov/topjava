package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

public class MealService {

    private MealRepository repository;

    public List<Meal> getAll() {
        return repository.getAll(SecurityUtil.authUserId()).stream().collect(Collectors.toList());
    }

    public Meal get(int id) {
        return checkNotFoundWithId(repository.get(id, SecurityUtil.authUserId()), id);
    }

    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id, SecurityUtil.authUserId()), id);
    }

    public void update(Meal meal) {
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }

}