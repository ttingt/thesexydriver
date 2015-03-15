package com.costbear.android.thesexydriver;

/**
 * Created by vincentchan on 15-03-13.
 */

public class Car {

    int year;
    String make;
    String model;
    int cylinders;
    String transmission;
    double fuelConsumption;
    int emissions;


    /**
     * Constructor
     * EFFECTS: creates a car with a year, make, model, fuel consumption and CO2 emissions
     * @param year - car year
     * @param make - car make (eg. Honda)
     * @param model - car model (eg. Acura)
     * @param fuelConsumption - city fuel consumption in L/100km
     * @param emissions - CO2 emissions in g/km
     */
    public Car(int year, String make, String model, int cylinders, String transmission, double fuelConsumption, int emissions) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.cylinders = cylinders;
        this.transmission = transmission;
        this.fuelConsumption = fuelConsumption;
        this.emissions = emissions;
    }

    public double fuelConsumed() {
        //TODO: AccelerationManagerActivity.sumDistance needs to be reimplemented
        return fuelConsumption / 100 * AccelerationManagerActivity.sumDistance * AccelerationManagerActivity.avgAccel;
    }

    public double co2Emitted() {
        //TODO: AccelerationManagerActivity.sumDistance needs to be reimplemented
        return emissions * AccelerationManagerActivity.sumDistance * AccelerationManagerActivity.avgAccel;
    }



    // GETTERS AND SETTERS
    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public int getCylinders() {
        return cylinders;
    }

    public String getTransmission() {
        return transmission;
    }

    public int getEmissions() {
        return emissions;
    }
}
