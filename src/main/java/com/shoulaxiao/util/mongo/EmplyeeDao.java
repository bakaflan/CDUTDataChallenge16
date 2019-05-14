package com.shoulaxiao.util.mongo;

import com.shoulaxiao.dao.IEmployeeDao;
import com.shoulaxiao.entity.Employee;

import java.util.List;

public class EmplyeeDao extends AbstractBaseMongoTemplete implements IEmployeeDao {

    public void insert(Employee employee){
        mongoTemplate.insert(employee);
    }


    public void insertAll(List<Employee> employees){
        mongoTemplate.insertAll(employees);
    }

    public void deleteById(String id){
    }


    public void deleteAll(){
        mongoTemplate.dropCollection(Employee.class);
    }
}
