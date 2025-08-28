/* RotationRepository.java
   Rotation Repository
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025 */
package za.ac.cput.repository;

import za.ac.cput.domain.Rotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RotationRepository extends JpaRepository<Rotation, Integer> {
    Optional<Rotation> findByRotationId(int RotationId);

    List<Rotation> findByX(double x);
    List<Rotation> findByY(double y);
    List<Rotation> findByZ(double z);
}
