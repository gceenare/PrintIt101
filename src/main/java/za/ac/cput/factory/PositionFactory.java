/* PositionFactory.java
   Position Factory class
   Author: Siyabulela Mgijima (230680305)
   Date: 18 May 2025 */
package za.ac.cput.factory;

import za.ac.cput.domain.Position;

public class PositionFactory {

    public static Position createPosition(double x, double y, double z) {

        return new Position.Builder()
                .setX(x)
                .setY(y)
                .setZ(z)
                .build();
    }
}
