package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDateTime;
import java.time.Month;


import static java.time.LocalDateTime.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;

public class MealRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Test
    public void TestGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MEAL1));
    }

    @Test
    public void TestDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk());
        assertMatch(mealService.getAll(AuthorizedUser.id()), MEAL6,MEAL5,MEAL4,MEAL3,MEAL2);
    }

    @Test
    public void TestGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MealTestData.contentJson(MEALS)));
    }

    @Test
    public void TestCreate() throws Exception {
        Meal expected = new Meal(null, of(2015, Month.NOVEMBER, 12, 12, 12), "NewЗавтрак", 999);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Meal returned = TestUtil.readFromJson(action, Meal.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(mealService.getAll(AuthorizedUser.id()), expected,MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1);
    }

    @Test
    public void TestUpdate() throws Exception {
        Meal updated = MEAL1;
        updated.setDescription("UpdatedDescription");
        updated.setCalories(11);
        mockMvc.perform(put(REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(mealService.get(MEAL1_ID,AuthorizedUser.id()), updated);
    }

    @Test
    public void TestGetBetween() throws Exception {
        LocalDateTime start = of(2015, Month.MAY, 30, 9, 0);
        LocalDateTime end = of(2015, Month.MAY, 30, 21, 0);

        mockMvc.perform(get(REST_URL + "filter")
        .param("startDateTime",start.toString())
                        .param("endDateTime",end.toString())
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(contentJson(MEAL1,MEAL2,MEAL3));

    }

}