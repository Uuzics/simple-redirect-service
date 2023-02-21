package org.uuzics.simpleredirectservice.service;

import org.uuzics.simpleredirectservice.entity.Redirection;

import java.util.List;

public interface RedirectionService {
    List<Redirection> queryByResourceId(String resourceId);
}
