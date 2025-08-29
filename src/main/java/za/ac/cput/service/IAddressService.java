package za.ac.cput.service;

import za.ac.cput.domain.Address;
import java.util.List;

public interface IAddressService extends IService<Address, Integer> {
    List<Address> getAll();
    List<Address> findByStreet(String street);
    List<Address> findByMunicipality(String municipality);
    List<Address> findByProvince(String province);
    List<Address> findByPostalCode(String postalCode);
}
