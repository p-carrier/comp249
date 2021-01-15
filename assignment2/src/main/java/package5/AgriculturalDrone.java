/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 2
 * Friday, February 21, 2020
 *
 * Phase I
 * */

package package5;

import package4.UAV;

/**
 * Class that build an AgriculturalDrone instance
 *
 * @author Philippe Carrier
 */
public class AgriculturalDrone extends UAV {

    private String brand;
    private int carryCapacity;

    /**
     * Default constructor
     */
    public AgriculturalDrone() {
        this.brand = "";
        this.carryCapacity = 0;
    }

    /**
     * Parametrize constructor
     * @param weight    The weight of the agricultural drone
     * @param price     The price of the agricultural drone
     * @param brand     The brand of the agricultural drone
     * @param carryCapacity     The amount of liquid the agricultural drone can carry (in Liters)
     */
    public AgriculturalDrone(double weight, double price, String brand, int carryCapacity) {
        super(weight, price);
        this.brand = brand;
        this.carryCapacity = carryCapacity;
    }

    /**
     * Copy constructor
     * @param agriculturalDrone    @AgriculturalDrone - Object to copy
     */
    public AgriculturalDrone(AgriculturalDrone agriculturalDrone) {
        super(agriculturalDrone);
        this.brand = agriculturalDrone.brand;
        this.carryCapacity = agriculturalDrone.carryCapacity;
    }

    /**
     * Getter for brand variable
     * @return The brand of the agricultural drone
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Setter for brand variable
     * @param brand - The brand of the agricultural drone
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Getter for carryCapacity variable
     * @return The carry capacity of the agricultural drone
     */
    public int getCarryCapacity() {
        return carryCapacity;
    }

    /**
     * Setter for carryCapacity variable
     * @param carryCapacity - The carry capacity of the agricultural drone
     */
    public void setCarryCapacity(int carryCapacity) {
        this.carryCapacity = carryCapacity;
    }

    /**
     * An overview of the agriculturalDrone instance
     * @return  String - the agriculturalDrone's variables
     */
    @Override
    public String toString() {
        return "This " + getClass().getSimpleName() + " manufactured by " + brand + " has a carry capacity of " +
                carryCapacity + " liters. " + super.toString();
    }

    /**
     * Compare an object to an agriculturalDrone instance
     * @param o an object that will be compare to an instance of agriculturalDrone
     * @return  true if it's equal false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AgriculturalDrone agriculturalDrone = (AgriculturalDrone) o;
        return carryCapacity == agriculturalDrone.carryCapacity &&
                brand.equals(agriculturalDrone.brand);
    }

}
