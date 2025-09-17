package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends User {

    @Column(name = "position", nullable = false)
    protected String position;

    @Column(name = "staff_Discount", nullable = false)
    protected double staffDiscount;

    protected Employee() {
    }

    private Employee(Builder builder) {
        super(builder);
        this.position = builder.position;
        this.staffDiscount = builder.staffDiscount;
    }

    public String getPosition() {
        return position;
    }

    public double getStaffDiscount() {
        return staffDiscount;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "position='" + position + '\'' +
                ", staffDiscount=" + staffDiscount +
                "} " + super.toString();
    }

    public static class Builder extends User.Builder<Builder> {
        private String position;
        private double staffDiscount;

        public Builder setPosition(String position) {
            this.position = position;
            return this;
        }

        public Builder setStaffDiscount(double staffDiscount) {
            this.staffDiscount = staffDiscount;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder copy(Employee employee) {
            super.copy(employee);
            this.position = employee.position;
            this.staffDiscount = employee.staffDiscount;
            return this;
        }

        @Override
        public Employee build() {
            return new Employee(this);
        }
    }
}
