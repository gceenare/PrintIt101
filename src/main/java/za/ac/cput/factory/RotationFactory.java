/* RotationFactory.java
   Rotation Factory class
   Author: Siyabulela Mgijima (230680305)
   Date: 18 May 2025 */
package za.ac.cput.factory;

import za.ac.cput.domain.Rotation;
import za.ac.cput.util.Helper;

public class RotationFactory {

    public static Rotation createRotation(double x, double y, double z) {
        if (!Helper.isAngleValid(x) ||
                !Helper.isAngleValid(y) ||
                !Helper.isAngleValid(z)) {
            return null;
        }

        return new Rotation.Builder()
                .setX(x)
                .setY(y)
                .setZ(z)
                .build();
    }
}