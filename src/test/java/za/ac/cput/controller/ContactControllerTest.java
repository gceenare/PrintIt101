package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Contact;
import za.ac.cput.service.ContactService;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ObjectMapper objectMapper;

    private Contact sampleContact() {
        return new Contact.Builder()
                .setEmail("jane.doe@example.com")
                .setPhoneNumber("0211234567")
                .build();
    }

    private Contact contact;

    @BeforeEach
    void setUp() {
        contact = sampleContact();
    }

    @Test
    @Order(1)
    void testCreateContact() throws Exception {
        String json = objectMapper.writeValueAsString(contact);

        mockMvc.perform(post("/api/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("0211234567"))
                .andExpect(jsonPath("$.contactId").exists());
    }

    @Test
    @Order(2)
    void testReadContact() throws Exception {
        Contact saved = contactService.create(contact);

        mockMvc.perform(get("/api/contacts/{id}", saved.getContactId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("0211234567"))
                .andExpect(jsonPath("$.contactId").value(saved.getContactId()));
    }

    @Test
    @Order(3)
    void testUpdateContact() throws Exception {
        Contact saved = contactService.create(contact);

        Contact updated = new Contact.Builder()
                .copy(saved)
                .setEmail("john.doe@example.com")
                .setPhoneNumber("0217654321")
                .build();

        String json = objectMapper.writeValueAsString(updated);

        mockMvc.perform(put("/api/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("0217654321"))
                .andExpect(jsonPath("$.contactId").value(saved.getContactId()));
    }

    @Test
    @Order(4)
    void testDeleteContact() throws Exception {
        Contact saved = contactService.create(contact);

        mockMvc.perform(delete("/api/contacts/{id}", saved.getContactId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/contacts/{id}", saved.getContactId()))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    void testGetAllContacts() throws Exception {
        contactService.create(contact);

        mockMvc.perform(get("/api/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    @Order(6)
    void testFindByEmail() throws Exception {
        contactService.create(contact);

        mockMvc.perform(get("/api/contacts/email/{email}", "jane.doe@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"));
    }

    @Test
    @Order(7)
    void testFindByPhoneNumber() throws Exception {
        contactService.create(contact);

        mockMvc.perform(get("/api/contacts/phone/{phoneNumber}", "0211234567"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value("0211234567"));
    }

    @Test
    @Order(8)
    void testExistsByEmail() throws Exception {
        Contact saved = contactService.create(contact);

        mockMvc.perform(get("/api/contacts/exists/email/{email}", saved.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        mockMvc.perform(get("/api/contacts/exists/email/{email}", "nonexistent@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    @Order(9)
    void testExistsByPhoneNumber() throws Exception {
        Contact saved = contactService.create(contact);

        mockMvc.perform(get("/api/contacts/exists/phone/{phoneNumber}", saved.getPhoneNumber()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        mockMvc.perform(get("/api/contacts/exists/phone/{phoneNumber}", "0000000000"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    @Order(10)
    void testCreateInvalidContact() throws Exception {
        Contact invalid = new Contact.Builder()
                .setEmail("") // invalid
                .setPhoneNumber("") // invalid
                .build();

        String json = objectMapper.writeValueAsString(invalid);

        mockMvc.perform(post("/api/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(11)
    void testReadNonExistentContact() throws Exception {
        mockMvc.perform(get("/api/contacts/{id}", 99999))
                .andExpect(status().isNotFound());
    }
}
