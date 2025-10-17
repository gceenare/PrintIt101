package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Contact;

import static org.junit.jupiter.api.Assertions.*;

class ContactFactoryTest {

    @Test
    public void testCreateValidContact() {
        Contact contact = ContactFactory.createContact("0831234567", "user@example.com");
        assertNotNull(contact);
        System.out.println("Valid Contact:\n" + contact);
    }

    @Test
    public void testCreateContactWithEmptyPhoneNumber() {
        Contact contact = ContactFactory.createContact("", "user@example.com");
        assertNull(contact);
    }

    @Test
    public void testCreateContactWithInvalidEmail() {
        Contact contact = ContactFactory.createContact("0831234567", "invalid-email");
        assertNull(contact);
    }

    @Test
    public void testCreateCompletelyInvalidContact() {
        Contact contact = ContactFactory.createContact("", "bademail");
        assertNull(contact);
    }
}
