package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.DAYS;

@Controller
public class MealController {

    @Autowired
    MealService mealService;

    @GetMapping("/meals")
    public ModelAndView getAll(ModelAndView modelAndView)
    {
        int id=AuthorizedUser.id();
        List<MealWithExceed> m = MealsUtil.getWithExceeded(mealService.getAll(id), MealsUtil.DEFAULT_CALORIES_PER_DAY);
        //return new ModelAndView("meals","meal",m);
        modelAndView.addObject("meals", m);
        return modelAndView;
    }

    @PostMapping("/meals")
    public ModelAndView getBeetween(ModelAndView modelAndView,
                                    @RequestParam (value ="startDate" , required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                    @RequestParam (value ="endDate" , required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                    @RequestParam (value ="startTime" , required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
                                    @RequestParam (value ="endTime" , required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime)
    {
        int id=AuthorizedUser.id();
        List<Meal> list = mealService.getBetweenDates(startDate != null ? startDate : DateTimeUtil.MIN_DATE, startDate != null ? startDate : DateTimeUtil.MAX_DATE, id);
        List<MealWithExceed> result = MealsUtil.getFilteredWithExceeded(list, startTime, endTime, MealsUtil.DEFAULT_CALORIES_PER_DAY);
        modelAndView.addObject("meals", result);
        return modelAndView;
    }


}
