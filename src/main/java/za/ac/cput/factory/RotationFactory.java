/* RotationFactory.java
   Rotation Factory class
   Author: Siyabulela Mgijima (230680305)
   Date: 18 May 2025 */
package za.ac.cput.factory;

import za.ac.cput.domain.Rotation;
import za.ac.cput.util.Helper;

public class RotationFactory {

    public static Rotation createRotation(double angle) {
        if (!Helper.isAngleValid(angle)) {
            return null;
        }

        return new Rotation.Builder()
                .setAngle(angle)
                .build();
    }
}
