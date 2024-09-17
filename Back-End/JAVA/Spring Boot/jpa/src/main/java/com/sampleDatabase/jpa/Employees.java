package com.sampleDatabase.jpa;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table( name = "employee")
public class Employees {

    @Id
    @Column(name = "empid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    @Column(name = "dep_id")
    private int depId;

    @Column(name = "address")
    private String address;

    @Column(name = "salary")
    private double salary;

    public Employees() {

    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "empId=" + empId +
                ", depId='" + depId + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}
