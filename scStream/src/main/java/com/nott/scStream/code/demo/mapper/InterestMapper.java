package com.nott.scStream.code.demo.mapper;

import com.nott.scStream.code.demo.vo.InterestQuery;
import com.nott.scStream.code.demo.vo.InterestRecord;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 17:07
 * @Modified By:
 **/
public interface InterestMapper {

    Integer findInterestListCount(InterestQuery query);
    List<InterestRecord> findInterestList(InterestQuery query);

}
