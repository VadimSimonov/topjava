package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
        List<UserMealWithExceed> result =getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();

        for (UserMealWithExceed a:result
             ) {
            System.out.println(a.getDateTime()+" "+a.getDescription()+" "+a.getCalories());

        }




    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        //UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed)
        //UserMealWithExceed userMealWithExceed = new UserMealWithExceed(LocalDateTime.of(2015, Month.MAY, 30,10,0),"1",22,true);

        List<UserMealWithExceed> result = new LinkedList<>();

        for (UserMeal a:mealList)
        {
            LocalTime time = LocalTime.of(a.getDateTime().getHour(),a.getDateTime().getMinute());
            int calories = a.getCalories();
            String description = a.getDescription();
            LocalDate date=a.getDateTime().toLocalDate();
            LocalDateTime localDateTime = LocalDateTime.of(date,time);

            if (isBetween(time,startTime,endTime) && calories<=caloriesPerDay)
           {
                result.add(new UserMealWithExceed(localDateTime,description,calories,false));
            }else if (isBetween(time,startTime,endTime) && calories>caloriesPerDay)
                result.add(new UserMealWithExceed(localDateTime,description,calories,true));
        }

        return result;
    }

    public static boolean isBetween(LocalTime candidate, LocalTime start, LocalTime end) {
        return !candidate.isBefore(start) && !candidate.isAfter(end);
    }
    

}
