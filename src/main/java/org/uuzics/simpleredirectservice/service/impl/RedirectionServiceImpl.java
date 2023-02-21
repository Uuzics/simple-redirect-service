package org.uuzics.simpleredirectservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uuzics.simpleredirectservice.mapper.RedirectionMapper;
import org.uuzics.simpleredirectservice.entity.Redirection;
import org.uuzics.simpleredirectservice.service.RedirectionService;

import java.util.List;

@Service("redirectionService")
public class RedirectionServiceImpl implements RedirectionService {

    @Autowired
    private RedirectionMapper redirectionMapper;

    @Override
    public List<Redirection> queryByResourceId(String resourceId) {
        return this.redirectionMapper.queryByResourceId(resourceId);
    }
}
