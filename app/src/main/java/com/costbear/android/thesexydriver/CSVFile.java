package com.costbear.android.thesexydriver;

/**
 * Created by vincentchan on 15-03-13.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVFile {
    InputStream inputStream;

    public CSVFile(InputStream inputStream){

        this.inputStream = inputStream;
    }

    public List<Car> read(){
        List<Car> resultList = new ArrayList<Car>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {

            Car car;

            String csvLine;
            while ((csvLine = reader.readLine()) != null) {


                String[] row = csvLine.split(",");

                int year = Integer.parseInt(row[0]);

                String make = row[1];
                String model = row[2];
                double fuelConsumption = Double.parseDouble(row[8]);
                int emissions = Integer.parseInt(row[12]);

                car = new Car(year, make, model, fuelConsumption, emissions);

                resultList.add(car);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return resultList;
    }
}