package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        MealRepository f = checkNotFoundWithId(repository, id);
        if (f!=null) {
            repository.delete(id);
        }
        throw new NotFoundException("delete not found id");

    }

    @Override
    public Meal get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
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
    public List<MealWithExceed> filterByDate(String startDate, String endDate, String startTime, String endTime) {
        return repository.filterByDate(startDate,endDate,startTime,endTime);
    }

    public void setRepository(MealRepository repository) {
        this.repository = repository;
    }
}