package za.ac.cput.domain;

public class Design {
    private int designId;
    private String filePath;

    private Design() {}

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
