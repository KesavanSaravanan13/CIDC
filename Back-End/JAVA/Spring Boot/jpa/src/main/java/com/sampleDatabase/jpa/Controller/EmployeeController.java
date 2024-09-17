package com.sampleDatabase.jpa.Controller;

import com.sampleDatabase.jpa.Service.EmployeeServices;
import com.sampleDatabase.jpa.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServices employee;

    @RequestMapping("/employees")
    public List<Employees> getEmployee() {
        return employee.getEmployees();
    }

    @RequestMapping("/employees/{empId}")
    public Employees getEmployeeById(@PathVariable int empId) {
        return employee.getEmployeeById(empId);
    }

    @PostMapping("/employees")
    public void postEmployee(@RequestBody Employees employees) {
        employee.postEmployee(employees);
    }

    @PutMapping("/employees/{empId}")
    public void updateEmployee(@RequestBody Employees employees) {
        employee.updateEmployee(employees);
    }

    @DeleteMapping("/employees/{empId}")
    public void deleteEmployeeById(@PathVariable int empId) {
        employee.deleteEmployeeById(empId);
    }

}