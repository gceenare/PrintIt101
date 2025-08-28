package za.ac.cput.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Address;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.repository.AddressRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
//@Transactional
//@Rollback
class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    private Address createAddress() {
        Address address = AddressFactory.createResidentialAddress(123, null, 0, "Main Street", "Cape Town", "Western Cape", "8001", "South Africa");
        assertNotNull(address, "Address creation failed in factory");
        return address;
    }

    @Test
    @Order(1)
    void create() {
        Address address = createAddress();
        Address saved = addressService.create(address);
        assertNotNull(saved);
        assertEquals("Main Street", saved.getStreet());
        assertEquals("Cape Town", saved.getMunicipality());
        assertEquals("Western Cape", saved.getProvince());
        assertEquals("8001", saved.getPostalCode());
        assertTrue(saved.getAddressId() > 0);
    }

    @Test
    @Order(2)
    void read() {
        Address address = createAddress();
        Address saved = addressRepository.save(address);
        Address found = addressService.read(saved.getAddressId());
        assertNotNull(found);
        assertEquals("Main Street", found.getStreet());
        assertEquals(saved.getAddressId(), found.getAddressId());
    }

    @Test
    @Order(3)
    void update() {
        Address address = createAddress();
        Address saved = addressRepository.save(address);
        Address updatedAddress = new Address.Builder()
                .copy(saved)
                .setStreet("New Street")
                .setMunicipality("Johannesburg")
                .build();
        Address updated = addressService.update(updatedAddress);
        assertNotNull(updated);
        assertEquals("New Street", updated.getStreet());
        assertEquals("Johannesburg", updated.getMunicipality());
        assertEquals(saved.getAddressId(), updated.getAddressId());
    }

    @Test
    @Order(4)
    void delete() {
        Address address = createAddress();
        Address saved = addressRepository.save(address);
        boolean deleted = addressService.delete(saved.getAddressId());
        assertTrue(deleted);
        assertNull(addressService.read(saved.getAddressId()));
    }

    @Test
    @Order(5)
    void getAll() {
        Address address = createAddress();
        addressRepository.save(address);
        List<Address> addresses = addressService.getAll();
        assertFalse(addresses.isEmpty());
        assertEquals("Main Street", addresses.get(0).getStreet());
    }

    @Test
    @Order(6)
    void findByStreet() {
        Address address = createAddress();
        addressRepository.save(address);
        List<Address> found = addressService.findByStreet("Main");
        assertFalse(found.isEmpty());
        assertEquals("Main Street", found.get(0).getStreet());
    }

    @Test
    @Order(7)
    void findByMunicipality() {
        Address address = createAddress();
        addressRepository.save(address);
        List<Address> found = addressService.findByMunicipality("Cape");
        assertFalse(found.isEmpty());
        assertEquals("Cape Town", found.get(0).getMunicipality());
    }

    @Test
    @Order(8)
    void findByProvince() {
        Address address = createAddress();
        addressRepository.save(address);
        List<Address> found = addressService.findByProvince("Western");
        assertFalse(found.isEmpty());
        assertEquals("Western Cape", found.get(0).getProvince());
    }

    @Test
    @Order(9)
    void findByPostalCode() {
        Address address = createAddress();
        addressRepository.save(address);
        List<Address> found = addressService.findByPostalCode("8001");
        assertFalse(found.isEmpty());
        assertEquals("8001", found.get(0).getPostalCode());
    }
}