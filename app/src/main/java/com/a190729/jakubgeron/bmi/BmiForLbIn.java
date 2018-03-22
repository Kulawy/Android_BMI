package com.a190729.jakubgeron.bmi;

/**
 * Created by JakubG on 10.03.2018.
 */

public class BmiForLbIn extends BMI{

    private final static int OVERMASS = 880;
    private final static int OVERHEIGHT = 120;

    public BmiForLbIn(double m, double h) {
        super(m, h);
    }

    @Override
    public double calculateBmi() throws IllegalArgumentException{
        if (!dataAreValid()){
            throw new IllegalArgumentException();
        }
        return (mass/(height*height))*703;
    }

    @Override
    protected boolean dataAreValid() {
        return (mass > 0 && height > 0 && mass <= OVERMASS && height <= OVERHEIGHT);
    }
}

