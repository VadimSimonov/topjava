package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName1", "email1", "password1", Role.ROLE_ADMIN));
            adminUserController.create(new User(null, "userName2", "email2", "password2", Role.ROLE_ADMIN));
            adminUserController.create(new User(null, "userName3", "email3", "password3", Role.ROLE_ADMIN));
            adminUserController.create(new User(null, "userName4", "email4", "password4", Role.ROLE_ADMIN));

            adminUserController.create(new User(null, "userName5", "email5", "password5", Role.ROLE_USER));

            System.out.println(adminUserController.getAll().size());

        }
    }
}
