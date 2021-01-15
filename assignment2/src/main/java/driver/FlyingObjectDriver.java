/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 2
 * Friday, February 21, 2020
 *
 * Phase I
 * */

package driver;

import package1.Airplane;
import package2.Helicopter;
import package2.Quadcopter;
import package3.Multirotor;
import package4.UAV;
import package5.AgriculturalDrone;
import package5.MAV;
import package6.FlyingObject;

/**
 * Program that creates an array of flying objects then finds the cheapest and the second cheapest
 * flying object inside the array.
 *
 * @author Philippe Carrier
 */
public class FlyingObjectDriver {

    /**
     * Driver class of the FlyingObjectDriver
     * @param args empty
     */
    public static void main(String[] args) {

        System.out.println("Welcome to FlyingObjectDriver program");

        System.out.println();
        System.out.println("Creation of the flying objects...");
        System.out.println();
        System.out.println();

        FlyingObject[] flyingObjects = createFlyingObjects();

        FlyingObject leastExpensive = flyingObjects[0];
        FlyingObject secondLeastExpensive = leastExpensive;
        double cheapestPrice = getPrice(flyingObjects[0]);
        double secondCheapestPrice = cheapestPrice;
        int cheapestLocation = 0;
        int secondCheapestLocation = 0;

        for (int i = 1; i < flyingObjects.length; i++) {
            double price = getPrice(flyingObjects[i]);
            if(i == 1) {
                if (price < cheapestPrice) {
                    cheapestPrice = price;
                    leastExpensive = flyingObjects[i];
                    cheapestLocation = i;
                } else {
                    secondCheapestPrice = price;
                    secondLeastExpensive = flyingObjects[i];
                    secondCheapestLocation = i;
                }
            } else {
                if (price < cheapestPrice) {
                    secondCheapestPrice = cheapestPrice;
                    cheapestPrice = price;
                    secondLeastExpensive = leastExpensive;
                    leastExpensive = flyingObjects[i];
                    secondCheapestLocation = cheapestLocation;
                    cheapestLocation = i;
                } else if(price < secondCheapestPrice){
                    secondCheapestPrice = price;
                    secondLeastExpensive = flyingObjects[i];
                    secondCheapestLocation = i;
                }
            }
        }

        System.out.println("Searching for the two cheapest flying objects...");
        System.out.println();
        System.out.println("The cheapest flying object is located at position " + cheapestLocation + " in the flying objects array.");
        System.out.println("Information:");
        System.out.println("\t" + leastExpensive.toString());
        System.out.println("The second cheapest flying object is located at position " + secondCheapestLocation + " in the flying objects array.");
        System.out.println("Information:");
        System.out.println("\t" + secondLeastExpensive.toString());

        System.out.println();
        System.out.println("Thanks for using this program.");
        System.out.println();
        System.out.println("Closing program...");

    }

    /**
     * Method that get the price of the different flying object base on their type.
     *
     * @param o    FlyingObject - A flying object
     * @return  double - The price of the flyingObject
     */
    private static double getPrice(FlyingObject o) {
        if(o instanceof Airplane) {
            Airplane airplane = (Airplane) o;
            return airplane.getPrice();
        }
        UAV uav = (UAV) o;
        return uav.getPrice();
    }

