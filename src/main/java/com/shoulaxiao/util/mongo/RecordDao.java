package com.shoulaxiao.util.mongo;

import com.shoulaxiao.dao.IRecord;
import com.shoulaxiao.entity.PageInfo;
import com.shoulaxiao.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
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


    public List<Record> findByFiledName(String filedName,String keyWord){
        Query query=Query.query(Criteria.where(filedName).regex(keyWord));

        return mongoTemplate.find(query, Record.class);
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

    /**
     * 分页查询
     */
   public Page<Record> findList(Integer pageNum){
       PageInfo pageInfo=new PageInfo();
       Query query=new Query();
       pageInfo.setPagenumber(pageNum);
       pageInfo.setPagesize(10);
       Long count=mongoTemplate.count(query,Record.class);
       List<Record> list = mongoTemplate.find(query.with(pageInfo), Record.class);
       Page<Record> pageList=new PageImpl<Record>(list,pageInfo,count);
       return pageList;
    }

    /**
     * 条件查询
     * @param query
     * @return
     */
    public List<Record> find(Query query){
        return mongoTemplate.find(query, Record.class);
    }


    /**
     * @param id 根据Id查询
     * @return
     */
    public Record findOne(String id){
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        return mongoTemplate.findOne(query,Record.class);
    }


}
