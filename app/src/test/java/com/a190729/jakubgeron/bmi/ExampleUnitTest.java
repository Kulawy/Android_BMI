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
        BMI bmiCounter = new BmiForKgM( 60, 170);
        double bmi = bmiCounter.calculateBmi();
        assertEquals( 20.761, bmi, 0.001);
    }

    @Test
    public void for_valid_mass_and_height_should_return_correct_value_Internal(){
        BMI bmiCounter = new BmiForLbIn( 400, 80);
        double bmi = bmiCounter.calculateBmi();
        assertEquals( 43.937, bmi, 0.001);
    }

    @Test
    public void for_zero_mass_and_height_should_throw_exception(){
        BMI bmiCounter = new BmiForKgM( 0, 0);
        try{
            double bmi = bmiCounter.calculateBmi() ;
            fail();
        }catch (IllegalArgumentException ex){
            //System.out.println("Exception was caught");
        }
    }

    @Test(expected= IllegalArgumentException.class)
    public void for_zero_mass_and_height_should_throw_exception_v2(){
        BMI bmiCounter = new BmiForKgM( 0, 0);
        double bmi = bmiCounter.calculateBmi() ;

    }

    @Test
    public void for_minus_mass_and_height_should_throw_exception(){
        BMI bmiCounter = new BmiForKgM( -10, -20);
        try{
            double bmi = bmiCounter.calculateBmi() ;
            fail();
        }catch (IllegalArgumentException ex){
            //System.out.println("Exception was caught");
        }
    }

    @Test
    public void for_zero_mass_and_height_should_throw_exception_Internal(){
        BMI bmiCounter = new BmiForLbIn( 0, 0);
        try{
            double bmi = bmiCounter.calculateBmi() ;
            fail();
        }catch (IllegalArgumentException ex){
            //System.out.println("Exception was caught");
        }
    }

    @Test
    public void for_minus_mass_and_height_should_throw_exception_Internal(){
        BMI bmiCounter = new BmiForLbIn( -40, -50);
        try{
            double bmi = bmiCounter.calculateBmi() ;
            fail();
        }catch (IllegalArgumentException ex){
            //System.out.println("Exception was caught");
        }
    }

    @Test(expected= IllegalArgumentException.class)
    public void for_minus_mass_and_height_should_throw_exception_Internal_v2() {
        BMI bmiCounter = new BmiForLbIn( -40, -50);
        double bmi = bmiCounter.calculateBmi() ;
    }

    @Test
    public void for_small_BMI_gave_blue_color(){
        BMI bmiCounterSmall = new BmiForKgM( 60, 200);
        int colorBlue = R.color.blue;
        assertEquals( colorBlue, bmiCounterSmall.setColor());
    }

    @Test
    public void for_mid_BMI_gave_green_color(){
        BMI bmiCounterMid = new BmiForKgM( 80, 180);
        int colorGreen = R.color.green;
        int colorBMI = bmiCounterMid.setColor();
        assertEquals( colorGreen, colorBMI );
    }

    @Test
    public void for_big_BMI_gave_red_color(){
        BMI bmiCounterBig = new BmiForKgM( 60, 150);
        int colorRed = R.color.red;
        assertEquals( colorRed, bmiCounterBig.setColor());
    }


}