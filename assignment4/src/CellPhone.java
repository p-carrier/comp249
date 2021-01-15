/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 4
 * Sunday, April 19, 2020
 *
 * Part 2
 * */

import java.util.Scanner;

/**
 * Class for creating a CellPhone instance
 *
 * @author Philippe Carrier
 */
public class CellPhone {

    long serialNum;
    String brand;
    int year;
    double price;

    public static Scanner scanner = new Scanner(System.in);

    /**
     * Constructor with 4 parameters
     *
     * @param serialNum     long - the serial number of the cellphone
     * @param brand     String - the brand of the cellphone
     * @param year      int - the year the cellphone was released
     * @param price     double - the price of the cellphone
     */
    public CellPhone(long serialNum, String brand, int year, double price) {
        this.serialNum = serialNum;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    /**
     * Copy constructor with new serial number
     *
     * @param cellPhone     CellPhone - The cellphone instance to copy
     * @param serialNum     long - the serial number of the copy
     */
    public CellPhone(CellPhone cellPhone, long serialNum) {
        this(serialNum, cellPhone.brand, cellPhone.year, cellPhone.price);
    }

    /**
     * Deep copy of the current CellPhone instance
     *
     * @return  CellPhone - a cellphone instance
     */
    protected CellPhone clone() {
        System.out.print("What will be the serial number of this new device? ");
        long serialNumber = scanner.nextLong();
        return new CellPhone(this, serialNumber);
    }


    /**
     * An overview of the CellPhone instance
     *
     * @return  String - the values of the cellphone
     */
    @Override
    public String toString() {
        return "[" + serialNum + ": " + brand + " " + price + "$ " + year + "]";
    }

    /**
     * Compare an object with the cellphone instance
     *
     * @param o     Object - The object being compare to the cellphone instance
     * @return      boolean - Whether the object is equal to the cellphone instance or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPhone cellPhone = (CellPhone) o;
        return year == cellPhone.year &&
                Double.compare(cellPhone.price, price) == 0 &&
                brand.equals(cellPhone.brand);
    }
}
