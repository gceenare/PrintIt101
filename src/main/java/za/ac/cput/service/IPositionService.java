/* IPositionService.java
   Position Service Interface
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025
*/
package za.ac.cput.service;

import za.ac.cput.domain.Position;
import java.util.List;

public interface IPositionService extends IService<Position, Integer> {
    List<Position> getAll();
    List<Position> findByX(double x);
    List<Position> findByY(double y);
    List<Position> findByXAndY(double x, double y);
}
