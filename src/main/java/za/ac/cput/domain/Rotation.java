/* Rotation.java
   Rotation POJO class
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025
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

    @Column(nullable = false)
    private double angle;

    protected Rotation() {
    }

    private Rotation(Builder builder) {
        this.rotationId = builder.rotationId;
        this.angle = builder.angle;
    }

    public int getRotationId() {
        return rotationId;
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public String toString() {
        return "Rotation{" +
                "rotationId=" + rotationId +
                ", angle=" + angle +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rotation)) return false;
        Rotation rotation = (Rotation) o;
        return rotationId == rotation.rotationId &&
                Double.compare(rotation.angle, angle) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rotationId, angle);
    }

    public static class Builder {
        private int rotationId;
        private double angle;

        public Builder setRotationId(int rotationId) {
            this.rotationId = rotationId;
            return this;
        }

        public Builder setAngle(double angle) {
            this.angle = angle;
            return this;
        }

        public Builder copy(Rotation rotation) {
            this.rotationId = rotation.rotationId;
            this.angle = rotation.angle;
            return this;
        }

        public Rotation build() {
            return new Rotation(this);
        }
    }
}
