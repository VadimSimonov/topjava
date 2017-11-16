package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.util.Collection;
import java.util.List;

public interface MealRepository {
    Meal save(Meal meal);

    boolean delete(int id,int userId);

    Meal get(int id,int userid);

    Collection<Meal> getAll();

    List<Meal> getByUserId(int userid);

    List<Meal> filterByDate(List<Meal> klist, String startDate, String endDate, String startTime, String endTime);
}
