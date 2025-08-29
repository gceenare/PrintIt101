/* PlacementDataRepository.java
   PlacementData Repository class
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025
*/
package za.ac.cput.repository;

import za.ac.cput.domain.PlacementData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlacementDataRepository extends JpaRepository<PlacementData, Integer> {

    Optional<PlacementData> findByPlacementDataId(int placementDataId);

    List<PlacementData> findByPosition_PositionId(int positionId);
    List<PlacementData> findByRotation_RotationId(int rotationId);
    List<PlacementData> findByScale_ScaleId(int scaleId);
}
