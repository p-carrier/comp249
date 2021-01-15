/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 2
 * Friday, February 21, 2020
 *
 * Phase II
 * */

package driver;

import package6.FlyingObject;

/**
 * Program that creates a copy of an array of Flying objects and compare the original array with the copied array
 * to determine if the copied array is properly made.
 *
 * @author Philippe Carrier
 */
public class FlyingObjectDriver2 {

    /**
     * Deep copy of the a FlyingObject array
     * @param flyingObjects FlyingObject[] -  array of flying objects
     * @return  copy of the flyingObject array
     */
    public static FlyingObject[] copyFlyingObjects(FlyingObject[] flyingObjects) {
        FlyingObject[] flyingObjectsCopy = new FlyingObject[flyingObjects.length];
        for (int i = 0; i < flyingObjects.length; i++) {
            flyingObjectsCopy[i] = new FlyingObject(flyingObjects[i]);
        }
        return flyingObjectsCopy;
    }

    /**
     * Driver class of the FlyingObjectDriver2
     * @param args empty
     */
    public static void main(String[] args) {

        System.out.println("Welcome to FlyingObjectDriver program");

        System.out.println();
        System.out.println("Creation of the flying objects...");
        System.out.println();
        System.out.println();

        FlyingObject[] flyingObjects = FlyingObjectDriver.createFlyingObjects();

        System.out.println();
        System.out.println("Copying the flyingObjects array...");
        System.out.println();

        FlyingObject[] flyingObjectsCopy = copyFlyingObjects(flyingObjects);
        int numDeepCopy = 0;

        System.out.println();
        System.out.println("Comparison with the original array...");
        System.out.println();

        for (int i = 0; i < flyingObjects.length; i++) {
            System.out.println("Flying object " + (i + 1));
            System.out.println("\tOriginal: ");
            System.out.println("\t" + flyingObjects[i].toString());
            System.out.println();
            System.out.println("\tCopy: ");
            System.out.println("\t" + flyingObjectsCopy[i].toString());
            System.out.println();
            System.out.println();

            if(flyingObjects[i].equals(flyingObjectsCopy[i]))
                numDeepCopy++;
        }

        if(numDeepCopy < flyingObjects.length) {
            System.out.println("The copy was not made correctly.");
            String explanation = "Since there is no way to check the type of class being copy inside the array of " +
                    "the copyFlyingObjects method \n(according to the assignment), the copy of the array will be " +
                    "filled with the superclass of all the class in \nthe array (in this case the FlyingObject class). " +
                    "Obviously, the result that was expected is not this one since \nthe state of the object inside " +
                    "the first array (flyingObjects) have not been copied to the object inside the \ncopy of the array " +
                    "(flyingObjectsCopy).\n" +
                    "In order to do it properly, the clone method could be use (it forces the creation of the object " +
                    "through the \ncopy constructor of the right class) or it could also be done by checking the class " +
                    "of the copied object and\nuse the appropriate constructor.";
            System.out.println();
            System.out.println(explanation);
            System.out.println();
        } else {
            System.out.println("The copy was made correctly.");
        }

        System.out.println();
        System.out.println("Thanks for using this program.");
        System.out.println();
        System.out.println("Closing program...");

    }

}
