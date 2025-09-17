/* IRotationService.java
   Rotation Service Interface
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025
*/
package za.ac.cput.service;

import za.ac.cput.domain.Rotation;
import java.util.List;

public interface IRotationService extends IService<Rotation, Integer> {
    List<Rotation> getAll();
    List<Rotation> findByAngle(double angle);
}
