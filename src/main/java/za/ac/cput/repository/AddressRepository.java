package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByStreetContainingIgnoreCase(String street);

    List<Address> findByMunicipalityContainingIgnoreCase(String municipality);

    List<Address> findByProvinceContainingIgnoreCase(String province);

    List<Address> findByPostalCode(String postalCode);
}
