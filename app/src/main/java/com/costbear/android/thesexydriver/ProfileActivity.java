package com.costbear.android.thesexydriver;

import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
//import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
//import android.widget.AdapterView.OnItemClickListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ProfileActivity extends ActionBarActivity {

    Button done;
    EditText year, make, model;

    CSVFile csvFile;
    InputStream inputStream;
    List<Car> carList;

    ListView listView;

    ItemArrayAdapter itemArrayAdapter;


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
                //model = (EditText) findViewById(R.id.modelField);

                String yearEntered = year.getText().toString();
                String makeEntered = make.getText().toString();
                //String modelEntered = model.getText().toString();

                List<Car> filteredCarList = new ArrayList<Car>();

                for (Car c : carList) {
                    if (yearEntered.equals(String.valueOf(c.getYear()))) {
                        filteredCarList.add(c);
                    }
                }

                List<Car> filteredByMake = new ArrayList<Car>();

                for (Car c : filteredCarList) {
                    if (makeEntered.equalsIgnoreCase(c.getMake())) {
                        filteredByMake.add(c);
                    }
                }

                List<String> carModelsList = new ArrayList<String>();

                for (Car c : filteredCarList) {
                    String model;
                    model = c.getModel();

                    carModelsList.add(model);

                }

                listView = (ListView) findViewById(R.id.listview);
                itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.row_models);

                Parcelable state = listView.onSaveInstanceState();
                listView.setAdapter(itemArrayAdapter);
                listView.onRestoreInstanceState(state);

                for (Car c : filteredByMake) {
                    itemArrayAdapter.add(c);
                }

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //String string = parent.getItemAtPosition(position);

                        Car car = (Car) itemArrayAdapter.getItem(position);

                        int year = (Integer) car.getYear();
                        String make = (String) car.getMake();
                        String model = (String) car.getModel();
                        int cylinders = (Integer) car.getCylinders();
                        String transmission = (String) car.getTransmission();
                        double fuelConsumption = (Double) car.getFuelConsumption();
                        int emissions = (Integer) car.getEmissions();


                        Toast.makeText(getApplicationContext(), model, Toast.LENGTH_SHORT).show();

                    }
                });


            }


        });

//

//
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
//            {
//                String data = (String) arg0.getItemAtPosition(arg2);
//
//                Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
//
//
//            }
//
//        });

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
