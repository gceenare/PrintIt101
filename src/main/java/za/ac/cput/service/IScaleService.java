/* IScaleService.java
   Scale Service Interface
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025
*/
package za.ac.cput.service;

import za.ac.cput.domain.Scale;
import java.util.List;

public interface IScaleService extends IService<Scale, Integer> {
    List<Scale> getAll();
    List<Scale> findByValue(double value);
}
