/* Scale.java
   Scale POJO class
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025
*/
package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "scales")
public class Scale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scaleId;

    private double value;

    protected Scale() {
    }

    private Scale(Builder builder) {
        this.scaleId = builder.scaleId;
        this.value = builder.value;
    }

    public int getScaleId() {
        return scaleId;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Scale{" +
                "scaleId=" + scaleId +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scale)) return false;
        Scale scale = (Scale) o;
        return scaleId == scale.scaleId &&
                Double.compare(scale.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scaleId, value);
    }

    public static class Builder {
        private int scaleId;
        private double value;

        public Builder setScaleId(int scaleId) {
            this.scaleId = scaleId;
            return this;
        }

        public Builder setValue(double value) {
            this.value = value;
            return this;
        }

        public Builder copy(Scale scale) {
            this.scaleId = scale.scaleId;
            this.value = scale.value;
            return this;
        }

        public Scale build() {
            return new Scale(this);
        }
    }
}
