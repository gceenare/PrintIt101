/* Rotation.java
   Rotation POJO class
   Author: Siyabulela Mgijima (230680305)
   Date: 11 May 2025 */
package za.ac.cput.factory;

import za.ac.cput.domain.Scale;

public class ScaleFactory {
    public static Scale createScale(double x, double y, double z) {
        if (x <= 0 || y <= 0 || z <= 0) {
            return null;
        }
        return new Scale.Builder()
                .setX(x)
                .setY(y)
                .setZ(z)
                .build();
    }
}