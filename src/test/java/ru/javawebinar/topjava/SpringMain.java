package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/spring-db.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
           // adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));
            System.out.println();

            MealRestController mealController = appCtx.getBean(MealRestController.class);
            List<MealWithExceed> filteredMealsWithExceeded =
                    mealController.getBetween(
                            LocalDate.of(2017, Month.NOVEMBER, 16), LocalTime.of(7, 0,12),
                            LocalDate.of(2017, Month.NOVEMBER, 18), LocalTime.of(11, 0,13));
            filteredMealsWithExceeded.forEach(System.out::println);
            System.out.println();
//            mealController.delete(100003);
            List<MealWithExceed> list = mealController.getAll();
            list.forEach(System.out::println);
            mealController.update(new Meal(LocalDateTime.now(),"newNew",1000),100007);
           // mealController.create(new Meal(LocalDateTime.now(),"new",2000));
            List<MealWithExceed> list1 = mealController.getAll();
            list1.forEach(System.out::println);
            Meal meal = mealController.get(100002);
            System.out.println(meal.toString());

        }
    }
}
