import org.junit.jupiter.api.Test;
import package1.Airplane;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirplaneTest {

    @Test
    public void defaultConstructorReturnsEmptyAirplane() {
        Airplane airplane = new Airplane();
        assertEquals("", airplane.getBrand());
        assertEquals(0.0, airplane.getPrice());
        assertEquals(0, airplane.getHorsePower());
    }

    @Test
    public void argumentConstructorReturnsAirplane() {
        Airplane airplane = new Airplane("Boeing", 1_000_000.00, 25_000);
        assertEquals("Boeing", airplane.getBrand());
        assertEquals(1_000_000.00, airplane.getPrice());
        assertEquals(25_000, airplane.getHorsePower());
    }

    @Test
    public void copyConstructorReturnsCopyAirplane() {
        Airplane airplane = new Airplane("Boeing", 1_000_000.00, 25_000);
        Airplane airplane1 = new Airplane(airplane);
        assertEquals("Boeing", airplane1.getBrand());
        assertEquals(1_000_000.00, airplane1.getPrice());
        assertEquals(25_000, airplane1.getHorsePower());
    }

}
