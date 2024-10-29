package lab01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RenterTest {

    @Test
    void testConstructorAndGetters() {
        Renter renter = new Renter("John", "Doe", "ID12345", "DL54321");

        assertEquals("John", renter.getFirstName());
        assertEquals("Doe", renter.getLastName());
        assertEquals("ID12345", renter.getIdentityDocument());
        assertEquals("DL54321", renter.getDriversLicense());
    }

    @Test
    void testToString() {
        Renter renter = new Renter("Jane", "Smith", "ID98765", "DL98765");
        String expected = "Renter{firstName='Jane', lastName='Smith', identityDocument='ID98765', driversLicense='DL98765'}";

        assertEquals(expected, renter.toString());
    }

    @Test
    void testEquality_sameObject() {
        Renter renter = new Renter("Alice", "Wonderland", "ID11111", "DL11111");

        assertEquals(renter, renter); // Same object should be equal
    }

    @Test
    void testEquality_identicalObjects() {
        Renter renter1 = new Renter("Alice", "Wonderland", "ID11111", "DL11111");
        Renter renter2 = new Renter("Alice", "Wonderland", "ID11111", "DL11111");

        assertEquals(renter1, renter2); // Identical objects should be equal
    }

    @Test
    void testInequality_differentIdentityDocument() {
        Renter renter1 = new Renter("Alice", "Wonderland", "ID11111", "DL11111");
        Renter renter2 = new Renter("Alice", "Wonderland", "ID22222", "DL11111");

        assertNotEquals(renter1, renter2); // Different identity documents, should not be equal
    }

    @Test
    void testInequality_differentDriversLicense() {
        Renter renter1 = new Renter("Alice", "Wonderland", "ID11111", "DL11111");
        Renter renter2 = new Renter("Alice", "Wonderland", "ID11111", "DL22222");

        assertNotEquals(renter1, renter2); // Different driver's licenses, should not be equal
    }

    @Test
    void testHashCode_identicalObjects() {
        Renter renter1 = new Renter("Alice", "Wonderland", "ID11111", "DL11111");
        Renter renter2 = new Renter("Alice", "Wonderland", "ID11111", "DL11111");

        assertEquals(renter1.hashCode(), renter2.hashCode()); // Hash codes should be the same
    }

    @Test
    void testHashCode_differentObjects() {
        Renter renter1 = new Renter("Alice", "Wonderland", "ID11111", "DL11111");
        Renter renter2 = new Renter("Bob", "Marley", "ID22222", "DL22222");

        assertNotEquals(renter1.hashCode(), renter2.hashCode()); // Hash codes should be different
    }
}
