/* ScaleFactory.java
   Scale Factory class
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025 */
package za.ac.cput.factory;

import za.ac.cput.domain.Scale;
import za.ac.cput.util.Helper;

public class ScaleFactory {
    public static Scale createScale(double value) {
        if (Helper.scaleIsInvalid(value)) {
            return null;
        }

        return new Scale.Builder()
                .setValue(value)
                .build();
    }
}
