package za.ac.cput.factory;

import za.ac.cput.domain.Position;

public class PositionFactory {

    public static Position createPosition(double x, double y) {
        // Validate: x and y must be positive
        if (x <= 0 || y <= 0) {
            System.out.println("Invalid Position values: x=" + x + ", y=" + y);
            return null;
        }

        // Use Builder to create Position
        return new Position.Builder()
                .setX(x)
                .setY(y)
                .build();
    }
}
