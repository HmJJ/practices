package com.nott.io.code.mongodb;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.nott.io.code.common.exception.ServiceException;
import com.nott.io.code.common.utils.AesEncrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

/**
 * @Author: Wangjun
 * @Description:
 * @Date: created in 2019/4/29
 * @Modified By:
 */
@Service(value = "mongoQueryService")
public class MongoQueryService {

    private static final Logger log = LoggerFactory.getLogger(MongoQueryService.class);
    @Autowired
    protected GridFsOperations gridFsOperations;
    @Autowired
    private GridFSBucket gridFSBucket;

    public MongoQueryService() {
    }

    public GridFSFile findOneById(String id) {
        GridFSFile result = this.gridFsOperations.findOne((new Query()).addCriteria(Criteria.where("_id").is(id)));
        return result;
    }

    public byte[] find(String id) {
        GridFSFile file = this.findOneById(id);
        Object var3 = null;

        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            GridFsResource gridFsResource = this.convertGridFSFile2Resource(file);
            FileCopyUtils.copy(gridFsResource.getInputStream(), bao);
            byte[] result;
            if (this.isEncrypted(file)) {
                String content = bao.toString();
                String decrypt = AesEncrypt.decrypt(content);
                result = Base64.getDecoder().decode(decrypt);
            } else {
                result = bao.toByteArray();
            }

            return result;
        } catch (Exception var8) {
            log.error("read file:{} error:{}", id, var8.getMessage());
            throw new ServiceException("读取文件发生错误");
        }
    }

    private boolean isEncrypted(GridFSFile file) {
        Object obj = file.getMetadata().get("encrypted");
        return obj != null ? (Boolean)obj : false;
    }

    public String getFileContentType(String id) {
        GridFSFile file = this.findOneById(id);
        return null != file ? file.getContentType() : null;
    }

    private GridFsResource convertGridFSFile2Resource(GridFSFile gridFsFile) {
        GridFSDownloadStream gridFSDownloadStream = this.gridFSBucket.openDownloadStream(gridFsFile.getObjectId());
        return new GridFsResource(gridFsFile, gridFSDownloadStream);
    }

}
