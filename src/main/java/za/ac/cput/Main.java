package za.ac.cput;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.User;

public class Main {
    public static void main(String[] args) {
        Address a = new Address.Builder().setAddressId(2).setBuildingName("CEEPEEYOUTEE").build();
        System.out.println(a);

        Contact c = new Contact.Builder().setContactId(1).setEmail("test@test.com").build();
        System.out.println(c);

        User u = new User.Builder().setUserId(2).setUserName("Thabiso").build();
        System.out.println(u);
    }
}
