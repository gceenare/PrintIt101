/*   Employee.java

     Employee POJO class

     Author: Zakhir McKinnon (22016299)

     Date: 11 May 2025 */

package za.ac.cput.domain;

public class Employee {
    protected String employeeId;
    protected double staffDiscount;
    protected String position;

    private Employee() {
    }

    private Employee(Builder builder) {
        this.employeeId = builder.employeeId;
        this.staffDiscount = builder.staffDiscount;
        this.position = builder.position;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public double getStaffDiscount() {
        return staffDiscount;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", staffDiscount=" + staffDiscount +
                ", position='" + position + '\'' +
                '}';
    }

    public static class Builder {
        private String employeeId;
        private double staffDiscount;
        private String position;

        public Builder setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder setStaffDiscount(double staffDiscount) {
            this.staffDiscount = staffDiscount;
            return this;
        }

        public Builder setPosition(String position) {
            this.position = position;
            return this;
        }

        public Builder copy(Employee employee) {
            this.employeeId = employee.employeeId;
            this.staffDiscount = employee.staffDiscount;
            this.position = employee.position;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
