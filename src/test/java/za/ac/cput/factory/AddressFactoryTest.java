package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;

import static org.junit.jupiter.api.Assertions.*;

class AddressFactoryTest {

    // ------------------ Postal Address ------------------
    @Test
    public void testCreateValidPostalAddress() {
        Address postalAddress = AddressFactory.createPostalAddress(1, 123, "Maple Street", "Cape Town", "Western Cape", "8001", "South Africa");
        assertNotNull(postalAddress);
        System.out.println("Postal Address:\n" + postalAddress);
    }

    @Test
    public void testCreateInvalidPostalAddress() {
        Address postalAddress = AddressFactory.createPostalAddress(1, 123, "", "Cape Town", "Western Cape", "8001", "South Africa");
        assertNull(postalAddress);
    }

    // ------------------ Residential Address ------------------
    @Test
    public void testCreateValidResidentialAddress() {
        Address resAddress = AddressFactory.createResidentialAddress(2, 456, "Sunset Villas", 12, "Ocean Drive", "Durban", "KZN", "4001", "South Africa");
        assertNotNull(resAddress);
        System.out.println("Residential Address:\n" + resAddress);
    }

    @Test
    public void testCreateInvalidResidentialAddress() {
        Address resAddress = AddressFactory.createResidentialAddress(0, 0, "", 0, "", "", "", "", "");
        assertNull(resAddress);
    }

    // ------------------ Business Address ------------------
    @Test
    public void testCreateValidBusinessAddress() {
        Address bizAddress = AddressFactory.createBusinessAddress(3, 789, "Tech Park", "Main Road", "Joburg", "Gauteng", "2000", "South Africa");
        assertNotNull(bizAddress);
        System.out.println("Business Address:\n" + bizAddress);
    }

    @Test
    public void testCreateInvalidBusinessAddress() {
        Address bizAddress = AddressFactory.createBusinessAddress(3, 789, "", "Main Road", "Joburg", "Gauteng", "2000", "South Africa");
        assertNull(bizAddress);
    }

    // ------------------ Full Address ------------------
    @Test
    public void testCreateValidFullAddress() {
        Address fullAddress = AddressFactory.createFullAddress(4, 101, "Global Towers", 33, 444, "Nelson Street", "Pretoria", "Gauteng", "0002", "South Africa");
        assertNotNull(fullAddress);
        System.out.println("Full Address:\n" + fullAddress);
    }

    @Test
    public void testCreateInvalidFullAddress() {
        Address fullAddress = AddressFactory.createFullAddress(4, 101, null, 33, 0, "Nelson Street", "Pretoria", "", "0002", "South Africa");
        assertNull(fullAddress);
    }
}