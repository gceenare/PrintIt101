package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByUserName(String userName);

    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);

    List<Customer> findByLastNameContainingIgnoreCase(String lastName);

    boolean existsByUserName(String userName);
}