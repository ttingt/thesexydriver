package com.costbear.android.thesexydriver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ha on 14/03/15.
 */
public class csvWriteAndReader {

    private ArrayList<String[]> dataList;

    public csvWriteAndReader() {
    }


    public void writeData()
    {
        try
        {
            FileWriter writer = new FileWriter("/res/raw/DrivingData.csv", true);

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

        String csvFile = "/res/raw/DrivingData.csv";
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
