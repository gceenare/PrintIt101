package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Address;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<Address> findById(Integer integer);

    List<Address> findByStreetContainingIgnoreCase(String street);

    List<Address> findByMunicipalityContainingIgnoreCase(String municipality);

    List<Address> findByProvinceContainingIgnoreCase(String province);

    List<Address> findByPostalCode(String postalCode);
}
