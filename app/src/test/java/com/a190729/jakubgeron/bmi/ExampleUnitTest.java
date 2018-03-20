package com.a190729.jakubgeron.bmi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void for_valid_mass_and_height_should_return_correct_value(){
        BMI bmiCounter = new BmiForKgM( 60, 1.70);
        double bmi = bmiCounter.calculateBmi();
        assertEquals( 20.761, bmi, 0.001);
    }

    @Test
    public void for_zero_mass_and_height_should_throw_exception(){
        BMI bmiCounter = new BmiForKgM( 0, 0);
        double bmi = bmiCounter.calculateBmi();
    }

    @Test
    public void for_minus_mass_and_height_should_throw_exception(){
        BMI bmiCounter = new BmiForKgM( -10, -20);
        double bmi = bmiCounter.calculateBmi();
    }


}