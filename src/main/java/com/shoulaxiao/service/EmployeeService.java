package com.shoulaxiao.service;

import com.shoulaxiao.entity.Employee;

import java.util.List;

public interface EmployeeService {

    void insert(Employee employee);

    void insertAll(List<Employee> employees);

    void deleteAll();
}
