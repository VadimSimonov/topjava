package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal meal : MealsUtil.MEALS) {
            save(meal);
        }
    }

    @Override
    public Meal save(Meal meal) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id) {
        log.info("delete {}", id);
        repository.remove(id);
    }

    @Override
    public Meal get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
       // return new ArrayList<>(repository.values());
        log.info("getAll {}");
        return repository.values().stream()
                .sorted(Comparator.comparing(Meal::getTime).thenComparing(Meal::getDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getByUserId(int userid) {
        log.info("getByUserId {}", userid);
        List<Meal> list = repository.entrySet().stream()
                .filter(entry -> userid==entry.getValue().getUserId())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        return list;
    }
}

