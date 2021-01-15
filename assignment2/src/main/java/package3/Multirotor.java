/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 2
 * Friday, February 21, 2020
 *
 * Phase I
 * */

package package3;

import package2.Helicopter;

/**
 * Class that build an Multirotor instance
 *
 * @author Philippe Carrier
 */
public class Multirotor extends Helicopter {

    private int numOfRotors;

    /**
     * Default constructor
     */
    public Multirotor() {
        this.numOfRotors = 0;
    }

    /**
     * Parametrize constructor
     *
     * @param brand     The brand of the Multirotor
     * @param price     The price of the Multirotor
     * @param horsePower        The horse power of the Multirotor
     * @param numOfCylinders    The number of cylinders in the engine of the Multirotor
     * @param creationYear      The year the Multirotor was built
     * @param passengerCapacity The amount of passenger the Multirotor can carry
     * @param numOfRotors       The number of rotors on the Multirotor
     */
    public Multirotor(String brand,
                      double price,
                      int horsePower,
                      int numOfCylinders,
                      int creationYear,
                      int passengerCapacity,
                      int numOfRotors) {
        super(brand, price, horsePower, numOfCylinders, creationYear, passengerCapacity);
        this.numOfRotors = numOfRotors;
    }

    /**
     * Copy constructor
     * @param multirotor    @Multirotor - Object to copy
     */
    public Multirotor(Multirotor multirotor) {
        super(multirotor);
        this.numOfRotors = multirotor.numOfRotors;
    }

    /**
     * Getter for numOfRotors variable
     * @return The number of rotor
     */
    public int getNumOfRotors() {
        return numOfRotors;
    }

    /**
     * Setter for numOfRotors variable
     * @param numOfRotors - The number of rotor
     */
    public void setNumOfRotors(int numOfRotors) {
        this.numOfRotors = numOfRotors;
    }

    /**
     * An overview of the multirotor instance
     * @return  String - the multirotor's variables
     */
    @Override
    public String toString() {
        return super.toString() + "This " + getClass().getSimpleName() + " has " + numOfRotors + " rotors." ;
    }

    /**
     * Compare an object to an multirotor instance
     * @param o an object that will be compare to an instance of multirotor
     * @return  true if it's equal false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Multirotor multirotor = (Multirotor) o;
        return numOfRotors == multirotor.numOfRotors;
    }

}
