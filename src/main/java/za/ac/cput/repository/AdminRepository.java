/* AdminRepository.java
   Admin Repository interface
   Author: System Generated
   Date: 16 September 2025
*/

package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Admin;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByUserName(String userName);

    List<Admin> findByAdminLevel(String adminLevel);

    List<Admin> findByDepartment(String department);

    List<Admin> findByFirstNameContainingIgnoreCase(String firstName);

    List<Admin> findByLastNameContainingIgnoreCase(String lastName);

    List<Admin> findByAdminLevelAndDepartment(String adminLevel, String department);

    boolean existsByUserName(String userName);
}