    /**
     * Creates an array containing different types of flying objects. The output will
     * contain 15 flying objects from 7 different types of flying objects.
     *
     * @return an array of flying objects
     */
    public static FlyingObject[] createFlyingObjects() {
        System.out.println("Airplane 1:");
        Airplane airplane1 = new Airplane("Boeing", 70_000_000.00, 55_000);
        System.out.println("\t" + airplane1.toString());
        System.out.println("Airplane 2:");
        Airplane airplane2 = new Airplane();
        airplane2.setBrand("Airbus");
        airplane2.setPrice(75_000_000.00);
        airplane2.setHorsePower(58_000);
        System.out.println("\t" + airplane2.toString());

        System.out.println("Helicopter 1:");
        Helicopter helicopter1 = new Helicopter("Boeing", 15_000_000.00, 38_000, 16, 2010, 8);
        System.out.println("\t" + helicopter1.toString());
        System.out.println("Helicopter 2:");
        Helicopter helicopter2 = new Helicopter(helicopter1);
        helicopter2.setPrice(12_500_000.00);
        helicopter2.setHorsePower(35_000);
        helicopter2.setCreationYear(2011);
        helicopter2.setPassengerCapacity(6);
        System.out.println("\t" + helicopter2.toString());

        System.out.println("Quadcopter 1:");
        Quadcopter quadcopter1 = new Quadcopter();
        quadcopter1.setBrand("Boeing");
        quadcopter1.setPrice(9_000_000.00);
        quadcopter1.setHorsePower(15_000);
        quadcopter1.setNumOfCylinders(8);
        quadcopter1.setCreationYear(2018);
        quadcopter1.setPassengerCapacity(2);
        quadcopter1.setMaxFlyingSpeed(100);
        System.out.println("\t" + quadcopter1.toString());
        System.out.println("Quadcopter 2:");
        Quadcopter quadcopter2 = new Quadcopter(quadcopter1);
        quadcopter2.setPrice(7_500_000.00);
        quadcopter2.setHorsePower(10_000);
        quadcopter2.setCreationYear(2017);
        quadcopter2.setMaxFlyingSpeed(85);
        System.out.println("\t" + quadcopter2.toString());

        System.out.println("Multirotor 1:");
        Multirotor multirotor1 = new Multirotor("Boeing", 21_000_000.00, 42_000, 22, 2015, 20, 2);
        System.out.println("\t" + multirotor1.toString());
        System.out.println("Multirotor 2:");
        Multirotor multirotor2 = new Multirotor();
        multirotor2.setBrand("Boeing");
        multirotor2.setPrice(9_000_000.00);
        multirotor2.setHorsePower(15_000);
        multirotor2.setNumOfCylinders(8);
        multirotor2.setCreationYear(2018);
        multirotor2.setPassengerCapacity(2);
        multirotor2.setNumOfRotors(4);
        System.out.println("\t" + multirotor2.toString());

        System.out.println("UAV 1:");
        UAV uav1 = new UAV(1500.0, 3_500_000.0);
        System.out.println("\t" + uav1.toString());
        System.out.println("UAV 2:");
        UAV uav2 = new UAV();
        uav2.setPrice(2_750_000.0);
        uav2.setWeight(1250.50);
        System.out.println("\t" + uav2.toString());
        System.out.println("UAV 3:");
        UAV uav3 = new UAV(uav1);
        System.out.println("\t" + uav3.toString());

        System.out.println("Agricultural Drone 1:");
        AgriculturalDrone agriculturalDrone1 = new AgriculturalDrone();
        agriculturalDrone1.setWeight(1200.0);
        agriculturalDrone1.setPrice(5_500_000.0);
        agriculturalDrone1.setBrand("Lockheed");
        agriculturalDrone1.setCarryCapacity(750);
        System.out.println("\t" + agriculturalDrone1.toString());
        System.out.println("Agricultural Drone 2:");
        AgriculturalDrone agriculturalDrone2 = new AgriculturalDrone(1300.0, 4_400_000.0, "Lockheed", 500);
        System.out.println("\t" + agriculturalDrone2.toString());

        System.out.println("MAV 1:");
        MAV mav1 = new MAV(2, 45_000.00, "fly", 2);
        System.out.println("\t" + mav1.toString());
        System.out.println("MAV 2:");
        MAV mav2 = new MAV(mav1);
        mav2.setPrice(55_000.0);
        System.out.println("\t" + mav2.toString());

        System.out.println();
        System.out.println("Comparing some flying objects....");
        System.out.println("\tmav1 is " + (mav1.equals(mav2) ? "" : "not ") + "equal to mav2.");
        System.out.println("\tuav1 is " + (uav1.equals(uav3) ? "" : "not ") + "equal to uav3.");
        System.out.println("\tquadcopter1 is " + (quadcopter1.equals(quadcopter2) ? "" : "not ") + "equal to quadcopter2.");
        System.out.println("\thelicopter1 is " + (helicopter1.equals(helicopter2) ? "" : "not ") + "equal to helicopter2.");
        System.out.println("\tairplane1 is " + (airplane1.equals(airplane2) ? "" : "not ") + "equal to airplane2.");
        System.out.println();

        FlyingObject[] flyingObjects = new FlyingObject[15];
        flyingObjects[0] = airplane1;
        flyingObjects[1] = airplane2;
        flyingObjects[2] = helicopter1;
        flyingObjects[3] = helicopter2;
        flyingObjects[4] = quadcopter1;
        flyingObjects[5] = quadcopter2;
        flyingObjects[6] = multirotor1;
        flyingObjects[7] = multirotor2;
        flyingObjects[8] = uav1;
        flyingObjects[9] = uav2;
        flyingObjects[10] = uav3;
        flyingObjects[11] = agriculturalDrone1;
        flyingObjects[12] = agriculturalDrone2;
        flyingObjects[13] = mav1;
        flyingObjects[14] = mav2;

        return flyingObjects;
    }
}
