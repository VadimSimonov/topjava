package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
           // AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);

           mealRestController.create(new Meal(null,3,LocalDateTime.of(2015, Month.APRIL, 30, 9, 0), "Завтрак", 500));
            System.out.println(mealRestController.getAll().size());
            List<Meal> list=mealRestController.getAll();
            for (Meal meal:list) {
                System.out.println(meal.getId() + " " + meal.getUserId() + " " + meal.getDate()+" "+meal.getDateTime()+ " " + meal.getDescription() + " " + meal.getCalories());
            }

            mealRestController.update(new Meal(null,3,LocalDateTime.of(2016, Month.JUNE, 20, 11, 0), "NewЗавтрак", 500),3);
            System.out.println(mealRestController.getAll().size());
            List<Meal> list1=mealRestController.getAll();
            for (Meal meal:list1) {
                System.out.println(meal.getId() + " " + meal.getUserId() + " " + meal.getDate()+" "+meal.getDateTime()+ " " + meal.getDescription() + " " + meal.getCalories());
            }
          //  mealRestController.delete(3);
            System.out.println(mealRestController.getAll().size());
            List<Meal> list2=mealRestController.getAll();
            for (Meal meal:list2) {
                System.out.println(meal.getId() + " " + meal.getUserId() + " " + meal.getDate()+" "+meal.getDateTime()+ " " + meal.getDescription() + " " + meal.getCalories());
            }
            List<Meal> list3 = mealRestController.getByUserId(2);
            for (Meal meal :list3) {
                System.out.println(meal.getId() + " " + meal.getUserId() + " " + meal.getDate()+" "+meal.getDateTime()+ " " + meal.getDescription() + " " + meal.getCalories());
            }

/*
            adminUserController.create(new User(null, "userName3", "email3", "password3", Role.ROLE_USER));
            adminUserController.create(new User(null, "userName4", "email4", "password4", Role.ROLE_USER));
            adminUserController.create(new User(null, "userName5", "email5", "password5", Role.ROLE_USER));
            adminUserController.create(new User(null, "userName0", "email0", "password0", Role.ROLE_USER));
            adminUserController.create(new User(null, "userName1", "email1", "password1", Role.ROLE_USER));
            adminUserController.create(new User(null, "userName2", "email2", "password2", Role.ROLE_USER));

            List<User> list=adminUserController.getAll();
            for (User user:list
                 ) {
                System.out.println(user.getId()+" "+user.getName()+" "+user.getEmail()+" "+user.getRoles()+" "+user.getPassword());
            }

            adminUserController.update(new User(null, "newUser", "mail", "passw", Role.ROLE_USER),1);
            List<User> list1=adminUserController.getAll();
            for (User user:list1
                    ) {
                System.out.println(user.getId()+" "+user.getName()+" "+user.getEmail()+" "+user.getRoles()+" "+user.getPassword());
            }
            adminUserController.delete(1);
           // System.out.println(adminUserController.get(1));
            List<User> list3=adminUserController.getAll();
            for (User user:list3
                    ) {
                System.out.println(user.getId()+" "+user.getName()+" "+user.getEmail()+" "+user.getRoles()+" "+user.getPassword());
            }
            System.out.println(adminUserController.getByMail("email011"));
*/

        }
    }
}
