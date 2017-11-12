package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
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
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository,id);
        ///// dodelat
    }

    @Override
    public Meal get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public void update(Meal meal) {

    }

    @Override
    public List<Meal> getAll() {
        return (List<Meal>) repository.getAll();
    }

    @Override
    public List<Meal> getByUserId(int userid) {
        return null;
    }

    public void setRepository(MealRepository repository) {
        this.repository = repository;
    }
}