package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MealWithExceed {
    private final LocalDateTime dateTime;
    private final LocalTime localTime;
    private final LocalDate localDate;
    private final String description;

    private final int calories;

    private final boolean exceed;

    public MealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        this.localTime = dateTime.toLocalTime();
        this.localDate=dateTime.toLocalDate();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }
}
