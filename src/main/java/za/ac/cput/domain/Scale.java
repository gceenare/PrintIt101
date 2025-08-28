/* Position.java
   Position POJO class
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025 */
package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "scales")
public class Scale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scaleId;

    private double x;
    private double y;
    private double z;

    protected Scale() {
    }

    private Scale(Builder builder) {
        this.scaleId = builder.scaleId;
        this.x = builder.x;
        this.y = builder.y;
        this.z = builder.z;
    }

    public int getScaleId() {
        return scaleId;
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
                "scaleId=" + scaleId +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scale)) return false;
        Scale scale = (Scale) o;
        return scaleId == scale.scaleId &&
                Double.compare(scale.x, x) == 0 &&
                Double.compare(scale.y, y) == 0 &&
                Double.compare(scale.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scaleId, x, y, z);
    }

    public static class Builder {
        private int scaleId;
        private double x;
        private double y;
        private double z;

        public Builder setScaleId(int scaleId) {
            this.scaleId = scaleId;
            return this;
        }

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
            this.scaleId = scale.scaleId;
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
