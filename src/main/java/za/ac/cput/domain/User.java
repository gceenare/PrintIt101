/*Address.java
  Contact POJO class
  Author: Thabiso Mbatha (22016299)
  Date: 11 May 2025 */
package za.ac.cput.domain;

public class User {
    protected int userId;
    protected int addressId;
    protected int contactId;
    protected String firstName;
    protected String lastName;
    protected String userName;
    protected String password;
    protected String role;

    protected User(){
    }

    protected User(Builder builder){
        this.userId = builder.userId;
        this.addressId = builder.addressId;
        this.contactId = builder.contactId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.userName = builder.userName;
        this.password = builder.password;
        this.role = builder.role;
    }

    public int getUserId() {
        return userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", address=" + addressId +
                ", contact=" + contactId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public static class Builder{
        private int userId;
        private int addressId;
        private int contactId;
        private String firstName;
        private String lastName;
        private String userName;
        private String password;
        private String role;

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setAddressId(int addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder setContactId(int contactId) {
            this.contactId = contactId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder copy(User user){
            this.userId = user.userId;
            this.addressId = user.addressId;
            this.contactId = user.contactId;
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.userName = user.userName;
            this.password = user.password;
            this.role = user.role;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
