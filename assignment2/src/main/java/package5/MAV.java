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
 * Class that build an MAV instance
 *
 * @author Philippe Carrier
 */
public class MAV extends UAV {

    private String model;
    private double size;

    /**
     * Default constructor
     */
    public MAV() {
        this.model = "";
        this.size = 0.0;
    }

    /**
     * Parametrize constructor
     * @param weight    The weight of the MAV
     * @param price     The price of the MAV
     * @param model     The model of the MAV
     * @param size      The size of the MAV
     */
    public MAV(double weight, double price, String model, double size) {
        super(weight, price);
        this.model = model;
        this.size = size;
    }

    /**
     * Copy constructor
     * @param mav    @Mav - Object to copy
     */
    public MAV(MAV mav) {
        super(mav);
        this.model = mav.model;
        this.size = mav.size;
    }

    /**
     * Getter for model variable
     * @return The model of the MAV
     */
    public String getModel() {
        return model;
    }

    /**
     * Setter for model variable
     * @param model - The model of the MAV
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter for size variable
     * @return The size of the MAV
     */
    public double getSize() {
        return size;
    }

    /**
     * Setter for size variable
     * @param size - The size of the MAV
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * An overview of the MAV instance
     * @return  String - the MAV's variables
     */
    @Override
    public String toString() {
        return super.toString() + "With a size of " + size + "cm, this " + getClass().getSimpleName() +
                " model " + model + " will fit your need. ";
    }

    /**
     * Compare an object to an MAV instance
     * @param o an object that will be compare to an instance of MAV
     * @return  true if it's equal false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MAV mav = (MAV) o;
        return Double.compare(mav.size, size) == 0 &&
                model.equals(mav.model);
    }

}
