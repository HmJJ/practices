package com.nott.scStream.code.demo.mapper;

import com.nott.scStream.code.demo.domain.Person;
import com.nott.scStream.code.demo.vo.InterestQuery;
import com.nott.scStream.code.demo.vo.InterestRecord;
import com.nott.scStream.code.demo.vo.PersonQuery;
import com.nott.scStream.code.demo.vo.PersonRecord;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 11:12
 * @Modified By:
 **/
@Service
public class PersonMapperService {

    private static Logger log = LoggerFactory.getLogger(PersonMapperService.class);

    @Autowired
    private SqlSession sqlSession;
    @Autowired
    private InterestMapperService interestMapperService;

    public List<PersonRecord> search(PersonQuery query) {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        List<PersonRecord> personRecords = mapper.findPersonList(query);
        for (int i = 0; i<personRecords.size(); i++) {
            PersonRecord record = personRecords.get(i);
            InterestQuery interestQuery = new InterestQuery();
            interestQuery.setPersonId(record.getId());
            List<InterestRecord> interestRecords = interestMapperService.search(interestQuery);
            record.setInterests(interestRecords);
            personRecords.set(i, record);
        }
        return personRecords;
    }

}
