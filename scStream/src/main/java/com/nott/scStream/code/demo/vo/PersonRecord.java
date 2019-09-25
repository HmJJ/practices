package com.nott.scStream.code.demo.vo;

import com.nott.scStream.code.demo.domain.Person;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 16:49
 * @Modified By:
 **/
public class PersonRecord extends Person {

    protected List<InterestRecord> interests;

    public List<InterestRecord> getInterests() {
        return interests;
    }

    public void setInterests(List<InterestRecord> interests) {
        this.interests = interests;
    }
}
