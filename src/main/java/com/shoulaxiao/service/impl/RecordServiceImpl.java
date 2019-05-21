package com.shoulaxiao.service.impl;

import com.shoulaxiao.entity.Record;
import com.shoulaxiao.service.RecordService;
import com.shoulaxiao.util.mongo.RecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordDao recordDao;

    public void insert(Record record){
       recordDao.insert(record);
    }

    public void insertAll(List<Record> records){
      recordDao.insertAll(records);
    }

    public List<Record> findByFiledName(String filedName,String keyWord){
        return recordDao.findByFiledName(filedName,keyWord);
    }


    public Page<Record> findList(Integer pageNum){
        return recordDao.findList(pageNum);
    }

    public void deleteAll(){
        recordDao.deleteAll();
    }

}
