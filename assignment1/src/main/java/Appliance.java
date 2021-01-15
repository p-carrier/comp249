/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 1
 * Friday, January 31, 2020
 *
 * Question 1 Part I
 * */

/**
 * Class that build an Appliance instance
 *
 * @author Philippe Carrier
 */
public class Appliance {

    private String type;
    private String brand;
    private final long serialNumber;
    private double price;
    public final static String[] TYPES = {
            "Fridge",
            "Air",
            "Conditioner",
            "Washer",
            "Dryer",
            "Freezer",
            "Stove",
            "Dishwasher",
            "Water",
            "Heaters",
            "Microwave"
    };
    private static long serialNumberValue = 1_000_000;
    private static int numberOfAppliances = 0;

    /**
     * Default constructor of the Appliance class
     */
    public Appliance() {
        this("", "", 0.0);
    }

    /**
     * 3 parameters constructor for the Appliance class
     * @param type  String - the type of the appliance
     * @param brand String - the brand of the appliance
     * @param price double - the price of the appliance
     */
    public Appliance(String type, String brand, double price) {
        this.type = type;
        this.brand = brand;
        this.serialNumber = serialNumberValue++;
        this.price = price;
        numberOfAppliances++;
    }

    /**
     * Copy constructor of the Appliance class
     * @param appliance Appliance - the appliance instance to copy
     */
    public Appliance(Appliance appliance) {
        this.type = appliance.type;
        this.brand = appliance.brand;
        this.serialNumber = serialNumberValue++;
        this.price = appliance.price;
        numberOfAppliances++;
    }

    /**
     * Check whether the type enter is a valid one
     * @param type String - the type of the appliance
     * @return true if the type is in @TYPES false otherwise
     */
    public static boolean isTypeValid(String type) {
        for (String t : TYPES) {
            if (t.equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the price enter is greater or equal to 1
     * @param price double - the value of the appliances in dollar
     * @return  true if the price is valid false otherwise
     */
    public static boolean isPriceValid(double price) {
        return price >= 1;
    }

    /**
     * Getter for the type variable
     * @return String - the type of the appliance
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for the type variable
     * @param type String - the new type of the appliance
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for the brand variable
     * @return String - the brand of the appliance
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Setter for the brand variable
     * @param brand String - the new brand of the appliance
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Getter for the serial number variable
     * @return long - the serial number of the appliance
     */
    public long getSerialNumber() {
        return serialNumber;
    }

    /**
     * Getter for the price variable
     * @return double - the price of the appliance
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for the price variable
     * @param price double - the new price of the appliance
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for the number of appliances variable
     * @return  int - the number of appliance created
     */
    public static int findNumberOfCreatedAppliances() {
        return numberOfAppliances;
    }

    /**
     * An overview of the appliance instance
     * @return String - the appliances' variables
     */
    public String toString() {
        return "Appliance Serial # "+ serialNumber +
                "\nBrand: " + brand +
                "\nType: " + type +
                "\nPrice: " + price;
    }

    /**
     * Compare an object to an appliance instance
     * @param o an object that will be compare to an instance of Appliance
     * @return  true if it's equal false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) 
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Appliance appliance = (Appliance) o;
        return price == appliance.price &&
                type.equals(appliance.type) &&
                brand.equals(appliance.brand);
    }

}
