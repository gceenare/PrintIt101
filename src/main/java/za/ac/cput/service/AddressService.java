package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.repository.AddressRepository;

import java.util.List;

@Service
public class AddressService implements IAddressService {

    private AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address create(Address address) {
        return repository.save(address);
    }

    @Override
    public Address read(Integer id) {
        return repository.findById(id).orElse(null);
    }


    @Override
    public Address update(Address address) {
        return repository.save(address);
    }

    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Address> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Address> findByStreet(String street) {
        return repository.findByStreetContainingIgnoreCase(street);
    }

    @Override
    public List<Address> findByMunicipality(String municipality) {
        return repository.findByMunicipalityContainingIgnoreCase(municipality);
    }

    @Override
    public List<Address> findByProvince(String province) {
        return repository.findByProvinceContainingIgnoreCase(province);
    }

    @Override
    public List<Address> findByPostalCode(String postalCode) {
        return repository.findByPostalCode(postalCode);
    }
}
