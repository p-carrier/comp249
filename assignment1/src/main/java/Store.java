/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 1
 * Friday, January 31, 2020
 *
 * Question 1 Part II
 * */

import java.util.Scanner;

/**
 * Program that let a store owner create, modify and view the inventory of his store. The store owner start
 * by defining the capacity of his inventory. Then, in option 1 of the menu, the owner will be able to
 * create the products that will be inside his inventory. In option 2 of the menu, the owner will be
 * able to modify the products inside his inventory. Option 3 and 4 will allow the owner to view his products
 * by filtering his inventory by brand or by price.
 *
 * @author Philippe Carrier
 */
public class Store {

    private static final int MAX_PASSWORD_ATTEMPT = 3;
    private static final int MAX_ATTEMPT = 4;
    private static final String PASSWORD = "c249";

    /**
     * Driver class of the store
     * @param args empty
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to your store application.");
        System.out.print("What is your maximal number of appliances? ");
        int maxAppliances = scanner.nextInt();

        Appliance[] inventory = new Appliance[maxAppliances];

        do {
            System.out.println("What do you want to do?");
            System.out.println("\t1. Enter new appliances (password required)");
            System.out.println("\t2. Change information of an appliance (password required)");
            System.out.println("\t3. Display all appliances by a specific brand");
            System.out.println("\t4. Display all appliances under a certain a price.");
            System.out.println("\t5. Quit");
            System.out.print("Please enter your choice: ");

            do {
                choice = scanner.nextInt();
            } while (invalidChoice(choice, 1, 5));

            switch (choice) {
                case 1:
                    option1(scanner, inventory);
                    break;
                case 2:
                    option2(scanner, inventory);
                    break;
                case 3:
                    option3(scanner, inventory);
                    break;
                case 4:
                    option4(scanner, inventory);
                    break;
            }
        } while(choice != 5);

        System.out.println("Thanks for using our services.");
        System.out.println("Closing the program....");
        scanner.close();
    }

    /**
     * Login  for the application
     * @param scanner for user input
     * @return true if the login was successful false otherwise
     */
    private static boolean login(Scanner scanner) {
        int attempt = 0;
        String password;
        do {
            if (attempt == 0) {
                System.out.print("Please enter your password: ");
            } else {
                System.out.print("Please enter a valid password: ");
            }
            password = scanner.next();
        } while(!password.equals(PASSWORD) && ++attempt < MAX_PASSWORD_ATTEMPT);
        return password.equals(PASSWORD);
    }

    /**
     * Selection of a valid value
     * @param choice  int - the value inputted by the user
     * @param lowerBound int - the smallest value the choice can be
     * @param upperBound int - the biggest value the choice can be
     * @return  true if invalid false otherwise
     */
    private static boolean invalidChoice(int choice, int lowerBound, int upperBound) {
        if (choice < lowerBound || choice > upperBound) {
            System.out.println("Please enter a valid value between " + lowerBound + " and " + upperBound + ": ");
            return true;
        }
        return false;
    }

    /**
     * Input process for the type of an appliance with validation of the type
     * @param scanner  Scanner - for user input
     * @return  String - a valid type for the appliance
     */
    private static String typeCreation(Scanner scanner) {
        String type = "";
        do {
            if (!type.isEmpty()) {
                System.out.print("\tYou have to enter a valid type. ");
            }
            type = scanner.next();
        } while (!Appliance.isTypeValid(type));
        return type;
    }

    /**
     * Input process for the price of an appliance with validation of the price
     * @param scanner Scanner - for user input
     * @return  double - a price greater or equal to 1
     */
    private static double priceCreation(Scanner scanner) {
        double price;
        boolean initialize = false;
        do {
            if (initialize) {
                System.out.print("\tYou have to enter a price greater or equal to 1$. ");
            }
            price = scanner.nextDouble();
            initialize = true;
        } while (!Appliance.isPriceValid(price));
        return price;
    }

