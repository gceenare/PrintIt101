/* PlacementData.java
   PlacementData POJO class
   Author: Siyabulela Mgijima (230680305)
   Date: 11 May 2025
*/
package za.ac.cput.domain;

public class PlacementData {
    protected int placementDataId;
    protected Position position;
    protected Rotation rotation;
    protected Scale scale;


    private PlacementData() {
    }

    private PlacementData(Builder builder) {
        this.placementDataId = builder.placementDataId;
        this.position = builder.position;
        this.rotation = builder.rotation;
        this.scale = builder.scale;
    }

    public int getPlacementDataId() {
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

    public static class Builder {
        protected int placementDataId;
        protected Position position;
        protected Rotation rotation;
        protected Scale scale;


        public Builder setPlacementDataId(int placementDataId) {
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
