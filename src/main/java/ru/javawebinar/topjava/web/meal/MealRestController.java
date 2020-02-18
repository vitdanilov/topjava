package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

public class MealRestController extends AbstractMealController {
    private MealService service;

    @Override
    public List<Meal> getAll() {
        return super.getAll();
    }

    @Override
    public Meal get(int id) {
        return super.get(id);
    }

    @Override
    public Meal create(Meal Meal) {
        return super.create(Meal);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(Meal Meal, int id) {
        super.update(Meal, id);
    }
}