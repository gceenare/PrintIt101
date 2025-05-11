package za.ac.cput;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;

public class Main {
    public static void main(String[] args) {
        Address a = new Address.Builder().setAddressId(2).setBuildingName("CEEPEEYOUTEE").build();
        System.out.println(a);

        Contact c = new Contact.Builder().setContactId(1).setEmail("test@test.com").build();
        System.out.println(c);
    }
}
