package com.shoulaxiao.dao;

import com.shoulaxiao.entity.Record;

import java.util.List;

public interface IRecord {

    /**
     * 单个插入
     * @param record
     */
    void insert(Record record);

    /**
     * 批量插入
     * @param records
     */
    void insertAll(List<Record> records);

    /**
     * 查询所有
     */
    List<Record> findAll();

    /**
     * 根据字段查询
     * @param filedName
     * @param keyWord
     * @return
     */
    List<Record> findByFiledName(String filedName,String keyWord);



    /**
     * 删除所有
     */
    void deleteAll();

    /**
     * 计数
     * @param criteriaRecord
     * @return
     */
    long count(Record criteriaRecord);
}
