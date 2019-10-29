package com.wayonsys.ecm.docsearch.elasticsearch.listener;

import com.alibaba.fastjson.JSONObject;
import com.wayonsys.ecm.docsearch.elasticsearch.service.EsUtils;
import com.wayonsys.ecm.docsearch.elasticsearch.vo.ElasticForm;
import com.wayonsys.ecm.document.domain.DocumentFile;
import com.wayonsys.ecm.document.service.DocumentFileQueryService;
import com.wayonsys.ecm.document.utils.DecodedMultipartFile;
import com.wayonsys.ecm.document.vo.DocumentFileRecord;
import com.wayonsys.ecm.document.vo.RabbitVo;
import com.wayonsys.ecm.mongodb.MongoQueryService;
import com.wayonsys.ecm.utils.FileUtils;
import com.wayonsys.framework.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/26 13:48
 * @Modified By:
 **/
@Service
public class ElasticListener {

    private static final Logger log = LoggerFactory.getLogger(ElasticListener.class);

    @Autowired
    private EsUtils esUtils;
    @Autowired
    private DocumentFileQueryService documentFileQueryService;
    @Autowired
    private MongoQueryService mongoQueryService;

    @RabbitListener(queuesToDeclare = @Queue("direct.es.queue"))
    public void queueListener(Message message) {
        RabbitVo info = (RabbitVo) FileUtils.byteToObject(message.getBody());
        String store = info.getStore();
        String index = info.getIndex();
        log.info(store);
        log.info(index);
        DocumentFileRecord documentFileRecord = documentFileQueryService.findByStore(store, null);
        byte[] buffer = mongoQueryService.find(store);
        String suffix = documentFileRecord.getFileSuffix();
        MultipartFile file = DecodedMultipartFile.base64ToMultipart(buffer, suffix);
        Map<String, Object> map = new HashMap<>();
        map.put("id", String.valueOf(documentFileRecord.getId()));
        map.put("name", documentFileRecord.getFileName());
        map.put("store", store);
        map.put("index", index);
        map.put("suffix", suffix);
        map.put("creator", documentFileRecord.getCreatedByName());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        map.put("createTime", documentFileRecord.getFileTime().format(dtf));
        map.put("tenantId", String.valueOf(documentFileRecord.getTenantId()));
        ElasticForm form = new ElasticForm(file, map);
        esUtils.save(form);
    }

}
