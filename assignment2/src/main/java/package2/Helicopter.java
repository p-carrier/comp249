/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 2
 * Friday, February 21, 2020
 *
 * Phase I
 * */

package package2;

import package1.Airplane;

/**
 * Class that build an Helicopter instance
 *
 * @author Philippe Carrier
 */
public class Helicopter extends Airplane {

    private int numOfCylinders;
    private int creationYear;
    private int passengerCapacity;

    /**
     * Default constructor
     */
    public Helicopter() {
        this.numOfCylinders = 0;
        this.creationYear = 0;
        this.passengerCapacity = 0;
    }

    /**
     * Parameterize constructor
     * @param brand The brand of the helicopter
     * @param price The price of the helicopter
     * @param horsePower    The horse power of the helicopter
     * @param numOfCylinders    The number of cylinder in the engine of the helicopter
     * @param creationYear  The year the helicopter was built
     * @param passengerCapacity The number of passenger the helicopter can carry
     */
    public Helicopter(String brand, double price, int horsePower, int numOfCylinders, int creationYear, int passengerCapacity) {
        super(brand, price, horsePower);
        this.numOfCylinders = numOfCylinders;
        this.creationYear = creationYear;
        this.passengerCapacity = passengerCapacity;
    }

    /**
     * Copy constructor
     * @param helicopter    @Helicopter - Object to copy
     */
    public Helicopter(Helicopter helicopter) {
        super(helicopter);
        this.numOfCylinders = helicopter.numOfCylinders;
        this.creationYear = helicopter.creationYear;
        this.passengerCapacity = helicopter.passengerCapacity;
    }

    /**
     * Getter for numOfCylinders variable
     * @return  the number of cylinders
     */
    public int getNumOfCylinders() {
        return numOfCylinders;
    }

    /**
     * Setter for numOfCylinders variable
     * @param numOfCylinders - The number of cylinders
     */
    public void setNumOfCylinders(int numOfCylinders) {
        this.numOfCylinders = numOfCylinders;
    }

    /**
     * Getter for the creationYear variable
     * @return  The creation year
     */
    public int getCreationYear() {
        return creationYear;
    }

    /**
     * Setter for the creatYear variable
     * @param creationYear - The creation year
     */
    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    /**
     * Getter for the passengerCapacity variable
     * @return  The passenger capacity
     */
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    /**
     * Setter for the passengerCapacity variable
     * @param passengerCapacity - The passenger capacity
     */
    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    /**
     * An overview of the Helicopter instance
     * @return  String - the helicopter's variables
     */
    @Override
    public String toString() {
        return super.toString() + "With its " + numOfCylinders + " cylinder engine block, the " +
                getClass().getSimpleName() + " can carry up to " + passengerCapacity + " passengers. This particular " +
                "model was created in " + creationYear + ". ";
    }

    /**
     * Compare an object to an helicopter instance
     * @param o an object that will be compare to an instance of helicopter
     * @return  true if it's equal false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Helicopter helicopter = (Helicopter) o;
        return numOfCylinders == helicopter.numOfCylinders &&
                creationYear == helicopter.creationYear &&
                passengerCapacity == helicopter.passengerCapacity;
    }

    
}
