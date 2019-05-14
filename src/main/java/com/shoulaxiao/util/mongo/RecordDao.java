package com.shoulaxiao.util.mongo;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.shoulaxiao.dao.IEmployeeDao;
import com.shoulaxiao.dao.IRecord;
import com.shoulaxiao.entity.Record;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class RecordDao extends AbstractBaseMongoTemplete implements IRecord {

    public void insert(Record record){
        mongoTemplate.insert(record);
    }

    public void insertAll(List<Record> records){
        mongoTemplate.insertAll(records);
    }

    public List<Record> findAll(){
        return mongoTemplate.findAll(Record.class);
    }

    public List<Record> findByFiledName(String filedName,String keyWord){

//        Pattern pattern=Pattern.compile("^.*keyWord.*$",Pattern.CASE_INSENSITIVE);
        Query query=Query.query(Criteria.where(filedName).regex(keyWord));
        return mongoTemplate.find(query,Record.class);
    }

    public long count(Record criteriaRecord){
        Query query=getQuery(criteriaRecord);
        
        return mongoTemplate.count(query,Record.class);
    }

    private Query getQuery(Record criteriaRecord) {
        if (criteriaRecord==null){
            criteriaRecord=new Record();
        }
        Query query=new Query();
        return query;
    }

    public void deleteAll(){
        mongoTemplate.dropCollection(Record.class);
    }


}
