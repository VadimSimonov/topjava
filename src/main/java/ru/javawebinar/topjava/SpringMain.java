package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
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

        }
    }
}
