package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.util.Helper;

public class AddressFactory {
    public static Address createPostalAddress(int addressId, int poBoxNumber, String street, String municipality, String province, String postalCode,  String country) {
        if(Helper.intIsNull(addressId) || Helper.intIsNull(poBoxNumber) || Helper.isNullOrEmpty(street) || Helper.isNullOrEmpty(municipality) || Helper.isNullOrEmpty(province) || Helper.isNullOrEmpty(postalCode) || Helper.isNullOrEmpty(country)) {
            return null;
        }
        return new Address.Builder()
                .setAddressId(addressId)
                .setPoBoxNumber(poBoxNumber)
                .setStreet(street)
                .setMunicipality(municipality)
                .setProvince(province)
                .setPostalCode(postalCode)
                .setCountry(country)
                .build();
    }

    public static Address createResidentialAddress(int addressId, int propertyNumber, String buildingName, int unitNumber, String street, String municipality, String province, String postalCode, String country) {
        if (Helper.intIsNull(addressId) || Helper.intIsNull(propertyNumber) || Helper.isNullOrEmpty(street) || Helper.isNullOrEmpty(municipality) || Helper.isNullOrEmpty(province) || Helper.isNullOrEmpty(postalCode) || Helper.isNullOrEmpty(country)) {
            return null;
        }

        return new Address.Builder()
                .setAddressId(addressId)
                .setPropertyNumber(propertyNumber)
                .setBuildingName(buildingName)
                .setUnitNumber(unitNumber)
                .setStreet(street)
                .setMunicipality(municipality)
                .setProvince(province)
                .setPostalCode(postalCode)
                .setCountry(country)
                .build();
    }

    public static Address createBusinessAddress(int addressId, int propertyNumber, String buildingName, String street, String municipality, String province, String postalCode, String country) {
        if (Helper.intIsNull(addressId) || Helper.intIsNull(propertyNumber) || Helper.isNullOrEmpty(buildingName) || Helper.isNullOrEmpty(street) || Helper.isNullOrEmpty(municipality) || Helper.isNullOrEmpty(province) || Helper.isNullOrEmpty(postalCode) || Helper.isNullOrEmpty(country)) {
            return null;
        }

        return new Address.Builder()
                .setAddressId(addressId)
                .setPropertyNumber(propertyNumber)
                .setBuildingName(buildingName)
                .setStreet(street)
                .setMunicipality(municipality)
                .setProvince(province)
                .setPostalCode(postalCode)
                .setCountry(country)
                .build();
    }

    public static Address createFullAddress(int addressId, int propertyNumber, String buildingName, int unitNumber, int poBoxNumber, String street, String municipality, String province, String postalCode, String country) {
        if (Helper.intIsNull(addressId) || Helper.intIsNull(propertyNumber) || Helper.intIsNull(poBoxNumber) || Helper.isNullOrEmpty(street) || Helper.isNullOrEmpty(municipality) || Helper.isNullOrEmpty(province) || Helper.isNullOrEmpty(postalCode) || Helper.isNullOrEmpty(country)) {
            return null;
        }

        return new Address.Builder()
                .setAddressId(addressId)
                .setPropertyNumber(propertyNumber)
                .setBuildingName(buildingName)
                .setUnitNumber(unitNumber)
                .setPoBoxNumber(poBoxNumber)
                .setStreet(street)
                .setMunicipality(municipality)
                .setProvince(province)
                .setPostalCode(postalCode)
                .setCountry(country)
                .build();
    }
}
