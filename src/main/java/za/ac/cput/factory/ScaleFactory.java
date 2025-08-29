/* ScaleFactory.java
   Scale Factory class
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025 */
package za.ac.cput.factory;

import za.ac.cput.domain.Scale;
import za.ac.cput.util.Helper;

public class ScaleFactory {
    public static Scale createScale(double x, double y, double z) {
        if (Helper.doubleIsInvalid(x) || Helper.doubleIsInvalid(y) || Helper.doubleIsInvalid(z)) {
            return null;
    }
        return new Scale.Builder()
                .setX(x)
                .setY(y)
                .setZ(z)
                .build();
    }
}