    /**
     * Option 1 of the menu, insertion of product in the store with login
     * @param scanner Scanner - for user input
     * @param inventory Appliance[] - store appliances inventory
     */
    private static void option1(Scanner scanner, Appliance[] inventory) {
        int attempt = 0;
        boolean isLogged;

        do {
            if (attempt != 0) {
                System.out.println();
                System.out.println("You have enter 3 consecutive wrong password.");
                System.out.println("You have " + (MAX_ATTEMPT - attempt) + " attempt left.");
                System.out.println();
            }
            isLogged = login(scanner);
        } while(!isLogged && ++attempt < MAX_ATTEMPT);

        if(!isLogged) {
            System.out.println("Program detected suspicious activities and will terminate immediately!");
            System.exit(0);
        }
        System.out.println();
        System.out.println("Password valid. Continuing to option 1....");
        System.out.println();
        System.out.print("How many appliances do you want to enter? ");
        int nbrAppliances = scanner.nextInt();

        if (nbrAppliances > inventory.length) {
            System.out.println("There isn't enough space in your inventory to store that many appliances.");
        }

        int availableSpace = inventory.length - Appliance.findNumberOfCreatedAppliances();

        if (availableSpace < nbrAppliances) {
            System.out.println("There is space for only " + availableSpace + " appliances in your inventory.");
            System.out.println("Operation aborted.");
            returnMainMenu();
            return;
        }

        for (int i = inventory.length - availableSpace; i < inventory.length - availableSpace + nbrAppliances; i++) {
            System.out.println("Let's enter the information for appliance " + (i + 1) + ": ");
            Appliance appliance = new Appliance();
            System.out.print("\tWhat is the brand of this appliance: ");
            appliance.setBrand(scanner.next());
            System.out.print("\tWhat is the type of this appliance: ");
            appliance.setType(typeCreation(scanner));
            System.out.print("\tWhat is the price of this appliance: ");
            appliance.setPrice(priceCreation(scanner));
            System.out.println();
            inventory[i] = appliance;
            System.out.println(inventory[i]);
            System.out.println();
        }

        System.out.println(nbrAppliances + " appliances successfully entered in the inventory.");
        returnMainMenu();
    }

    /**
     * Option 2 of the menu, modification of product in the store with login
     * @param scanner Scanner - for user input
     * @param inventory Appliance[] - store appliances inventory
     */
    private static void option2(Scanner scanner, Appliance[] inventory) {
        if (!login(scanner)) {
            System.out.println();
            System.out.println("You provided the wrong password 3 times.");
            returnMainMenu();
            return;
        }

        int location = -1;
        do {
            System.out.print("What is the serial number of the appliance you would like to update? ");
            long serialNumber = scanner.nextLong();

            for (int i = 0; i < inventory.length; i++) {
                if (inventory[i] == null)
                    break;
                if (inventory[i].getSerialNumber() == serialNumber) {
                    location = i;
                    break;
                }
            }

            if (location == -1) {
                System.out.println("The serial number you enter is not valid.");
                System.out.println("Would you like to enter a new serial number? (Y/N) ");
                if (scanner.next().equals("N")) {
                    returnMainMenu();
                    break;
                }
            }

        } while (location == -1);

        if (location == -1) {
            return;
        }

        Appliance appliance = inventory[location];
        int choice;

        do {
            System.out.println();
            System.out.println(appliance);
            System.out.println();
            System.out.println("What information would you like to change?");
            System.out.println("\t1.\tBrand");
            System.out.println("\t2.\tType");
            System.out.println("\t3.\tPrice");
            System.out.println("\t4.\tQuit");
            System.out.print("Enter your choice: ");

            do {
                choice = scanner.nextInt();
            } while (invalidChoice(choice, 1, 4));

            switch (choice) {
                case 1:
                    System.out.print("What is the new value for this appliance's brand: ");
                    appliance.setBrand(scanner.next());
                    break;
                case 2:
                    System.out.print("What is the new value for this appliance's type: ");
                    appliance.setType(typeCreation(scanner));
                    break;
                case 3:
                    System.out.print("What is the new value for this appliance's price: ");
                    appliance.setPrice(priceCreation(scanner));
                    break;
            }
            if (choice != 4 )
                System.out.println("Appliances updated successfully.");

        } while( choice != 4);

        System.out.println();
        returnMainMenu();
    }

    /**
     * Option 3 in the menu, viewing all the product of a specific brand
     * @param scanner Scanner - for user input
     * @param inventory Appliance[] - store appliances inventory
     */
    private static void option3(Scanner scanner, Appliance[] inventory) {
        System.out.print("What brand would you like to see? ");
        String brand = scanner.next();
        System.out.println();

        for(Appliance appliance : inventory) {
            if (appliance == null)
                break;
            if(appliance.getBrand().equalsIgnoreCase(brand)) {
                System.out.println(appliance);
                System.out.println();
            }
        }
    }

    /**
     * Option 4 in the menu, viewing all products with price lower than the specify price
     * @param scanner Scanner - for user input
     * @param inventory Appliance[] - store appliances inventory
     */
    private static void option4(Scanner scanner, Appliance[] inventory) {
        System.out.print("What is your upper bound price? ");
        double price = scanner.nextDouble();
        System.out.println();

        for(Appliance appliance : inventory) {
            if (appliance == null)
                break;
            if(appliance.getPrice() <= price) {
                System.out.println(appliance);
                System.out.println();
            }
        }
    }

    /**
     * Returning to menu text
     */
    private static void returnMainMenu() {
        System.out.println("Returning to the main menu....");
        System.out.println();
    }

}
