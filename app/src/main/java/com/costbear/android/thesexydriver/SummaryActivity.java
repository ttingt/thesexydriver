package com.costbear.android.thesexydriver;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SummaryActivity extends ActionBarActivity {

    int brakePtsCount;
    int brakeRatingSoFar;
    int speedPtsCount;
    int speedRatingSoFar;

    TextView brakingRatingTextView;
    TextView ratingTextView;
    TextView speedRatingTextView;
    TextView ratingMsgTextView;

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
        }

        if (brakePtsCount >= 3 && speedPtsCount >= 1) {
            overallRating = String.valueOf(Math.round(brakeRatingSoFar/brakePtsCount*.4 + speedRatingSoFar/speedPtsCount*.6));
        } else {
            overallRating = "Drive just a bit more first :3";
        }

        brakingRatingTextView.setText(brakeR);
        System.out.println(speedR);
        speedRatingTextView.setText(speedR);
        ratingTextView.setText(overallRating);

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
