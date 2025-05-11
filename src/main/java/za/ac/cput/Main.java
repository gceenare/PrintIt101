package za.ac.cput;

import za.ac.cput.domain.*;

public class Main {
    public static void main(String[] args) {
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
    }
}
