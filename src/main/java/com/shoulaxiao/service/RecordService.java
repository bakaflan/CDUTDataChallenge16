package com.shoulaxiao.service;

import com.shoulaxiao.entity.Record;

import java.util.List;

public interface RecordService {

    void insert(Record record);

    void insertAll(List<Record> records);

    /**
     * 根据字段进行查找
     * @param filedName
     * @param keyWord
     * @return
     */
    List<Record> findByFiledName(String filedName,String keyWord);

    List<Record> findAll();

    void deleteAll();
}
