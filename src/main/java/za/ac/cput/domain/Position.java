/* Position.java
   Position POJO class
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025 */
package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int positionId;

    private double x;
    private double y;

    protected Position() {
    }

    private Position(Builder builder) {
        this.positionId = builder.positionId;
        this.x = builder.x;
        this.y = builder.y;
    }

    public int getPositionId() {
        return positionId;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return positionId == position.positionId &&
                Double.compare(position.x, x) == 0 &&
                Double.compare(position.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionId, x, y);
    }

    public static class Builder {
        private int positionId;
        private double x;
        private double y;

        public Builder setPositionId(int positionId) {
            this.positionId = positionId;
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

        public Builder copy(Position position) {
            this.positionId = position.positionId;
            this.x = position.x;
            this.y = position.y;
            return this;
        }

        public Position build() {
            return new Position(this);
        }
    }
}


