package com.costbear.android.thesexydriver;

/**
 * Created by vincentchan on 15-03-13.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVFile {
    InputStream inputStream;
    private ArrayList<String[]> dataList;
    List<Car> resultList;


    public CSVFile() {

    }
    public CSVFile(InputStream inputStream){

        this.inputStream = inputStream;
    }

    public List<Car> read(){
        resultList = new ArrayList<Car>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {

            Car car;

            String csvLine;
            reader.readLine();
            reader.readLine();
            while ((csvLine = reader.readLine()) != null) {


                String[] row = csvLine.split(",");

                int year = Integer.parseInt(row[0]);
                String make = row[1];
                String model = row[2];
                int cylinders = Integer.parseInt(row[5]);
                String transmission = row[6];
                double fuelConsumption = Double.parseDouble(row[8]);
                int emissions = Integer.parseInt(row[12]);

                car = new Car(year, make, model, cylinders, transmission, fuelConsumption, emissions);

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

    public void writeData()
    {
        try
        {
            FileWriter writer = new FileWriter("/res/raw/drivingdata.csv", true);

            writer.append("MKYONG");
            writer.append(',');
            writer.append("26");
            writer.append('\n');
            //generate whatever data you want

            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }


    public void readData() {

        String csvFile = "/res/raw/drivingdata.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] drivingData = line.split(cvsSplitBy);
                dataList.add(drivingData);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}