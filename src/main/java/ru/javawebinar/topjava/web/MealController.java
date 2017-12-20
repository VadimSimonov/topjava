package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
        List<Meal> list = mealService.getBetweenDates(startDate != null ? startDate : DateTimeUtil.MIN_DATE, endDate != null ? endDate : DateTimeUtil.MAX_DATE, id);
        List<MealWithExceed> result = MealsUtil.getFilteredWithExceeded(list, startTime, endTime, MealsUtil.DEFAULT_CALORIES_PER_DAY);
        modelAndView.addObject("meals", result);
        return modelAndView;
    }

    @GetMapping("/meals/delete/{id}")
    public ModelAndView delete(@PathVariable(name = "id") Integer id)
    {
        mealService.delete(id,AuthorizedUser.id());
        return new ModelAndView("redirect:/meals");
    }

    @GetMapping("/meals/update/{id}")
    public ModelAndView Update(@PathVariable(name = "id") Integer id)
    {
        Meal user = mealService.getWithUser(id, AuthorizedUser.id());
        return new ModelAndView("mealForm", "meal", user);
    }

    @GetMapping("/meals/add")
    public ModelAndView add()
    {
        String create = "create";
        return new ModelAndView("mealForm","create",create);
    }

    @PostMapping("/meals/save")
            public ModelAndView createAndUpdate(
                                                @RequestParam (value = "id" , required = false) Integer id,
                                                @RequestParam (value = "dateTime",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime,
                                                @RequestParam (value = "description",required = false)String description,
                                                @RequestParam (value = "calories",required = false)Integer calories)
    {
        if (id==null)
        {
            Meal meal = new Meal(localDateTime, description, calories);
            mealService.create(meal,AuthorizedUser.id());
        }else {
            Meal meal = new Meal(id,localDateTime, description, calories);
            mealService.update(meal,AuthorizedUser.id());
        }
        return new ModelAndView("redirect:/meals");
    }
}
