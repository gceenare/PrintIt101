/*   Contact.java
     Contact POJO class
     Author: Thabiso Mbatha (22016299)
     Date: 11 May 2025 */
package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    private String phoneNumber;
    private String email;

    protected Contact() {}

    private Contact(Builder builder){
        this.contactId = builder.contactId;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
    }

    public int getContactId() { return contactId; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return contactId == contact.contactId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId);
    }

    public static class Builder {
        private int contactId;
        private String phoneNumber;
        private String email;

        public Builder setContactId(int contactId) {
            this.contactId = contactId;
            return this;
        }
        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder copy(Contact contact){
            this.contactId = contact.contactId;
            this.phoneNumber = contact.phoneNumber;
            this.email = contact.email;
            return this;
        }

        public Contact build(){
            return new Contact(this);
        }
    }
}
