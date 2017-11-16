package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int id,int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id,userId), id);
    }

    @Override
    public Meal get(int id,int userdId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id,userdId), id);
    }

    @Override
    public void update(Meal meal) {
        repository.save(meal);
    }

    @Override
    public List<Meal> getAll() {
        return (List<Meal>) repository.getAll();
    }

    @Override
    public List<Meal> getByUserId(int userid) {
        return repository.getByUserId(userid);
    }

    @Override
    public List<Meal> filterByDate(List<Meal> klist, String startDate, String endDate, String startTime, String endTime) {
        return repository.filterByDate(klist,startDate,endDate,startTime,endTime);
    }

    public void setRepository(MealRepository repository) {
        this.repository = repository;
    }
}