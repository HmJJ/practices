package com.wayonsys.docsearch.elasticsearch;


import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ElasticService {

    Logger log = LoggerFactory.getLogger(ElasticService.class);


    @Autowired
    RestHighLevelClient restHighLevelClient;

    String indexName = "productindex";

    public void saveDoc() {


        IndexRequest request = new IndexRequest();
        ProductDoc doc = new ProductDoc();

        doc.setSn(UUID.randomUUID().toString());
        doc.setName("雨刷");

        doc.setDescription("test product" + new Date());
        doc.setDocCategory("YUSHUA");
        doc.setDocType("PRODUCT");

        String source = JSON.toJSONString(doc);

        request.index(indexName).id(UUID.randomUUID().toString());
        request.source(JSON.toJSONString(doc), XContentType.JSON);


        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void searchDoc() throws Exception {
//        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(100L));
        SearchRequest searchRequest = new SearchRequest("productindex");
//        searchRequest.scroll(scroll);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);


        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        log.info("search result size:" + searchResponse.getHits().getTotalHits().value);



    }



    public void saveContent() {

    }





    public void searchContent() {

    }



}
