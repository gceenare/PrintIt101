
package za.ac.cput.domain;

public class Scale {
    protected double x;
    protected double y;
    protected double z;

    private Scale() {
    }

    private Scale(Builder builder) {
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
        return "Scale{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public static class Builder {
        private double x;
        private double y;
        private double z;

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

        public Builder copy(Scale scale) {
            this.x = scale.x;
            this.y = scale.y;
            this.z = scale.z;
            return this;
        }

        public Scale build() {
            return new Scale(this);
        }
    }
}
