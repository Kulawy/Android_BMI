package com.a190729.jakubgeron.bmi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.prefs.PreferenceChangeEvent;

public class BMImainActivity extends AppCompatActivity {

    private String userBMI ;
    private int colorByBmi ;
    private double d_mass;
    private double d_height;
    private boolean modeValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_main);

        //Locale localization = Locale.getDefault();
        //String countryCode = localization.getCountry();

        SharedPreferences storedBack = PreferenceManager.getDefaultSharedPreferences(this);
        String mass = storedBack.getString("s_mass", "");
        String height = storedBack.getString("s_height", "");
        modeValue = storedBack.getBoolean( "b_modeValue", false);
        Switch mySwith = (Switch) findViewById(R.id.mode_switch);
        mySwith.setChecked(modeValue);
        setValues(mass, height);
        setMode();
        userBMI = "BMI";
        colorByBmi = R.color.default_color;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_save:
                saveState();
                return true;
            case R.id.action_about_me:
                startAboutMe();
            case R.id.action_clear_saved:
                clearSavedValues();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getMass(){
        EditText massData = (EditText) findViewById(R.id.mass);
        String s_userMass = massData.getText().toString();
        if (s_userMass.equals("")) {
            d_mass = 0;
        } else
            d_mass = Double.parseDouble(s_userMass);
    }

    private void getHeight(){
        EditText heightData = (EditText) findViewById(R.id.height);
        String s_userHeight = heightData.getText().toString();
        if (s_userHeight.equals("")) {
            d_height = 0;
        } else
            d_height = Double.parseDouble(s_userHeight);
    }

    @SuppressLint("DefaultLocale")
    public void letCount(View view) {

        getMass();

        getHeight();

        TextView resultView = (TextView) findViewById(R.id.result);
        //saveState();
        try {
            BMI object;
            if (modeValue){
                object = new BmiForLbIn(d_mass, d_height);
            }else {
                object = new BmiForKgM(d_mass, d_height);
            }
            double i_userBmi = object.calculateBmi();
            userBMI = String.format("%.2f", i_userBmi);
            colorByBmi = object.setColor();
            resultView.setTextColor(getResources().getColor(object.setColor()));
            resultView.setText(userBMI);
            startDisplayBMI();
        } catch (IllegalArgumentException error) {
            letToastForValidation();
        }
    }


    private void letToastForValidation() {
        if (modeValue) {

            if ((d_height <= 0 || d_height >= 120) && (d_mass <= 0 || d_mass >= 180)) {
                Toast.makeText(this, R.string.warningValues, Toast.LENGTH_SHORT).show();
            } else {
                if (d_height <= 0 || d_height >= 120) {
                    Toast.makeText(this, R.string.warningHeight, Toast.LENGTH_SHORT).show();
                } else if (d_mass <= 0 || d_mass >= 180) {
                    Toast.makeText(this, R.string.warningMass, Toast.LENGTH_SHORT).show();
                }
            }

        }
        else {
            if ((d_height <= 0 || d_height >= 300) && (d_mass <= 0 || d_mass >= 400)) {
                Toast.makeText(this, R.string.warningValues, Toast.LENGTH_SHORT).show();
            } else {
                if (d_height <= 0 || d_height >= 300) {
                    Toast.makeText(this, R.string.warningHeight, Toast.LENGTH_SHORT).show();
                } else if (d_mass <= 0 || d_mass >= 400) {
                    Toast.makeText(this, R.string.warningMass, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private void saveState() {

        SharedPreferences store = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = store.edit();
        editor.clear();
        editor.putBoolean("b_modeValue", modeValue);

        EditText massData = (EditText) findViewById(R.id.mass);
        EditText heightData = (EditText) findViewById(R.id.height);
        if ( !massData.getText().toString().equals("") || !heightData.getText().toString().equals("") ){
            if (!massData.getText().toString().equals("")) {
                d_mass = Double.parseDouble(massData.getText().toString());
                String s_userMass = (new Double(d_mass)).toString();
                editor.putString("s_mass", s_userMass);
            } else
                Toast.makeText(this, R.string.warningEmptyValue, Toast.LENGTH_SHORT).show();

            if (!heightData.getText().toString().equals("")) {
                d_height = Double.parseDouble(heightData.getText().toString());
                String s_userHeight = (new Double(d_height)).toString();
                editor.putString("s_height", s_userHeight);
            } else
                Toast.makeText(this, R.string.warningEmptyValue, Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, R.string.warningEmptyValue, Toast.LENGTH_SHORT).show();

        editor.apply();
    }

    private void startDisplayBMI() {
        Intent bmiIntent = new Intent(this, BmiDisplayActivity.class);
        bmiIntent.putExtra("colorInt", colorByBmi);
        bmiIntent.putExtra("value", userBMI);
        startActivity(bmiIntent);
    }


    private void startAboutMe() {
        Intent aboutMe = new Intent(this, AboutAuthorActivity.class);
        startActivity(aboutMe);
    }

    private void setValues(String mass, String height) {
        //double d_mass = Double.parseDouble(mass);
        //double d_height = Double.parseDouble(height);
        if (mass != "") {
            EditText massData = (EditText) findViewById(R.id.mass);
            massData.setText(mass);

        }
        if (height != "") {
            EditText heightData = (EditText) findViewById(R.id.height);
            heightData.setText(height);
        }
    }

    /*private boolean isKgM(){
        Switch varMode = (Switch) findViewById(R.id.mode_switch);
        return !varMode.isChecked();
    } */


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("myText", userBMI);
        outState.putInt("color", colorByBmi);
        outState.putDouble("mass", d_mass);
        outState.putDouble( "height", d_height);
        outState.putBoolean("switchKey", modeValue);
        //saveState();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        userBMI = savedInstanceState.getString("myText");
        colorByBmi = savedInstanceState.getInt("color");
        modeValue = savedInstanceState.getBoolean("switchKey");

        TextView countButton = (TextView) findViewById(R.id.result);
        countButton.setTextColor(getResources().getColor(colorByBmi));
        countButton.setText(userBMI);
    }

    public void changeMode(View view){

        Switch modeSwitch = (Switch) findViewById(R.id.mode_switch);
        modeValue = modeSwitch.isChecked();
        setMode();
        if ( modeValue )
            convertPLtoENG();
        else
            convertENGtoPL();

    }

    private void setMode(){
        TextView massTextView = (TextView) findViewById(R.id.mass_text);
        TextView heightTextView = (TextView) findViewById(R.id.height_text);

        if ( modeValue ) {
            massTextView.setText(R.string.mass_lb);
            heightTextView.setText(R.string.height_in);

        }

        else {
            massTextView.setText(R.string.mass_kg);
            heightTextView.setText(R.string.height_cm);

        }
    }

    private void convertPLtoENG(){
        getMass();
        d_mass *= 2.205;
        getHeight();
        d_height *= 0.394;

        d_mass = Math.round(d_mass*100.0)/100.0;
        d_height = Math.round(d_height*100.0)/100.0;

        setValues(String.valueOf(d_mass), String.valueOf(d_height));

    }

    private void convertENGtoPL(){
        getMass();
        d_mass *= 0.454;

        getHeight();
        d_height *= 2.54;

        d_mass = Math.round(d_mass*100.0) / 100.0;
        d_height = Math.round(d_height*100.0) / 100.0;

        setValues(String.valueOf(d_mass), String.valueOf(d_height));
    }

    private void clearSavedValues(){
        SharedPreferences store = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = store.edit();
        editor.clear();
        editor.apply();
    }

}
