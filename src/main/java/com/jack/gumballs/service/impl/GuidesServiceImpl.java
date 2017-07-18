package com.jack.gumballs.service.impl;

import com.jack.gumballs.base.mapper.BaseMapper;
import com.jack.gumballs.base.service.impl.BaseServiceImpl;
import com.jack.gumballs.domain.model.Guides;
import com.jack.gumballs.domain.model.GuidesExample;
import com.jack.gumballs.mapper.GuidesMapper;
import com.jack.gumballs.service.GuidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LiJiakui on 17/6/13.
 */
@Service
public class GuidesServiceImpl extends BaseServiceImpl<Guides,GuidesExample> implements GuidesService{

    @Autowired
    private GuidesMapper guidesMapper;

    @Override
    public BaseMapper<Guides, GuidesExample> getMapper() {
        return guidesMapper;
    }
}
