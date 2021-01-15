/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 4
 * Sunday, April 19, 2020
 *
 * Part 2
 * */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Program that read information about cell phone from a file and insert it into
 * a linkedList then search for specific cellphone with their serial number. Then,
 * it test the CellList class methods and constructors, the CellNode class methods
 * and constructors and the CellPhone class methods and constructors.
 *
 * @author Philippe Carrier
 */
public class CellListUtilisation {

    public static void main(String[] args) {

        System.out.println("Welcome to Cell List Utilisation");
        System.out.println();
        System.out.println();

        CellList cellList1 = new CellList();
        list_population(cellList1);

        cellList1.showContents();

        System.out.println();
        System.out.println();
        System.out.println("We will now search for three serial numbers: ");

//        Serial number search in list
        for (int i = 0; i < 3; i ++) {
            System.out.print((i+1) + "- please enter a serial number: ");
            CellList.CellNode cellNode = cellList1.find(CellPhone.scanner.nextLong());
            System.out.println(cellNode != null ? cellNode.getCellPhone() : "This phone is not in the list.");
        }

        method_testing();

        System.out.println();
        System.out.println("Thank you for using Cell List Utilisation program.");
        System.out.println("Closing...");
    }

    /**
     * Read the information from a file (Cell_Info.txt) and insert all the cellphone
     * information into the list (once per serial number)
     *
     * @param cellList1 CellList - the list to be populated.
     */
    private static void list_population(CellList cellList1) {
        try (Scanner inputStream = new Scanner(new FileInputStream("Cell_Info.txt"))) {
            while (inputStream.hasNextLine()) {
                String[] phone = inputStream.nextLine().split("\\s+");
                if (!cellList1.contains(Long.parseLong(phone[0]))) {
                    cellList1.addToStart(new CellPhone(
                            Long.parseLong(phone[0]),
                            phone[1],
                            Integer.parseInt(phone[3]),
                            Double.parseDouble(phone[2])
                    ));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file you're trying to open, Cell_info.txt, does not exist.");
        }
    }

    /**
     * Testing of all the constructor methods and all the methods of each classes.
     */
    private static void method_testing() {
        System.out.println();
        System.out.println();
        System.out.println("Let's test the program");
        boolean errors = false;
        try {
            System.out.println("Let's construct some cellphone instance for test purposes...");
            CellPhone cellPhone1 = new CellPhone(1111111, "Apple", 2020, 799.99);
            CellPhone cellPhone2 = new CellPhone(cellPhone1,2222222);
            CellPhone cellPhoneClone = cellPhone1.clone();
            System.out.println("CellPhone class test begin...");
            assert cellPhone1.equals(cellPhone2);
            assert cellPhone1.equals(cellPhoneClone);
            assert cellPhone1.toString().equals("[1111111: Apple 799.99$ 2020]");
            System.out.println("All CellPhone class test succeed.");

            System.out.println();
            System.out.println("Let's construct some CellNode instance for test purposes...");
            CellList.CellNode cellNodeEmpty = new CellList().new CellNode();
            CellList.CellNode cellNode1 = new CellList().new CellNode(cellPhone1, cellNodeEmpty);
            CellList.CellNode cellNodeCopy = new CellList().new CellNode(cellNode1);
            CellList.CellNode cellNodeClone = cellNode1.clone();

            System.out.println("CellNode class test begin...");
            assert cellNodeEmpty.getCellPhone() == null && cellNodeEmpty.getPointer() == null;
            assert cellNode1.getCellPhone().equals(cellPhone1) && cellNode1.getPointer() == cellNodeEmpty;
            assert cellNodeCopy.getCellPhone().equals(cellPhone1) && cellNodeCopy.getPointer() == cellNodeEmpty;
            assert cellNodeClone.getCellPhone().equals(cellPhone1) && cellNodeClone.getPointer() == cellNodeEmpty;
            cellNodeEmpty.setPointer(cellNode1);
            cellNodeEmpty.setCellPhone(cellPhone1);
            assert cellNodeEmpty.getCellPhone().equals(cellPhone1) && cellNode1.getPointer() == cellNode1;
            System.out.println("All CellNode class test succeed.");

            System.out.println();
            System.out.println("Let's construct some CellList instance for test purposes...");
            CellList cellList = new CellList();
            cellList.addToStart(cellPhone1);
            cellList.addToStart(cellPhone2);
            CellList cellListEmpty = new CellList();
            CellList cellListCopy = new CellList(cellList);

            System.out.println("CellList class test begin...");
            assert cellList.equals(cellListCopy);
            assert !cellList.equals(cellListEmpty);

            cellListEmpty.addToStart(cellPhone1);
            assert cellListEmpty.contains(cellPhone1.serialNum);
            System.out.println("Insert at start: ");
            cellListEmpty.showContents();
            System.out.println("Replace object at index 0: ");
            cellListEmpty.replaceAtIndex(cellPhone2, 0);
            cellListEmpty.showContents();
            System.out.println("Delete from start: ");
            cellListEmpty.deleteFromStart();
            cellListEmpty.showContents();

            System.out.println();
            System.out.println("Before insert at index:");
            cellList.showContents();
            System.out.println("Insert at index 1: ");
            cellList.insertAtIndex(new CellPhone(12345678, "Google", 2050, 1799.99), 1);
            cellList.showContents();
            System.out.println("Delete at index 1:");
            cellList.deleteFromIndex(1);
            cellList.showContents();

            System.out.println();
            System.out.println("All CellList class test succeed.");


        } catch (AssertionError e) {
            System.out.println(e.getMessage());
            System.out.println("One of the test failed");
            errors = true;
        } finally {
            if (!errors) {
                System.out.println("All tests passed.");
            }
        }
    }
}
