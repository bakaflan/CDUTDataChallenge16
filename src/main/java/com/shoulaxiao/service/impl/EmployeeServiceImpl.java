package com.shoulaxiao.service.impl;

import com.shoulaxiao.entity.Employee;
import com.shoulaxiao.service.EmployeeService;
import com.shoulaxiao.util.mongo.EmplyeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmplyeeDao emplyeeDao;

    /**
     * 插入
     * @param employee
     */
    public void insert(Employee employee){
        emplyeeDao.insert(employee);
    }


    public void insertAll(List<Employee> employees){
        emplyeeDao.insertAll(employees);
    }

    public void deleteAll(){
        emplyeeDao.deleteAll();
    }

}
