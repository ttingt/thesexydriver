package com.costbear.android.thesexydriver;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ProfileActivity extends ActionBarActivity {

    Button done;
    EditText year, make, model;

    CSVFile csvFile;
    InputStream inputStream;
    List<Car> carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        done = (Button) findViewById(R.id.done);
        inputStream = getResources().openRawResource(R.raw.cardatabase2000to2014);
        csvFile = new CSVFile(inputStream);

        carList = csvFile.read();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = (EditText) findViewById(R.id.yearField);
                make = (EditText) findViewById(R.id.makeField);
                model = (EditText) findViewById(R.id.modelField);

                String yearEntered = year.getText().toString();
                String makeEntered = make.getText().toString();
                String modelEntered = model.getText().toString();

                List<Car> filteredCarList = new ArrayList<Car>();

                for (Car c : carList) {
                    if (yearEntered.equals(String.valueOf(c.getYear()))) {
                        filteredCarList.add(c);
                    }
                }

                for (int i = 0; i < filteredCarList.size(); i++) {
                    if (makeEntered.equals(filteredCarList.get(i).getMake())) {
                        filteredCarList.remove(i);
                    }
                }


            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
