package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

        List<UserMealWithExceed> a = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
     //   for (UserMealWithExceed x:a) {
     //       System.out.println(x.dateTime);
     //   }
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> result = new ArrayList<>();

        Map<LocalDate, Integer> map=mealList.stream().collect(Collectors.groupingBy(p->p.getDateTime().toLocalDate(),Collectors.summingInt(UserMeal::getCalories)));
        for (UserMeal um:mealList             ) {
            if (map.containsKey(um.getDateTime().toLocalDate()) && TimeUtil.isBetween(um.getDateTime().toLocalTime(),startTime,endTime))
            {
                Integer h = map.get(um.getDateTime().toLocalDate());
                int calories=um.getCalories();
                String desc = um.getDescription();
                LocalDateTime time = um.getDateTime();
                result.add(h<=caloriesPerDay ?new UserMealWithExceed(time,desc,calories,false):new UserMealWithExceed(time,desc,calories,true));
            }
        }
            return result;

        }

}
