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
import za.ac.cput.domain.Address;
import za.ac.cput.service.AddressService;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ObjectMapper objectMapper;

    private Address address;

    private Address sampleAddress() {
        return new Address.Builder()
                .setPropertyNumber(123)
                .setBuildingName("Sunset Apartments")
                .setUnitNumber(12)
                .setStreet("Main St")
                .setMunicipality("Cape Town")
                .setProvince("Western Cape")
                .setPostalCode("8000")
                .setCountry("South Africa")
                .build();
    }

    @BeforeEach
    void setUp() {
        address = sampleAddress();
    }

    @Test
    @Order(1)
    void testCreateAddress() throws Exception {
        String json = objectMapper.writeValueAsString(address);

        mockMvc.perform(post("/api/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.street").value("Main St"))
                .andExpect(jsonPath("$.municipality").value("Cape Town"))
                .andExpect(jsonPath("$.province").value("Western Cape"))
                .andExpect(jsonPath("$.addressId").exists());
    }

    @Test
    @Order(2)
    void testReadAddress() throws Exception {
        Address saved = addressService.create(address);

        mockMvc.perform(get("/api/addresses/{id}", saved.getAddressId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.street").value("Main St"))
                .andExpect(jsonPath("$.municipality").value("Cape Town"))
                .andExpect(jsonPath("$.province").value("Western Cape"))
                .andExpect(jsonPath("$.addressId").value(saved.getAddressId()));
    }

    @Test
    @Order(3)
    void testUpdateAddress() throws Exception {
        Address saved = addressService.create(address);

        Address updated = new Address.Builder()
                .copy(saved)
                .setStreet("Long Street")
                .setMunicipality("Stellenbosch")
                .build();

        String json = objectMapper.writeValueAsString(updated);

        mockMvc.perform(put("/api/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.street").value("Long Street"))
                .andExpect(jsonPath("$.municipality").value("Stellenbosch"))
                .andExpect(jsonPath("$.addressId").value(saved.getAddressId()));
    }

    @Test
    @Order(4)
    void testDeleteAddress() throws Exception {
        Address saved = addressService.create(address);

        mockMvc.perform(delete("/api/addresses/{id}", saved.getAddressId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/addresses/{id}", saved.getAddressId()))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    void testGetAllAddresses() throws Exception {
        addressService.create(address);

        mockMvc.perform(get("/api/addresses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    @Order(6)
    void testFindByStreet() throws Exception {
        addressService.create(address);

        mockMvc.perform(get("/api/addresses/street/{street}", "Main St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].street").value("Main St"));
    }

    @Test
    @Order(7)
    void testFindByMunicipality() throws Exception {
        addressService.create(address);

        mockMvc.perform(get("/api/addresses/municipality/{municipality}", "Cape Town"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].municipality").value("Cape Town"));
    }

    @Test
    @Order(8)
    void testFindByProvince() throws Exception {
        addressService.create(address);

        mockMvc.perform(get("/api/addresses/province/{province}", "Western Cape"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].province").value("Western Cape"));
    }

    @Test
    @Order(9)
    void testFindByPostalCode() throws Exception {
        addressService.create(address);

        mockMvc.perform(get("/api/addresses/postalCode/{postalCode}", "8000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].postalCode").value("8000"));
    }

    @Test
    @Order(10)
    void testCreateInvalidAddress() throws Exception {
        Address invalid = new Address.Builder()
                .setStreet("") // invalid
                .setMunicipality("Cape Town")
                .setProvince("Western Cape")
                .setPostalCode("8000")
                .setCountry("South Africa")
                .build();

        String json = objectMapper.writeValueAsString(invalid);

        mockMvc.perform(post("/api/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(11)
    void testReadNonExistentAddress() throws Exception {
        mockMvc.perform(get("/api/addresses/{id}", 99999))
                .andExpect(status().isNotFound());
    }
}
