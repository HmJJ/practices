package com.wayonsys.ecm.docsearch.elasticsearch.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wayonsys.common.entity.DomainEntity;
import com.wayonsys.ecm.docsearch.elasticsearch.vo.ElasticForm;
import com.wayonsys.ecm.docsearch.elasticsearch.vo.ElasticQuery;
import com.wayonsys.ecm.docsearch.elasticsearch.vo.ElasticRecord;
import com.wayonsys.ecm.utils.FileUtils;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/25 17:25
 * @Modified By:
 **/
@Service
public class ElasticService {

    private static final Logger log = LoggerFactory.getLogger(ElasticService.class);

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    protected static final String DEFAULT_INDEX_NAME = "docindex";

    private IndexRequest indexRequestGenerator(ElasticForm form) {
        IndexRequest indexRequest = null;
        /*String[] temArr = entity.getClass().getName().split("\\.");
        String name = temArr[temArr.length - 1].toLowerCase();*/
        Map<String, Object> params = form.getMap();
        String json = JSONObject.toJSONString(params);
        indexRequest = new IndexRequest()
                .index(DEFAULT_INDEX_NAME)
                .id(params.get("id") == null ? UUID.randomUUID().toString() : (String) params.get("id"))
                .source(json, XContentType.JSON)
                .opType(DocWriteRequest.OpType.CREATE);
        return indexRequest;
    }

    public boolean saveIndex(ElasticForm form) {
        IndexRequest indexRequest = indexRequestGenerator(form);
        boolean flag = false;
        try {
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            if (indexResponse != null) {
                flag = true;
            }
        } catch (Exception e) {
            log.info(e.getCause().getMessage());
        }
        return flag;
    }

    public List<ElasticRecord> searchIndex(ElasticQuery query) {
        log.info("ElasticSearch, searchIndex, ElasticQuery: {}", query.toString());
        String index = query.getIndex() + query.getTenantId();
        if (!existOfIndex(index)) {
            log.info("ElasticService, searchIndex, 该Index: {} 不存在!", index);
            return null;
        }
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        if (query.getIncludeFields() != null || query.getExcludeFields() != null) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.should(QueryBuilders.multiMatchQuery(query.getQuery(), query.getMatchFields()));

            // 配置高亮查询
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            for (String str : query.getMatchFields()) {
                highlightBuilder.field(str);
            }
            highlightBuilder.requireFieldMatch(false); // 如果要高亮多个字段，这项要为false
            highlightBuilder.preTags("<span style=\"color:red\">");
            highlightBuilder.postTags("</span>");
            // 如果要高亮很多内容的字段，则必须要以下两个配置，不然会导致高亮不全，文章内容确实等
            highlightBuilder.fragmentSize(800000);  // 最大高亮分片数
            highlightBuilder.numOfFragments(0); // 从第一个分片获取高亮片段
            searchSourceBuilder.highlighter(highlightBuilder);
            searchSourceBuilder.query(boolQueryBuilder);
        } else {
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        }

        // 分页设置
        searchSourceBuilder.trackScores(true);
        searchSourceBuilder.from((query.getPage() < 1 ? 1 : query.getPage() - 1) * (query.getSize() < 10 ? 10 : query.getSize()));
        searchSourceBuilder.size(query.getSize() < 10 ? 10 : query.getSize());

        /**
         * 不查整个文档内容
         */
        searchSourceBuilder.fetchSource(query.getIncludeFields(), query.getExcludeFields());

        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse == null) {
                log.info("ElasticService, searchIndex, 查询失败, searchResponse为空");
                return null;
            }
            log.info("ElasticService, searchIndex, SearchResponse: {}", searchResponse.toString());
            List<ElasticRecord> course = new ArrayList<>();
            SearchHits hits = searchResponse.getHits();
            log.info("search: success! total: {}", hits.getHits().length);
            for (SearchHit hit : hits) {
                Map<String, Object> source = hit.getSourceAsMap();
                JSONObject jsonObject = new JSONObject(source);
                ElasticRecord record = JSONObject.parseObject(jsonObject.toJSONString(), ElasticRecord.class);
                if (jsonObject.get("attachment") != null) {
                    JSONObject attachment = new JSONObject((HashMap)jsonObject.get("attachment"));
                    record.setContent(attachment.get("content") == null ? "" : (String) attachment.get("content"));
                }
                record = parseHighLightFields(hit.getHighlightFields(), query.getMatchFields(), record);
                course.add(record);
            }
            return course;
        } catch (IOException e) {
//            log.info(e.getCause().getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void deleteIndex(ElasticQuery query) {
        if (existOfId(query.getId())) {
            DeleteRequest deleteRequest = new DeleteRequest(DEFAULT_INDEX_NAME, query.getId());
            try {
                DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
                System.out.println(deleteResponse.getResult().getLowercase());
            } catch (IOException e) {
                log.info(e.getCause().getMessage());
            }
        } else {
            System.out.println("没有匹配的数据");
        }
    }

    public boolean existOfId(String id) {
        GetRequest getRequest = new GetRequest(DEFAULT_INDEX_NAME, id);
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = false;
        try {
            exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info(e.getCause().getMessage());
        }
        return exists;
    }

    public boolean existOfIndex(String index) {
        GetIndexRequest request = new GetIndexRequest(index);
        boolean exists = false;
        try {
            exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
            log.info("ElasticService, existOfIndex, exist: {}", exists);
        } catch (IOException e) {
            log.info(e.getCause().getMessage());
        }
        return exists;
    }

    /**
     * 保存base64格式的pdf、ppt、doc
     * @param file
     * @return
     */
    public boolean saveIndex(MultipartFile file, Map<String, Object> params) {
        if (file == null) {
            return false;
        }
        String base64Str = FileUtils.multipartFileToBase64(file);
        params.put("data", base64Str);
        String index = params.get("index") == null ? DEFAULT_INDEX_NAME + params.get("tenantId") : (String) params.get("index") + params.get("tenantId");
        log.info("当前es的index为：{}", index);
        IndexRequest indexRequest = new IndexRequest(index)
                .id(params.get("id") == null ? UUID.randomUUID().toString() : (String) params.get("id"))
                .setPipeline("attachment")
                .source(params);
        try {
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            return true;
        } catch (Exception e) {
//            log.info(e.getCause().getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public ElasticRecord parseHighLightFields(Map<String, HighlightField> map, String[] matchFields, ElasticRecord record) {
        JSONObject object = new JSONObject();

        for (int i = 0; i < matchFields.length; i++) {
            String field = matchFields[i];
            if (map.get(field) != null) {
                HighlightField highlightField = map.get(field);
                String name = highlightField.getName();
                String preText = highlightField.toString();
                String text = preText.substring(preText.indexOf("fragments[[") + 11, preText.length() - 2);
                String matchField = text.substring(text.indexOf(">") + 1, text.lastIndexOf("<"));
                if ("attachment.content".equals(name)) {
                    object.put("content", text);
                } else if ("name".equals(name)) {
                    object.put(name, text);
                    /*String fileName = record.getName();
                    record.setName(fileName.replaceAll(matchField, text));*/
                }
            }
        }
        record.setHighlight(object);

        return record;
    }

}
