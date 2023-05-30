package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class PlannerTest {
    private Planner planner;

    @BeforeEach
    void setUp() {
        planner = new Planner();
    }

    @ParameterizedTest(name = "Activity Level: {0}")
    @EnumSource(ActivityLevel.class)
    void shouldReturnCorrectDailyCalories_whenUserProvided(ActivityLevel activityLevel) {
        //given
        User user = TestConstants.TEST_USER;

        //when
        int Calories = planner.calculateDailyCaloriesDemand(user,activityLevel);

        //then
        assertEquals(TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel),Calories);
    }

    @Test
    void shouldReturnDailyIntake_whenUserProvided() {
        //given
        User user = TestConstants.TEST_USER;

        //when
        DailyIntake result = planner.calculateDailyIntake(user);

        //then
        DailyIntake expected = TestConstants.TEST_USER_DAILY_INTAKE;
        assertEquals(expected.getCalories(), result.getCalories());
        assertEquals(expected.getCarbohydrate(), result.getCarbohydrate());
        assertEquals(expected.getFat(), result.getFat());
        assertEquals(expected.getProtein(), result.getProtein());
    }

}