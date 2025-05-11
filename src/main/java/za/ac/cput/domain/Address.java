package za.ac.cput.domain;

public class Address {
    protected int addressId;
    protected int propertyNumber;
    protected String buildingName;
    protected int unitNumber;
    protected int poBoxNumber;
    protected String street;
    protected String municipality;
    protected String province;
    protected String postalCode;
    protected String country;

    private Address(){
    }

    private Address(Builder builder){
        this.addressId = builder.addressId;
        this.propertyNumber = builder.propertyNumber;
        this.buildingName = builder.buildingName;
        this.unitNumber = builder.unitNumber;
        this.poBoxNumber = builder.poBoxNumber;
        this.street = builder.street;
        this.municipality = builder.municipality;
        this.province = builder.province;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getPropertyNumber() {
        return propertyNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public int getPoBoxNumber() {
        return poBoxNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", propertyNumber=" + propertyNumber +
                ", buildingName='" + buildingName + '\'' +
                ", unitNumber=" + unitNumber +
                ", poBoxNumber=" + poBoxNumber +
                ", street='" + street + '\'' +
                ", municipality='" + municipality + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public static class Builder{
        protected int addressId;
        protected int propertyNumber;
        protected String buildingName;
        protected int unitNumber;
        protected int poBoxNumber;
        protected String street;
        protected String municipality;
        protected String province;
        protected String postalCode;
        protected String country;

        public Builder setAddressId(int addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder setPropertyNumber(int propertyNumber) {
            this.propertyNumber = propertyNumber;
            return this;
        }

        public Builder setBuildingName(String buildingName) {
            this.buildingName = buildingName;
            return this;
        }

        public Builder setUnitNumber(int unitNumber) {
            this.unitNumber = unitNumber;
            return this;
        }

        public Builder setPoBoxNumber(int poBoxNumber) {
            this.poBoxNumber = poBoxNumber;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setMunicipality(String municipality) {
            this.municipality = municipality;
            return this;
        }

        public Builder setProvince(String province) {
            this.province = province;
            return this;
        }

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder copy(Address address){
            this.addressId = address.addressId;
            this.propertyNumber = address.propertyNumber;
            this.buildingName = address.buildingName;
            this.unitNumber = address.unitNumber;
            this.poBoxNumber = address.poBoxNumber;
            this.street = address.street;
            this.municipality = address.municipality;
            this.province = address.province;
            this.postalCode = address.postalCode;
            this.country = address.country;
            return this;
        }

        public Address build(){
            return new Address(this);
        }
    }
}
