package com.athena.px.springbootdemo.mapper;

import com.athena.px.springbootdemo.model.Gg;

public interface GgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gg record);

    int insertSelective(Gg record);

    Gg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gg record);

    int updateByPrimaryKey(Gg record);
}