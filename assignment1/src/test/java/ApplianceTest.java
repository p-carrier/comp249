import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApplianceTest {

    @BeforeEach
    public void destroyAppliance() {

    }

    @Test
    public void createAppliance() {
        Appliance appliance = new Appliance();
        assertEquals("", appliance.getType());
        assertEquals("", appliance.getBrand());
        assertEquals(Appliance.findNumberOfCreatedAppliances() + 999_999, appliance.getSerialNumber());
        assertEquals(0.0, appliance.getPrice());
    }

    @Test
    public void createApplianceWithValues() {
        Appliance appliance = new Appliance("Fridge", "brand", 20.0);
        assertEquals(Appliance.TYPES[0], appliance.getType());
        assertEquals("brand", appliance.getBrand());
        assertEquals(Appliance.findNumberOfCreatedAppliances() + 999_999, appliance.getSerialNumber());
        assertEquals(20.0, appliance.getPrice());
    }

    @Test void createApplianceWithOtherAppliance() {
        Appliance appliance = new Appliance("Fridge", "brand", 20.0);
        Appliance appliance1 = new Appliance(appliance);
        assertEquals(Appliance.TYPES[0], appliance1.getType());
        assertEquals("brand", appliance1.getBrand());
        assertEquals(Appliance.findNumberOfCreatedAppliances() + 999_999, appliance1.getSerialNumber());
        assertEquals(20.0, appliance1.getPrice());
    }

    @Test
    public void increasingSerialNumber() {
        Appliance appliance1 = new Appliance();
        Appliance appliance2 = new Appliance();
        assertEquals(appliance1.getSerialNumber() + 1, appliance2.getSerialNumber());
        Appliance appliance3 = new Appliance();
        assertEquals(appliance2.getSerialNumber() + 1, appliance3.getSerialNumber());
        Appliance appliance4 = new Appliance();
        assertEquals(appliance3.getSerialNumber() + 1, appliance4.getSerialNumber());
    }

    @Test
    public void increasingNumberOfAppliances() {
        long startNumberOfAppliances = Appliance.findNumberOfCreatedAppliances();
        Appliance appliance = new Appliance();
        assertEquals(startNumberOfAppliances + 1, Appliance.findNumberOfCreatedAppliances());
        Appliance appliance1 = new Appliance("Fridge", "brand", 20.0);
        assertEquals(startNumberOfAppliances + 2, Appliance.findNumberOfCreatedAppliances());
        Appliance appliance2 = new Appliance(appliance);
        assertEquals(startNumberOfAppliances + 3, Appliance.findNumberOfCreatedAppliances());
    }

    @Test
    public void twoIdenticalAppliancesAreEquals() {
        Appliance appliance1 = new Appliance("Fridge", "brand", 20.0);
        Appliance appliance2 = new Appliance(appliance1);

        assertTrue(appliance1.equals(appliance2));
    }

    @Test
    public void twoDifferentAppliancesAreNotEquals() {
        Appliance appliance1 = new Appliance("Fridge", "brand", 20.0);
        Appliance appliance2 = new Appliance("Stove", "brand2", 25.0);

        assertFalse(appliance1.equals(appliance2));
    }

    @Test
    public void changeTypeOnValidType() {
        Appliance appliance = new Appliance("Fridge", "brand", 20.0);
        appliance.setType("Stove");
        assertEquals("Stove", appliance.getType());
    }

    @Test
    public void noChangeTypeOnInvalidType() {
        Appliance appliance = new Appliance("Fridge", "brand", 20.0);
        appliance.setType("banana");
        assertEquals("Fridge", appliance.getType());
    }

    @Test
    public void noTypeOnInvalidTypeOnCreateAppliance() {
        Appliance appliance = new Appliance("Banana", "brand", 20.0);

        assertEquals("", appliance.getType());
    }

    @Test
    public void applianceValuesInStringFormat() {
        Appliance appliance = new Appliance("Fridge", "brand", 20.0);

        assertEquals("Appliance Serial # "+ (Appliance.findNumberOfCreatedAppliances() + 999_999) +
                "\nBrand: brand" +
                "\nType: Fridge" +
                "\nPrice: 20.0", appliance.toString());
    }

    @Test
    public void appliancePriceMustBeGreaterEqualThanOneOnApplianceCreation() {
        Appliance appliance = new Appliance("Fridge", "brand", -10.0);
        assertEquals(0.0, appliance.getPrice());
    }

    @Test
    public void appliancePriceMustBeGreaterEqualThanOneOnSetter() {
        Appliance appliance = new Appliance("Fridge", "brand", 10.0);
        appliance.setPrice(-50.0);
        assertEquals(10.0, appliance.getPrice());
    }
}
