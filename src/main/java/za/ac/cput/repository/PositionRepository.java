/* PositionRepository.java
   Position Repository
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025 */
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Position;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    Optional<Position> findByPositionId(int positionId);

    List<Position> findByX(double x);
    List<Position> findByY(double y);
    List<Position> findByZ(double z);
}



