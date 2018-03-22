package com.a190729.jakubgeron.bmi;

/**
 * Created by JakubG on 01.03.2018.
 */

public abstract class BMI {

    protected double mass;
    protected double height;
    private static final double UNDER_BMI = 18.5;
    private static final double OVER_BMI = 25;

    public BMI(double m, double h){
        mass = m;
        height = h;
    }

    public void setHeight(double height) {
        this.height = height;
    }


    public void setMass(int mass) {
        this.mass = mass;
    }

    public int setColor(){

        if ( calculateBmi() < UNDER_BMI )
            return R.color.blue;
        else if ( calculateBmi() > OVER_BMI )
            return R.color.red;
        else
            return R.color.green;
    }

    public double getHeight() {
        return height;
    }

    public double getMass() {
        return mass;
    }

    abstract public double calculateBmi();
    /*{
        if (dataAreValid()){
            throw new IllegalArgumentException();
        }
        return mass/(height*height);
    }*/
    abstract protected boolean dataAreValid();
    /*{
        return mass>0 && height >0;
    }*/
}

/* ZADANIE :

 Mamy ekran z bmi na linarlayout :
 w pasku opcja save i 3 kropki
 po opcji save, kiedy aplikacja zostaje zabita to ją wczytuje z zapisanymi danymi;
 mass:
 height:
 button
 po wciśnięciu buttona otwieramy nową aktywność i rysujemy na środku
 BMI:
 wartość bmi a kolor tła to zależy od tego jakie jest bmi

 w rogu głównego ekranu mają być trzy kropki i rozwija się menu z  button "O Autorze"
 który przenosi nas do nowej activity z O mnie ( zdjęcie , imie nazwisko itp.) i niech będzie full screan activity

 i rotacja ma nas nie zepsuć

 przełącznik : w dolnym rogu ekranu, pomiędzy jednostkami , kg i metry , stopy i funty


 punkty można stracić za to że nie będzie działać tak ja powinno , za to, że UI nie będzie zrozumiały dla użytkownika
 stringi zawsze w resoursach
 możemy zamiast toasta pobawić się onError ( wyszukać onError)
 nie będzie dobrze reagować na rotację
 źle będzie liczyć


  */
