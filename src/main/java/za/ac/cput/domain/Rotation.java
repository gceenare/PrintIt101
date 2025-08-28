/* Rotation.java
   Rotation POJO class
   Author: Siyabulela Mgijima
   Date: 28 Aug 2025
*/

package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rotations")
public class Rotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rotationId;

    private double x;
    private double y;
    private double z;

    protected Rotation() {}

    private Rotation(Builder builder) {
        this.rotationId = builder.rotationId;
        this.x = builder.x;
        this.y = builder.y;
        this.z = builder.z;
    }

    public int getRotationId() {
        return rotationId;
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
                "rotationId=" + rotationId +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rotation rotation = (Rotation) o;
        return rotationId == rotation.rotationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rotationId);
    }

    public static class Builder {
        private int rotationId;
        private double x;
        private double y;
        private double z;

        public Builder setRotationId(int rotationId) {
            this.rotationId = rotationId;
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

        public Builder copy(Rotation rotation) {
            this.rotationId = rotation.rotationId;
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
