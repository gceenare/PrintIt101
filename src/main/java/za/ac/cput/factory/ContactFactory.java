package za.ac.cput.factory;

import za.ac.cput.domain.Contact;
import za.ac.cput.util.Helper;

public class ContactFactory {
    public static Contact createContact(int contactId, String phoneNumber, String email){
        if(Helper.intIsNull(contactId) || Helper.isNullOrEmpty(phoneNumber) || !Helper.isValidEmail(email)){
            return null;
        }
        return new Contact.Builder().setContactId(contactId).setPhoneNumber(phoneNumber).setEmail(email).build();
    }
}
