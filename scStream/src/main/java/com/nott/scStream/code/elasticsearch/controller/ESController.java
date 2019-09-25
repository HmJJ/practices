package com.nott.scStream.code.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.nott.scStream.code.demo.domain.Person;
import com.nott.scStream.code.elasticsearch.service.ESService;
import com.nott.scStream.code.elasticsearch.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 10:43
 * @Modified By:
 **/
@RequestMapping("es")
@RestController
public class ESController {

    @Autowired
    private ESService esService;

    @RequestMapping(value = "putData", method = RequestMethod.POST)
    public void putData(@RequestBody Person person) {
        esService.singleSave(person);
    }

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public String query(@RequestBody QueryVo vo) {
        StringBuffer searchStr = new StringBuffer();
        searchStr.append("{\n");
        searchStr.append("  \"query\": {\n");
        searchStr.append("      \"match_phrase\": {\n");
        searchStr.append("          \"remark\": ");
        searchStr.append("\"" + vo.getQuery() + "\"\n");
        searchStr.append("      }\n");
        searchStr.append("  },\n");
        searchStr.append("  \"highlight\": {\n");
        searchStr.append("      \"fields\": {\n");
        searchStr.append("          \"remark\": {}\n");
        searchStr.append("      }\n");
        searchStr.append("  }\n");
        searchStr.append("}");
        JSONObject jsonObject = JSONObject.parseObject(searchStr.toString());
        return esService.baseQuery(jsonObject.toJSONString(), "nott", "Person");
    }

}
