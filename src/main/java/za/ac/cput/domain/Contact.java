package za.ac.cput.domain;

public class Contact {
    protected int contactId;
    protected int phoneNumber;
    protected String email;

    private Contact(){
    }

    private Contact(Builder builder){
        this.contactId = builder.contactId;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
    }

    public int getContactId() {
        return contactId;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }

    public static class Builder{
        protected int contactId;
        protected int phoneNumber;
        protected String email;

        public Builder setContactId(int contactId) {
            this.contactId = contactId;
            return this;
        }

        public Builder setPhoneNumber(int phoneNumber) {
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
