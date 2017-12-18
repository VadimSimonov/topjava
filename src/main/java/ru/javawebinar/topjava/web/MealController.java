package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

@Controller
public class MealController {

    @Autowired
    MealService mealService;

    @RequestMapping(value = "meals",method = RequestMethod.GET)
    public ModelAndView getAll(ModelAndView modelAndView)
    {
        int id=AuthorizedUser.id();
        List<MealWithExceed> m = MealsUtil.getWithExceeded(mealService.getAll(id), MealsUtil.DEFAULT_CALORIES_PER_DAY);
        //return new ModelAndView("meals","meal",m);
        modelAndView.addObject("meals", m);
        return modelAndView;
    }
}
