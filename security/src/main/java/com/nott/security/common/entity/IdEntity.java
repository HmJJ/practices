package com.nott.security.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 10:20
 * @Modified By:
 **/
@MappedSuperclass
public abstract class IdEntity implements Serializable {

    private static final long serialVersionUID = -7360423188247154116L;

    protected Long id;

    public IdEntity() {}

    public static List<String> asStringList(List<? extends IdEntity> list) {
        List<String> results = new ArrayList<>();
        Iterator var2 = list.iterator();

        while (var2.hasNext()) {
            IdEntity o = (IdEntity) var2.next();
            results.add(o.getId().toString());
        }

        return results;
    }

    public static List<Long> asList(List<? extends IdEntity> list) {
        List<Long> results = new ArrayList<>();
        Iterator var2 = list.iterator();

        while (var2.hasNext()) {
            IdEntity o = (IdEntity) var2.next();
            results.add(o.getId());
        }

        return results;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    @Transient
    public Boolean isNew() {
        return this.id == null;
    }

    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            IdEntity idEntity = (IdEntity) o;
            return idEntity.id != null && this.id != null ? Objects.equals(this.id, idEntity.id) : false;
        } else {
            return false;
        }
    }
}
