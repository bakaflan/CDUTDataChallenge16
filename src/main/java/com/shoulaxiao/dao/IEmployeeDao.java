package com.shoulaxiao.dao;

import com.shoulaxiao.entity.Employee;

import java.util.List;

public interface IEmployeeDao {

    /**
     * 新增
     * @param employee
     */
     void insert(Employee employee);

    /**
     * 批量新增
     * @param employees
     */
    void insertAll(List<Employee> employees);

    /**
     * 主键删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 批量删除
     */
    void deleteAll();
}
