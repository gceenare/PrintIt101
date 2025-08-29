/* PlacementData.java
   PlacementData POJO class
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025
*/
package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "placement_data")
public class PlacementData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer placementDataId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    protected Position position;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rotation_id")
    protected Rotation rotation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scale_id")
    private Scale scale;

    protected PlacementData() {
        // JPA requires a default constructor
    }

    private PlacementData(Builder builder) {
        this.placementDataId = builder.placementDataId;
        this.position = builder.position;
        this.rotation = builder.rotation;
        this.scale = builder.scale;
    }

    public Integer getPlacementDataId() {
        return placementDataId;
    }

    public Position getPosition() {
        return position;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public Scale getScale() {
        return scale;
    }

    @Override
    public String toString() {
        return "PlacementData{" +
                "placementDataId=" + placementDataId +
                ", position=" + position +
                ", rotation=" + rotation +
                ", scale=" + scale +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlacementData)) return false;
        PlacementData that = (PlacementData) o;
        return Objects.equals(placementDataId, that.placementDataId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placementDataId);
    }

    public static class Builder {
        private Integer placementDataId;
        protected Position position;
        protected Rotation rotation;
        protected Scale scale;

        public Builder setPlacementDataId(Integer placementDataId) {
            this.placementDataId = placementDataId;
            return this;
        }

        public Builder setPosition(Position position) {
            this.position = position;
            return this;
        }

        public Builder setRotation(Rotation rotation) {
            this.rotation = rotation;
            return this;
        }

        public Builder setScale(Scale scale) {
            this.scale = scale;
            return this;
        }

        public Builder copy(PlacementData placementData) {
            this.placementDataId = placementData.placementDataId;
            this.position = placementData.position;
            this.rotation = placementData.rotation;
            this.scale = placementData.scale;
            return this;
        }

        public PlacementData build() {
            return new PlacementData(this);
        }
    }
}
