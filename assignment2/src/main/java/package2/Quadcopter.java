/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 2
 * Friday, February 21, 2020
 *
 * Phase I
 * */

package package2;

/**
 * Class that build an Quadcopter instance
 *
 * @author Philippe Carrier
 */
public class Quadcopter extends Helicopter {

    private int maxFlyingSpeed;

    /**
     * Default constructor
     */
    public Quadcopter() {
        this.maxFlyingSpeed = 0;
    }

    /**
     * Parametrize constructor
     *
     * @param brand     The brand of the Quadcopter
     * @param price     The price of the Quadcopter
     * @param horsePower        The horse power of the Quadcopter
     * @param numOfCylinders    The number of cylinders in the Quadcopter's engine
     * @param creationYear      The year the Quadcopter was built
     * @param passengerCapacity The amount of passenger the Quadcopter can carry
     * @param maxFlyingSpeed    The max flying speed of the Quadcopter
     */
    public Quadcopter(String brand,
                      double price,
                      int horsePower,
                      int numOfCylinders,
                      int creationYear,
                      int passengerCapacity,
                      int maxFlyingSpeed) {
        super(brand, price, horsePower, numOfCylinders, creationYear, passengerCapacity);
        this.maxFlyingSpeed = maxFlyingSpeed;
    }

    /**
     * Copy constructor
     * @param quadcopter    @Quadcopter - Object to be copy
     */
    public Quadcopter(Quadcopter quadcopter) {
        super(quadcopter);
        this.maxFlyingSpeed = quadcopter.maxFlyingSpeed;
    }

    /**
     * Getter for maxFlyingSpeed variable
     * @return The max flying speed of the quadcopter
     */
    public int getMaxFlyingSpeed() {
        return maxFlyingSpeed;
    }

    /**
     * Setter for maxFlyingSpeed variable
     * @param maxFlyingSpeed - The max flying speed of the quadcopter
     */
    public void setMaxFlyingSpeed(int maxFlyingSpeed) {
        this.maxFlyingSpeed = maxFlyingSpeed;
    }

    /**
     * An overview of the quadcopter instance
     * @return  String - the quadcopter's variables
     */
    @Override
    public String toString() {
        return super.toString() + "The max flying speed of this machine is " + maxFlyingSpeed + " km/h. ";
    }

    /**
     * Compare an object to an quadcopter instance
     * @param o an object that will be compare to an instance of quadcopter
     * @return  true if it's equal false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Quadcopter quadcopter = (Quadcopter) o;
        return maxFlyingSpeed == quadcopter.maxFlyingSpeed;
    }

}
