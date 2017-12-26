package ru.javawebinar.topjava.web;

import org.hamcrest.Matcher;
import org.junit.Test;
import ru.javawebinar.topjava.model.Meal;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class RootControllerTest extends AbstractControllerTest {
    @Test
    public void testMeals() throws Exception {
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("meals"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/meals.jsp"))
                .andExpect(model().attribute("meals", hasSize(6)))
                .andExpect(model().attribute("meals", hasItems(
                                mealis(MEAL1,false),
                                mealis(MEAL2,false),
                                mealis(MEAL3,false),
                                mealis(MEAL4,true),
                                mealis(MEAL5,true),
                                mealis(MEAL6,true)
                  )));
    }

    private Matcher mealis(Meal meal, boolean flag) {
        return allOf(
                hasProperty("id", is(meal.getId())),
                hasProperty("dateTime", is(meal.getDateTime())),
                hasProperty("description", is(meal.getDescription())),
                hasProperty("calories", is(meal.getCalories())),
                hasProperty("exceed", is(flag))
        );
    }

    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", hasSize(2)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ)),
                                hasProperty("name", is(USER.getName()))
                        )
                )));
    }
}