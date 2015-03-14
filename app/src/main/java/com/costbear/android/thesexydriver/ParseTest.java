package com.costbear.android.thesexydriver;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ParseTest extends ActionBarActivity {

    CSVFile csvFile;
    InputStream inputStream;
    List<Car> carList;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_test);

        inputStream = getResources().openRawResource(R.raw.cardatabase2000to2014);
        csvFile = new CSVFile(inputStream);

        carList = csvFile.read();

        text = (TextView) findViewById(R.id.text);

        text.setText(carList.get(0).getMake());



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parse_test, menu);
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
