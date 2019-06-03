package com.nott.io.code.UpAndDown.service;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/6/3 10:42
 * @Modified By:
 **/
@Service(value = "upAndDownService")
public class UpAndDownService {

    private static final Logger log = LoggerFactory.getLogger(UpAndDownService.class);

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private GridFsTemplate gridFsTemplate;

    public String storeFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();

            InputStream inputStream = file.getInputStream();

            ObjectId storeId = gridFsTemplate.store(inputStream, fileName, contentType);

            return storeId.toString();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }

    public boolean deleteFile(String storeId) {
        try {

            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(storeId));
            gridFsTemplate.delete(query);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }

}
