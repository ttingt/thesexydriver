package com.costbear.android.thesexydriver;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class SummaryActivity extends Activity {

    static final int initialScore = 10;

    int brakePtsCount;
    int brakeRatingSoFar;
    int speedPtsCount;
    int speedRatingSoFar;
    int overallScore;
    String ratingString;

    TextView brakingRatingTextView;
    TextView ratingTextView;
    TextView speedRatingTextView;
    TextView ratingMsgTextView;
    TextView fuelConsumption;
    TextView emissions;
    TextView totaldistance;

    ImageButton reset;
    ImageButton stats;
    ImageButton credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Intent i = getIntent();
        brakePtsCount = i.getIntExtra("brakePtsCount", 0);
        brakeRatingSoFar = i.getIntExtra("brakeRatingSoFar", 0);
        speedPtsCount = i.getIntExtra("speedPtsCount", 0);
        speedRatingSoFar = i.getIntExtra("speedRatingSoFar", 0);

        brakingRatingTextView = (TextView) findViewById(R.id.brakingrating);
        speedRatingTextView = (TextView) findViewById(R.id.speedingrating);
        ratingTextView = (TextView) findViewById(R.id.rating);
        ratingMsgTextView = (TextView) findViewById(R.id.ratingmsg);
        fuelConsumption = (TextView) findViewById(R.id.fuelconsumption);
        emissions = (TextView) findViewById(R.id.emissions);
        totaldistance = (TextView) findViewById(R.id.totaldistance);
        reset = (ImageButton) findViewById(R.id.home);
        stats = (ImageButton) findViewById(R.id.stats);
        credits = (ImageButton) findViewById(R.id.credits);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
        brakingRatingTextView.setTypeface(face);
        speedRatingTextView.setTypeface(face);
        ratingTextView.setTypeface(face);
        ratingMsgTextView.setTypeface(face);
        fuelConsumption.setTypeface(face);
        emissions.setTypeface(face);
        totaldistance.setTypeface(face);

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(SummaryActivity.this, MainActivity.class);

                startActivity(i);
                finish();
            }
        });

        credits.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(SummaryActivity.this, CreditsActivity.class);

                startActivity(i);
                finish();
            }
        });
        stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(SummaryActivity.this, StatsActivity.class);

                startActivity(i);
                finish();
            }
        });

        String brakeR;
        String speedR;
        String overallRating;
        if( brakePtsCount < 1) {
            brakeR = "Waiting to get more data points first!";
        } else {
            brakeR = String.valueOf(brakeRatingSoFar/brakePtsCount);
        }
        if(speedPtsCount < 1) {
            speedR = "Waiting to get more data points first!";
        } else {
            speedR = String.valueOf(speedRatingSoFar/speedPtsCount);
            overallScore = (int) Math.round(brakeRatingSoFar/brakePtsCount*.4 + speedRatingSoFar/speedPtsCount*.6);
        }



        if (brakePtsCount >= 3 && speedPtsCount >= 1) {
            double brakingPercentage = 1 - (double) brakeRatingSoFar/ (double) brakePtsCount * 0.05;
            double speedingPercentage = 1 + (double)speedRatingSoFar/(double)speedPtsCount * 0.033;
            double score = ((double) initialScore ) * brakingPercentage * speedingPercentage;
            overallScore = (int) score;
            if (overallScore < 0) overallScore=0;
            overallRating = String.valueOf(overallScore) + " out of 10";
        } else {
            overallRating = "Drive just a bit more first :3";
        }

        rewardOutput();

        brakingRatingTextView.setText(brakeR);
        System.out.println(speedR);
        speedRatingTextView.setText(speedR);
        ratingTextView.setText(overallRating);
        ratingMsgTextView.setText(ratingString);

        double tripConsumption = getIntent().getDoubleExtra("fuelConsumed", 0);
        double tripEmission = getIntent().getDoubleExtra("co2Emitted", 0);

        fuelConsumption.setText("You consumed " + tripConsumption + "L of fuel");
        emissions.setText("Your CO2 emissions were: " + tripEmission + "g/km");
        totaldistance.setText("Trip Distance: " + AccelerationManagerActivity.sumDistance + "km");

    }

    public void rewardOutput(){

        int score = overallScore;
        switch (score) {
            case 1: ratingString = "You are absolutely unsexy. Please think of Mother Nature";
                break;
            case 2: ratingString = "That was so unsexy.";
                break;
            case 3: ratingString = "You are absolutely unsexy. Please think of Mother Nature";
                break;
            case 4: ratingString = "You're starting to turn people off with you're bad driving and CO2 emissions.";
                break;
            case 5: ratingString = "You're about borderline sexy right now";
                break;
            case 6: ratingString = "Hey, it'd be a lot sexier if you took softer brakes!";
                break;
            case 7: ratingString = "You could do better, reduce those CO2 emissions!";
                break;
            case 8: ratingString = "Wow! That was some sexy driving.";
                break;
            case 9: ratingString = "You have a bright future in being sexy.";
                break;
            case 10: ratingString = "You are absolutely sexy.";
                break;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
