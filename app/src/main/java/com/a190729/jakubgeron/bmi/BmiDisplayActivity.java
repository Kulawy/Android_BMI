package com.a190729.jakubgeron.bmi;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class BmiDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_display);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent srcIntent = getIntent();

        ConstraintLayout bmiDisplayConstraintLayout = (ConstraintLayout) findViewById(R.id.bmi_display_background);
        TextView bmiValueTextView = (TextView) findViewById(R.id.bmi_value);
        bmiValueTextView.setText(srcIntent.getStringExtra("value"));
        bmiDisplayConstraintLayout.setBackgroundColor((getResources().getColor(srcIntent.getIntExtra("colorInt", R.color.default_color))));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_bmi_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
