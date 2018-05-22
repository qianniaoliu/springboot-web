package com.athena.px.mapper;


import com.athena.px.model.Gg;

public interface GgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gg record);

    int insertSelective(Gg record);

    Gg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gg record);

    int updateByPrimaryKey(Gg record);
}