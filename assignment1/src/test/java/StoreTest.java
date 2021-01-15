//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Scanner;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class StoreTest {
//
//    @Test
//    public void doubleTest() {
//        String data = "99.99";
//        System.setIn(new InputStream() {
//            @Override
//            public int read() throws IOException {
//                return 0;
//            }
//        });
//        Scanner mockScanner = new Scanner(System.in);
//
//        double input = mockScanner.nextDouble();
//
//        assertEquals(99.99, input);
//    }
//
//    @Test
//    public void userLoggedInWithValidPassword() {
//        String data = "a a c249";
//        System.setIn(new ByteArrayInputStream(data.getBytes()));
//        Scanner mockScanner = new Scanner(System.in);
//
//        boolean isLogged = Store.login(mockScanner);
//
//        assertTrue(isLogged);
//
//        mockScanner.close();
//    }
//
//    @Test
//    public void option1WillCreateProducts() {
//        Appliance[] inventory = new Appliance[8];
//        String data = "c249 4 " +
//                "Hello Fridge 99.99 " +
//                "Hello Fridge 99.99 " +
//                "Wolf Fridge 99.99 " +
//                "Frigidaire Fridge 99.99";
//
//        System.setIn(new ByteArrayInputStream(data.getBytes()));
//        Scanner mockScanner = new Scanner(System.in);
//
//        Store.option1(mockScanner, inventory);
//
//        mockScanner.close();
//
//        assertEquals("Fridge", inventory[0].getType());
//        assertEquals("Fridge", inventory[1].getType());
//        assertEquals("Fridge", inventory[2].getType());
//        assertEquals("Fridge", inventory[3].getType());
//        assertEquals(99.99, inventory[0].getPrice());
//        assertEquals(99.99, inventory[1].getPrice());
//        assertEquals(99.99, inventory[2].getPrice());
//        assertEquals(99.99, inventory[3].getPrice());
//        assertEquals("Hello", inventory[0].getBrand());
//        assertEquals("Hello", inventory[1].getBrand());
//        assertEquals("Wolf", inventory[2].getBrand());
//        assertEquals("Frigidaire", inventory[3].getBrand());
//        assertNull(inventory[4]);
//        assertNull(inventory[5]);
//        assertNull(inventory[6]);
//        assertNull(inventory[7]);
//    }
//
//    @Test
//    public void option2WillUpdateProduct() {
//        Appliance[] inventory = {
//                new Appliance("Fridge", "Hello", 99.99),
//                new Appliance("Fridge", "Hello", 99.99),
//                new Appliance("Fridge", "Wolf", 99.99),
//                new Appliance("Fridge", "Frigidaire", 99.99),
//                null,
//                null,
//                null,
//                null
//        };
//
//        long serialNumber = inventory[2].getSerialNumber();
//        String data = "c249 " + serialNumber + " 3 79.99 4";
//        System.setIn(new ByteArrayInputStream(data.getBytes()));
//        Scanner mockScanner = new Scanner(System.in);
//
//        Store.option2(mockScanner, inventory);
//
//        mockScanner.close();
//        assertEquals(79.99, inventory[2].getPrice());
//    }
//
//    @Test
//    public void option3WillShowAllProductOfBrand() {
//        String data = "Hello";
//        System.setIn(new ByteArrayInputStream(data.getBytes()));
//        Scanner mockScanner = new Scanner(System.in);
//        Appliance[] inventory = {
//                new Appliance("Fridge", "Hello", 99.99),
//                new Appliance("Fridge", "Hello", 99.99),
//                new Appliance("Fridge", "Wolf", 99.99),
//                new Appliance("Fridge", "Frigidaire", 99.99),
//                null,
//                null,
//                null,
//                null
//        };
//
//        Store.option3(mockScanner, inventory);
//        mockScanner.close();
//    }
//
//    @Test
//    public void option4WillShowAllProductBelowEqualPrice() {
//        String data = "70.00";
//        System.setIn(new ByteArrayInputStream(data.getBytes()));
//        Scanner mockScanner = new Scanner(System.in);
//        Appliance[] inventory = {
//                new Appliance("Fridge", "Hello", 39.99),
//                new Appliance("Fridge", "Hello", 99.99),
//                new Appliance("Fridge", "Wolf", 39.99),
//                new Appliance("Fridge", "Frigidaire", 70.00),
//                null,
//                null,
//                null
//        };
//
//        Store.option4(mockScanner, inventory);
//
//        mockScanner.close();
//    }
//}
