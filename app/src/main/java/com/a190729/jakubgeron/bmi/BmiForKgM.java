package com.a190729.jakubgeron.bmi;

/**
 * Created by JakubG on 08.03.2018.
 */

public class BmiForKgM extends BMI {

    private final static int OVERMASS = 400;
    private final static int OVERHEIGHT = 300;

    public BmiForKgM(double m, double h) {
        super(m, h);
    }

    @Override
    public double calculateBmi() throws IllegalArgumentException{
        if (!dataAreValid()){
            throw new IllegalArgumentException();
        }
        return mass/((height/100)*(height/100));
    }

    @Override
    protected boolean dataAreValid() {
        return (mass > 0 && height > 0 && mass <= OVERMASS && height <= OVERHEIGHT);
    }
}


// Add vertical albo horizontal guidelayn w costrait layout w graficznym tworzeniu layoutu
// content description ??? moja morda

