package com.nott.scStream.code.demo.mapper;

import com.nott.scStream.code.demo.vo.PersonQuery;
import com.nott.scStream.code.demo.vo.PersonRecord;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 11:11
 * @Modified By:
 **/
public interface PersonMapper {

    Integer findPersonListCount(PersonQuery query);
    List<PersonRecord> findPersonList(PersonQuery query);

}
