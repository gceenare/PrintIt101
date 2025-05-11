package za.ac.cput.domain;

public class User {
    protected int userId;
    protected Address address;
    protected Contact contact;
    protected String firstName;
    protected String lastName;
    protected String userName;
    protected String password;
    protected String role;

    private User(){
    }

    private User(Builder builder){
        this.userId = builder.userId;
        this.contact = builder.contact;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.userName = builder.userName;
        this.password = builder.password;
        this.role = builder.role;
    }

    public int getUserId() {
        return userId;
    }

    public Address getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
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
                ", address=" + address +
                ", contact=" + contact +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public static class Builder{
        private int userId;
        private Address address;
        private Contact contact;
        private String firstName;
        private String lastName;
        private String userName;
        private String password;
        private String role;

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setContact(Contact contact) {
            this.contact = contact;
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
            this.address = user.address;
            this.contact = user.contact;
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
