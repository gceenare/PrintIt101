package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Contact;

import static org.junit.jupiter.api.Assertions.*;

class ContactFactoryTest {

    // ------------------ Valid Contact ------------------
    @Test
    public void testCreateValidContact() {
        Contact contact = ContactFactory.createContact(1, "0831234567", "user@example.com");
        assertNotNull(contact);
        System.out.println("Valid Contact:\n" + contact);
    }

    // ------------------ Invalid: Null ID ------------------
    @Test
    public void testCreateContactWithNullId() {
        Contact contact = ContactFactory.createContact(0, "0831234567", "user@example.com");
        assertNull(contact);
    }

    // ------------------ Invalid: Empty Phone Number ------------------
    @Test
    public void testCreateContactWithEmptyPhoneNumber() {
        Contact contact = ContactFactory.createContact(2, "", "user@example.com");
        assertNull(contact);
    }

    // ------------------ Invalid: Invalid Email ------------------
    @Test
    public void testCreateContactWithInvalidEmail() {
        Contact contact = ContactFactory.createContact(3, "0831234567", "invalid-email");
        assertNull(contact);
    }

    // ------------------ Invalid: All Fields Invalid ------------------
    @Test
    public void testCreateCompletelyInvalidContact() {
        Contact contact = ContactFactory.createContact(0, "", "bademail");
        assertNull(contact);
    }
}