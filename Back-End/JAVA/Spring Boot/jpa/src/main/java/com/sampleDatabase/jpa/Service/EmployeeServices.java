package com.sampleDatabase.jpa.Service;

import com.sampleDatabase.jpa.Employees;
import com.sampleDatabase.jpa.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeServices {

    @Autowired
    private Repo repo;

    public void postEmployee(Employees employees) {
        this.repo.save(employees);
    }

    public Employees getEmployeeById(int empId) {
        return this.repo.getReferenceById(empId);
    }

    public void deleteEmployeeById(int empId) {
        this.repo.deleteById(empId);
    }

    public void updateEmployee(Employees emp) {
        this.repo.save(emp);
    }

    public List<Employees> getEmployees() {
        return this.repo.findAll();
    }
}
