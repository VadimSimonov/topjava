package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import java.util.List;

public interface MealService {

    Meal create(Meal meal);

    void delete(int id,int userId) throws NotFoundException;

    Meal get(int id,int userId) throws NotFoundException;

    void update(Meal meal);

    List<Meal> getAll();

    List<Meal> getByUserId(int userid);

    List<Meal> filterByDate(List<Meal> klist, String startDate, String endDate, String startTime, String endTime);

}