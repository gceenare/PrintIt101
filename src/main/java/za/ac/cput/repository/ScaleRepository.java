/* ScaleRepository.java
   Scale Repository
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025 */
package za.ac.cput.repository;

import za.ac.cput.domain.Scale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScaleRepository extends JpaRepository<Scale, Integer> {
    Optional<Scale> findByScaleId(int scaleId);

    List<Scale> findByX(double x);
    List<Scale> findByY(double y);
    List<Scale> findByZ(double z);
}



