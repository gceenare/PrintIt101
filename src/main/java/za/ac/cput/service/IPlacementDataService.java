/* IPlacementDataService.java
   PlacementData Service Interface
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025
*/
package za.ac.cput.service;

import za.ac.cput.domain.PlacementData;
import java.util.List;

public interface IPlacementDataService extends IService<PlacementData, Integer> {
    List<PlacementData> getAll();
    List<PlacementData> findByPositionId(int positionId);
    List<PlacementData> findByRotationId(int rotationId);
    List<PlacementData> findByScaleId(int scaleId);
}
