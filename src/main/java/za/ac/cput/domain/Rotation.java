/* Rotation.java
   Rotation POJO class
   Author: Siyabulela Mgijima (230680305)
   Date: 11 May 2025
*/
package za.ac.cput.domain;

public class Rotation {
    protected double x;
    protected double y;
    protected double z;

    private Rotation() {
    }

    private Rotation(Builder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.z = builder.z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Rotation{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public static class Builder {
        protected double x;
        protected double y;
        protected double z;

        public Builder setX(double x) {
            this.x = x;
            return this;
        }

        public Builder setY(double y) {
            this.y = y;
            return this;
        }

        public Builder setZ(double z) {
            this.z = z;
            return this;
        }

        public Builder copy(Rotation rotation) {
            this.x = rotation.x;
            this.y = rotation.y;
            this.z = rotation.z;
            return this;
        }

        public Rotation build() {
            return new Rotation(this);
        }
    }
}


