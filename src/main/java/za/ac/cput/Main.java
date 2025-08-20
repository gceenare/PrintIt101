package za.ac.cput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import za.ac.cput.domain.*;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.UserFactory;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        Address a = new Address.Builder().setAddressId(2).setBuildingName("CEEPEEYOUTEE").build();
        System.out.println(a);

        Contact c = new Contact.Builder().setContactId(1).setEmail("test@test.com").build();
        System.out.println(c);

        User u = new User.Builder().setUserId(2).setUserName("Thabiso").build();
        System.out.println(u);

        Position position = new Position.Builder().setX(10).setY(8).setZ(6).build();
        System.out.println(position);

        Rotation rotation = new Rotation.Builder().setX(20).setY(15).setZ(10).build();
        System.out.println(rotation);

        Scale scale = new Scale.Builder().setX(3).setY(6).setZ(9).build();
        System.out.println(scale);

        PlacementData placementData = new PlacementData.Builder().setPlacementDataId(21).setPosition(position).setRotation(rotation).setScale(scale).build();
        System.out.println(placementData);


        // ----------------------------- AddressFactory Test -----------------------------
        Address postalAddress = AddressFactory.createPostalAddress(
                1, 1234, "Oak Street", "Cape Town", "Western Cape", "8001", "South Africa"
        );
        System.out.println("Postal Address:\n" + postalAddress);

        Address residentialAddress = AddressFactory.createResidentialAddress(
                2, 56, "Sunrise Apartments", 12, "Palm Road", "Durban", "KwaZulu-Natal", "4001", "South Africa"
        );
        System.out.println("\nResidential Address:\n" + residentialAddress);

        Address businessAddress = AddressFactory.createBusinessAddress(
                3, 789, "TechHub Building", "Main Road", "Johannesburg", "Gauteng", "2000", "South Africa"
        );
        System.out.println("\nBusiness Address:\n" + businessAddress);

        Address fullAddress = AddressFactory.createFullAddress(
                4, 100, "Global Towers", 44, 555, "Market Street", "Pretoria", "Gauteng", "0002", "South Africa"
        );
        System.out.println("\nFull Address:\n" + fullAddress);

        // ----------------------------- ContactFactory Test -----------------------------
        Contact contact = ContactFactory.createContact(
                10, "0812345678", "test@example.com"
        );
        System.out.println("\nContact:\n" + contact);

        // ----------------------------- UserFactory Test -----------------------------
        User user = UserFactory.createUser(
                99,
                residentialAddress.getAddressId(),
                contact.getContactId(),
                "Thabiso",
                "Mbatha",
                "thabiso.mbatha",
                "securePassword123",
                "Admin"
        );
        System.out.println("\nUser:\n" + user);

    }
}
