package com.shoulaxiao.dao;

import com.shoulaxiao.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;

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
     * 分页查询
     */
    Page<Record> findList(Integer pageNum);

    /**
     * 条件查询
     * @param query
     * @return
     */
    public List<Record> find(Query query);


    /**
     * @param id 根据Id查询
     * @return
     */
    public Record findOne(String id);

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


}
