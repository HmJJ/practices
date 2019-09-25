package com.wayonsys.docsearch.elasticsearch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/api/elastic")
@Controller
public class ElasticController {

    Logger log = LoggerFactory.getLogger(ElasticController.class);

    @Autowired
    ElasticService elasticService;

    @RequestMapping("saveDoc")
    @ResponseBody
    public String saveDoc() {

        elasticService.saveDoc();

        return "OK";

    }


    @RequestMapping("searchDoc")
    @ResponseBody
    public String searchDoc() {
        try {
            elasticService.searchDoc();
            ;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";

    }




}
