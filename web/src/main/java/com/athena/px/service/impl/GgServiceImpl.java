package com.athena.px.service.impl;

import com.athena.px.mapper.GgMapper;
import com.athena.px.model.Gg;
import com.athena.px.service.GgService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/5/22 11:05
 */
@Service
public class GgServiceImpl implements GgService {

    final GgMapper ggMapper;

    public GgServiceImpl(GgMapper ggMapper) {
        this.ggMapper = ggMapper;
    }

    @Override
    public Gg findGg(Integer id) {
        return ggMapper.selectByPrimaryKey(id);
    }
}
