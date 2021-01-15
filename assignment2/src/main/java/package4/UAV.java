/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 2
 * Friday, February 21, 2020
 *
 * Phase I
 * */

package package4;

import package6.FlyingObject;

import java.util.Locale;

/**
 * Class that build an UAV instance
 *
 * @author Philippe Carrier
 */
public class UAV extends FlyingObject {

    private double weight;
    private double price;

    /**
     * Default constructor
     */
    public UAV() {
        this(0, 0.0);
    }

    /**
     * Parametrize constructor
     * @param weight    The weight of the UAV
     * @param price     The price of the UAV
     */
    public UAV(double weight, double price) {
        this.weight = weight;
        this.price = price;
    }

    /**
     * Copy constructor
     * @param uav    @UAV - Object to copy
     */
    public UAV(UAV uav) {
        this(uav.weight, uav.price);
    }

    /**
     * Getter for weight variable
     * @return The weight of the UAV
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Setter for weight variable
     * @param weight - The weight of the UAV
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Getter for price variable
     * @return The price of the UAV
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for price variable
     * @param price - The price of the UAV
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * An overview of the uav instance
     * @return  String - the uav' variables
     */
    @Override
    public String toString() {
        return String.format(Locale.CANADA,
                "At a cost of $%,.2f, this " + getClass().getSimpleName() + " weight %,.2fkg. ",
                price,
                weight);
    }

    /**
     * Compare an object to an uav instance
     * @param o an object that will be compare to an instance of uav
     * @return  true if it's equal false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UAV uav = (UAV) o;
        return Double.compare(uav.weight, weight) == 0 &&
                Double.compare(uav.price, price) == 0;
    }

}
