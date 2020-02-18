package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

public abstract class AbstractMealController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<Meal> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Meal create(Meal Meal) {
        log.info("create {}", Meal);
        checkNew(Meal);
        return service.create(Meal);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Meal Meal, int id) {
        log.info("update {} with id={}", Meal, id);
        assureIdConsistent(Meal, id);
        service.update(Meal);
    }

}