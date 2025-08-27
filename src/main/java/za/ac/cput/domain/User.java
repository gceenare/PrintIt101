package za.ac.cput.domain;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    protected Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    protected Contact contact;

    protected String firstName;
    protected String lastName;
    protected String userName;
    protected String password;
    protected String role;

    protected User() {}

    protected User(Builder<?> builder) {
        this.userId = builder.userId;
        this.address = builder.address;
        this.contact = builder.contact;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.userName = builder.userName;
        this.password = builder.password;
        this.role = builder.role;
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
                ", role='" + role + '\'' +
                '}';
    }

    // Generic Builder
    public static abstract class Builder<T extends Builder<T>> {
        private Long userId;
        private Address address;
        private Contact contact;
        private String firstName;
        private String lastName;
        private String userName;
        private String password;
        private String role;

        public T setUserId(Long userId) {
            this.userId = userId;
            return self();
        }

        public T setAddress(Address address) {
            this.address = address;
            return self();
        }

        public T setContact(Contact contact) {
            this.contact = contact;
            return self();
        }

        public T setFirstName(String firstName) {
            this.firstName = firstName;
            return self();
        }

        public T setLastName(String lastName) {
            this.lastName = lastName;
            return self();
        }

        public T setUserName(String userName) {
            this.userName = userName;
            return self();
        }

        public T setPassword(String password) {
            this.password = password;
            return self();
        }

        public T setRole(String role) {
            this.role = role;
            return self();
        }

        protected abstract T self();

        public abstract User build();

        public T copy(User user) {
            this.userId = user.userId;
            this.address = user.address;
            this.contact = user.contact;
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.userName = user.userName;
            this.password = user.password;
            this.role = user.role;
            return self();
        }
    }
}
