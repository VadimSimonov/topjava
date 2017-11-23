package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static ru.javawebinar.topjava.UserTestData.*;

public class MealTestData {
    public static final int Meal1_ID = START_SEQ+2;
    public static final int Meal2_ID = START_SEQ+3;

    public static final int Meal1_User_ID = USER_ID;
    public static final int Meal2_User_ID = ADMIN_ID;

    public static final Meal meal1 = new Meal(Meal1_ID,LocalDateTime.of(2017,11,17, 10,59,52),
            "Обед",500);
    public static final Meal meal2 = new Meal(Meal2_ID,LocalDateTime.of(2017,11,17,14,13,54),
            "Ужин",200);
    public static final List<Meal> mealList=new ArrayList<>();


    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }


    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }


    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("id", "description",
                "calories", "date_time","user_id").isEqualTo(expected);
    }
}
