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
        return fuelConsumption / 100; // * distanceTraveled * accel multiplier
    }

    public double co2Emitted() {
        return emissions; // * distanceTravelled * accel multiplier
    }


    // GETTERS AND SETTERS
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public int getCylinders() {
        return cylinders;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}
