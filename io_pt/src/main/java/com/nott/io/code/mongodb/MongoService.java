package com.nott.io.code.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.nott.io.code.common.utils.AesEncrypt;
import com.nott.io.code.common.utils.DateUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author: Wangjun
 * @Description:
 * @Date: created in 2019/4/29
 * @Modified By:
 */
@Service
public class MongoService {

    @Autowired
    protected GridFsOperations gridFsOperations;
    @Autowired
    private MongoQueryService mongoQueryService;
    private static final Logger log = LoggerFactory.getLogger(MongoService.class);

    public MongoService() {
    }

    public void save(String id, Map<String, Object> map) {
        GridFSFile gridFSDBFile = this.mongoQueryService.findOneById(id);
        gridFSDBFile.getMetadata().putAll(map);
    }

    public String storeEncryptedFile(MongoFile file) {
        String content = new String(file.getContent());
        String encrypt = AesEncrypt.encrypt(content);
        InputStream inputStream = new ByteArrayInputStream(encrypt.getBytes());
        DBObject metadata = this.getDBObject(file);
        metadata.put("encrypted", true);
        metadata.put("storeTime", DateUtils.getCurrentSecond());
        ObjectId objectId = this.gridFsOperations.store(inputStream, file.getName(), file.getMimeType(), metadata);
        try {
            inputStream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return objectId.toString();
    }

    public String storeFile(MongoFile file) {
        ByteArrayInputStream inputStream = null;

        String var5;
        try {
            DBObject metadata = this.getDBObject(file);
            metadata.put("encrypted", false);
            metadata.put("storeTime", DateUtils.getCurrentSecond());
            inputStream = new ByteArrayInputStream(file.getContent());
            ObjectId objectId = this.gridFsOperations.store(inputStream, file.getName(), file.getMimeType(), metadata);
            var5 = objectId.toString();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }

        return var5;
    }

    private DBObject getDBObject(MongoFile file) {
        DBObject metadata = new BasicDBObject();
        if (file.getMetadata() != null) {
            Iterator var3 = file.getMetadata().entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var3.next();
                metadata.put((String) entry.getKey(), entry.getValue());
            }
        }

        return metadata;
    }

    public void deleteGridById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        this.gridFsOperations.delete(query);
    }

    public void deleteById(List<String> list) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").in(list));
        this.gridFsOperations.delete(query);
    }

}
