/* PlacementDataFactory.java
   PlacementData Factory class
   Author: Siyabulela Mgijima (230680305)
   Date: 18 May 2025 */
package za.ac.cput.factory;

import za.ac.cput.domain.PlacementData;
import za.ac.cput.domain.Position;
import za.ac.cput.domain.Rotation;
import za.ac.cput.domain.Scale;
import za.ac.cput.util.Helper;

public class PlacementDataFactory {
    public static PlacementData createPlacementData(Position position, Rotation rotation, Scale scale) {
        if (!Helper.areAllObjectsNotNull(position, rotation, scale)) {
            return null;
        }

        return new PlacementData.Builder()
                .setPosition(position)
                .setRotation(rotation)
                .setScale(scale)
                .build();
    }
}