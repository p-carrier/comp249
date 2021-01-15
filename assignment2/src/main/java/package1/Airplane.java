/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 2
 * Friday, February 21, 2020
 *
 * Phase I
 * */

package package1;

import package6.FlyingObject;

import java.util.Locale;

/**
 * Class that build an Airplane instance
 *
 * @author Philippe Carrier
 */
public class Airplane extends FlyingObject {

    private String brand;
    private double price;
    private int horsePower;

    /**
     * Default constructor
     */
    public Airplane() {
        this("", 0.0, 0);
    }

    /**
     * Parametrize constructor
     * @param brand     The brand of the airplane
     * @param price     The price of the airplane
     * @param horsePower    The horse power of the airplane
     */
    public Airplane(String brand, double price, int horsePower) {
        this.brand = brand;
        this.price = price;
        this.horsePower = horsePower;
    }

    /**
     * Copy constructor
     * @param airplane    @Airplane - Object to copy
     */
    public Airplane(Airplane airplane) {
        this(airplane.brand, airplane.price, airplane.horsePower);
    }

    /**
     * Getter for brand variable
     * @return the brand of the airplane
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Setter for brand variable
     * @param brand - the brand of the airplane
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Getter for price variable
     * @return The price of the airplane
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for price variable
     * @param price - The price of the airplane
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for horsePower variable
     * @return The horsepower of the airplane
     */
    public int getHorsePower() {
        return horsePower;
    }

    /**
     * Setter for horsePower variable
     * @param horsePower - The horsepower of the airplane
     */
    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    /**
     * An overview of the airplane instance
     * @return  String - the airplane's variables
     */
    @Override
    public String toString() {
        return String.format(Locale.CANADA,"This " + getClass().getSimpleName() + " is manufactured by %s. " +
                "It costs $%,.2f and can develop %,d horsepower. ", brand, price, horsePower);
    }

    /**
     * Compare an object to an airplane instance
     * @param o an object that will be compare to an instance of airplane
     * @return  true if it's equal false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return Double.compare(airplane.price, price) == 0 &&
                horsePower == airplane.horsePower &&
                brand.equals(airplane.brand);
    }
}
