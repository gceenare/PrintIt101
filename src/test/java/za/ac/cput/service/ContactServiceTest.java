package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Contact;
import za.ac.cput.factory.ContactFactory;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class ContactServiceTest {

    @Autowired
    private ContactService service;

    private Contact contact;

    @BeforeEach
    void setUp() {
        contact = ContactFactory.createContact("0812345678", "test@example.com");
        assertNotNull(contact);
    }

    @Test
    void create() {
        Contact created = service.create(contact);
        assertNotNull(created);
        assertEquals(contact.getEmail(), created.getEmail());
        System.out.println("Created: " + created);
    }

    @Test
    void read() {
        Contact created = service.create(contact);
        Contact read = service.read(created.getContactId());
        assertNotNull(read);
        assertEquals(created.getContactId(), read.getContactId());
        System.out.println("Read: " + read);
    }

    @Test
    void update() {
        Contact created = service.create(contact);
        Contact updated = new Contact.Builder()
                .copy(created)
                .setPhoneNumber("0823456789")
                .setEmail("t@example.com")
                .build();

        Contact result = service.update(updated);
        assertNotNull(result);
        assertEquals("0823456789", result.getPhoneNumber());
        System.out.println("Updated: " + result);
    }

    @Test
    void delete() {
        Contact created = service.create(contact);
        boolean deleted = service.delete(created.getContactId());
        assertTrue(deleted);
        System.out.println("Deleted: " + deleted);
    }

    @Test
    void getAll() {
        service.create(contact);
        List<Contact> contacts = service.getAll();
        assertNotNull(contacts);
        assertFalse(contacts.isEmpty());
        System.out.println("All Contacts: " + contacts);
    }

    @Test
    void findByEmail() {
        Contact tempContact = ContactFactory.createContact("08123456721", "tst333@example.com");
        service.create(tempContact);
        Contact found = service.findByEmail(tempContact.getEmail());
        assertNotNull(found);
        assertEquals(tempContact.getEmail(), found.getEmail());
        System.out.println("Found by Email: " + found);
    }

    @Test
    void findByPhoneNumber() {
        Contact temp1Contact = ContactFactory.createContact("081234567221", "tst333@example.com");
        service.create(temp1Contact);
        Contact found = service.findByPhoneNumber(temp1Contact.getPhoneNumber());
        assertNotNull(found);
        assertEquals(temp1Contact.getPhoneNumber(), found.getPhoneNumber());
        System.out.println("Found by Phone Number: " + found);
    }

    @Test
    void existsByEmail() {
        service.create(contact);
        boolean exists = service.existsByEmail(contact.getEmail());
        assertTrue(exists);
        System.out.println("Exists by Email: " + exists);
    }

    @Test
    void existsByPhoneNumber() {
        service.create(contact);
        boolean exists = service.existsByPhoneNumber(contact.getPhoneNumber());
        assertTrue(exists);
        System.out.println("Exists by Phone Number: " + exists);
    }
}
