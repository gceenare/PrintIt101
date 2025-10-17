package za.ac.cput.service;

import za.ac.cput.domain.Contact;
import java.util.List;

public interface IContactService extends IService<Contact, Integer> {
    List<Contact> getAll();
    Contact findByEmail(String email);
    Contact findByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
