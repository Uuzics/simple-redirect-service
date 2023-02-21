package org.uuzics.simpleredirectservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.uuzics.simpleredirectservice.entity.Redirection;

import java.util.List;

@Mapper
@Repository
public interface RedirectionMapper {
    @Select("SELECT id,resource_id,redirect_url,redirect_type FROM redirection WHERE resource_id = #{resourceId}")
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "resourceId", column = "resource_id", javaType = String.class),
            @Result(property = "redirectUrl", column = "redirect_url", javaType = String.class),
            @Result(property = "redirectType", column = "redirect_type", javaType = String.class)
    })
    List<Redirection> queryByResourceId(String resourceId);
}
