package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
        mealList.add(meal1);
        mealList.add(meal2);
    }
    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception {
        Meal meal = service.get(Meal2_ID, Meal1_User_ID);
        assertMatch(meal2,meal);

    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(Meal1_ID, 99999);

    }

    @Test
    public void delete() throws Exception {
        service.delete(Meal1_ID,Meal1_User_ID);
        Assert.assertEquals(1,service.getAll(Meal1_User_ID).size());
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(Meal1_ID,1);
    }

    @Test
    public void getBetweenDates() throws Exception {
        assertMatch(service.getBetweenDates(LocalDate.of(2017,11,16),
                LocalDate.of(2017,11,18),Meal1_User_ID),mealList);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        assertMatch(service.getBetweenDateTimes(LocalDateTime.of(2017,11,16,8,0,0),
                LocalDateTime.of(2017,11,18,21,0,0),Meal1_User_ID),mealList);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> meals = service.getAll(Meal1_User_ID);
        Assert.assertEquals(mealList.size(),meals.size());
        assertMatch(meals,mealList);
    }

    @Test
    public void update() throws Exception {
        Meal updated = meal1;
        updated.setDescription("NewChanges");
        updated.setCalories(999);
        service.update(updated,Meal1_User_ID);
        assertMatch(service.update(updated,Meal1_User_ID),updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        Meal updated = meal1;
        updated.setDescription("NewChanges");
        updated.setCalories(999);
        service.update(updated,Meal2_User_ID);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(null,LocalDateTime.of(2017,11,17,
                10,59,52), "Create",700);
        Meal created = service.create(newMeal,Meal1_User_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(Meal1_User_ID), meal1, newMeal, meal2);
    }

}