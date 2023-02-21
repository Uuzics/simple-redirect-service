/*
 * Copyright 2023 Uuzics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.uuzics.simpleredirectservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.uuzics.simpleredirectservice.entity.Redirection;

import java.util.List;

/**
 * Database mapper for Redirection
 */
@Mapper
@Repository
public interface RedirectionMapper {
    /**
     * Get Redirection by resource ID
     *
     * @param resourceId resource ID
     * @return List of Redirection(s) with given resource ID
     */
    @Select("SELECT id,resource_id,redirect_url,redirect_type FROM redirection WHERE resource_id = #{resourceId}")
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "resourceId", column = "resource_id", javaType = String.class),
            @Result(property = "redirectUrl", column = "redirect_url", javaType = String.class),
            @Result(property = "redirectType", column = "redirect_type", javaType = String.class)
    })
    List<Redirection> queryByResourceId(String resourceId);
}
