package com.example.demo.mapper;

import com.example.demo.dto.Discussion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DiscussionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Discussion record);

    int insertSelective(Discussion record);

    Discussion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Discussion record);

    int updateByPrimaryKey(Discussion record);
}