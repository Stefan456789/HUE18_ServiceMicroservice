package me.stefan.carmicroservice.server.services;


import me.stefan.carmicroservice.server.businesslogic.Employee;
import me.stefan.carmicroservice.server.dtos.EmployeeDto;
import me.stefan.carmicroservice.server.resources.EmployeeResource;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDataService {
    private HashMap<Integer, Employee> employees = new HashMap<>();
    private int id = 0;
    public void createInitialEmployees() {
        Employee e = new Employee();
        e.setId(id++);
        e.setName("Name");
        e.setAddress("123;456");
        this.employees.put(e.getId(), e);
    }

    public List<EmployeeResource> getEmployeeResources() {
        return employees.values().stream().map(this::convertEmployeeToEmployeeResource).collect(Collectors.toList());
    }

    private EmployeeResource convertEmployeeToEmployeeResource(Employee employee) {
        EmployeeResource employeeResource = new EmployeeResource();
        employeeResource.setId(id++);
        employeeResource.setName(employee.getName());
        employeeResource.setLatitude(employee.getAddress().split(";")[0]);
        employeeResource.setLongitude(employee.getAddress().split(";")[1]);
        return employeeResource;
    }

    public EmployeeResource addEmployee(EmployeeDto employeeDto) {
        Employee newEmployee = new Employee();
        newEmployee.setId(this.employees.size());
        newEmployee.setName(employeeDto.getName());
        newEmployee.setAddress(employeeDto.getLatitude()+ ";" +employeeDto.getLongitude());
        this.employees.put(newEmployee.getId(), newEmployee);
        return convertEmployeeToEmployeeResource(newEmployee);
    }



}
