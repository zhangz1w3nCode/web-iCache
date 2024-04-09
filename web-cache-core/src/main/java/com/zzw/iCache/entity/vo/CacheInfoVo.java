package com.zzw.iCache.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CacheInfoVo {

    private String ip;

    private long count;

    private Set<String> refreshNames;
}