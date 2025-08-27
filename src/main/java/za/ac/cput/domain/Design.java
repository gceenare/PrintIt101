package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "designs") // lowercase is conventional for table names
public class Design {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int designId;

    private String filePath;

    // Default constructor for JPA
    protected Design() {}

    private Design(Builder builder) {
        this.designId = builder.designId;
        this.filePath = builder.filePath;
    }

    public int getDesignId() {
        return designId;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public String toString() {
        return "Design{" +
                "designId=" + designId +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Design)) return false;
        Design design = (Design) o;
        return designId == design.designId && Objects.equals(filePath, design.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(designId, filePath);
    }

    // Builder pattern
    public static class Builder {
        private int designId;
        private String filePath;

        public Builder setDesignId(int designId) {
            this.designId = designId;
            return this;
        }

        public Builder setFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder copy(Design design) {
            this.designId = design.designId;
            this.filePath = design.filePath;
            return this;
        }

        public Design build() {
            return new Design(this);
        }
    }
}
