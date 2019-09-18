package com.nott.scStream.code.demo.mapper;

import com.nott.scStream.code.demo.vo.InterestQuery;
import com.nott.scStream.code.demo.vo.InterestRecord;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 17:07
 * @Modified By:
 **/
@Service
public class InterestMapperService {

    private static final Logger log = LoggerFactory.getLogger(InterestMapperService.class);

    @Autowired
    private SqlSession sqlSession;

    public List<InterestRecord> search(InterestQuery query) {
        InterestMapper mapper = sqlSession.getMapper(InterestMapper.class);
        List<InterestRecord> interestRecords = mapper.findInterestList(query);
        return interestRecords;
    }

}
