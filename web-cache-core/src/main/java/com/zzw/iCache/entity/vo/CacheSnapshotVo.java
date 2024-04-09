package com.zzw.iCache.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class CacheSnapshotVo  {

    private String address;

    private String cacheKey;

    private String cacheValue;

    private String cacheOldValue;

    private String operation;

    private Date tmCreate;

//    public CacheSnapshotVo(String address, SnapshotEntity entity){
//        this.address = address;
//        this.cacheKey = entity.getCacheKey();
//        this.cacheOldValue = entity.getCacheOldValue();
//        this.cacheValue = entity.getCacheValue();
//        this.operation = entity.getOperation().name();
//        this.tmCreate = entity.getTmCreate();
//    }
}