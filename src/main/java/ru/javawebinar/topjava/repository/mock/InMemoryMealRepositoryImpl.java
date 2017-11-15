package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        log.info("getAll {}");
        return repository.values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getByUserId(int userid) {
        log.info("getByUserId {}", userid);
        return repository.entrySet().stream()
                .filter(entry -> userid==entry.getValue().getUserId())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> filterByDate(List<Meal> klist, String startDate, String endDate, String startTime, String endTime) {
        return klist.stream()
                .filter(i -> DateTimeUtil.isBetweenDate(i.getDate(),
                        checkIfNull(startDate) ? LocalDate.MIN : LocalDate.parse(startDate),checkIfNull(endDate) ? LocalDate.MAX : LocalDate.parse(endDate)))
                .filter(i -> DateTimeUtil.isBetween(i.getTime(),
                        checkIfNull(startTime) ? LocalTime.MIN : LocalTime.parse(startTime), checkIfNull(endTime) ? LocalTime.MAX : LocalTime.parse(endTime)))
                .collect(Collectors.toList());

    }

    private boolean checkIfNull(String str)
    {
        return str==null || str.isEmpty();
    }

}

