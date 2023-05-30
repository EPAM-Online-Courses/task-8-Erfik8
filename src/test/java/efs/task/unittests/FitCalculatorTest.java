package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;
import java.util.List;

import static efs.task.unittests.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;
        //when
        boolean expected = FitCalculator.isBMICorrect(weight, height);
        //then
        assertTrue(expected);
    }

    @Test
    void shouldReturnFalse_whenDietNotRecommended() {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean expected = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(expected);
    }

    @Test
    public void shouldThrowException_whenHeightZero() {
        //given

        double weight = 69.5;
        double height = 0.0;

        //then

        assertThrows(IllegalArgumentException.class, () -> {
            boolean expected = FitCalculator.isBMICorrect(weight, height);
        });
    }

    @ParameterizedTest(name = "weight")
    //given
    @ValueSource(doubles = {50.5,55.3,50.2})
    void shouldReturnFalse_whenDietNotRecommendedOnlyWeight(Double weight) {
        //when
        Double height = 1.7;
        boolean expected = FitCalculator.isBMICorrect(weight, height);
        //then
        assertFalse(expected);
    }

    @ParameterizedTest(name = "weight, height")
    //given
    @CsvSource({"69.5,1.7", "20.0,1.0", "1.0,2.0"})
    void shouldReturnFalse_whenDietNotRecommendedArray(Double weight, Double height) {
        //when
        boolean expected = FitCalculator.isBMICorrect(weight, height);
        //then
        assertFalse(expected);
    }

    @ParameterizedTest(name = "weight, height")
    //given
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalse_whenDietNotRecommendedCsvFile(Double weight, Double height) {
        //when
        boolean expected = FitCalculator.isBMICorrect(weight, height);
        //then
        assertFalse(expected);
    }

    @Test
    void shouldReturnWorstBMIinConstants_whenListProvided() {
        //given
        List<User> UserList = TestConstants.TEST_USERS_LIST;

        //when
        User worst = FitCalculator.findUserWithTheWorstBMI(UserList);

        //then
        assertEquals(1.79, worst.getHeight());
        assertEquals(97.3, worst.getWeight());
    }

    @Test
    void shouldReturnNull_whenEmptyList() {
        //given
        List<User> UserList = Collections.emptyList();

        //when
        User worst = FitCalculator.findUserWithTheWorstBMI(UserList);

        //then
        assertEquals(null, worst);
    }

    @Test
    void shouldReturnCorrectBMI_whenListProvided() {
        //given
        List<User> UserList = TestConstants.TEST_USERS_LIST;
        double[] expectedBMI = TEST_USERS_BMI_SCORE;
        //when
        double[] scoreList = FitCalculator.calculateBMIScore(UserList);

        //then
        assertArrayEquals(expectedBMI, scoreList);
    }
}
