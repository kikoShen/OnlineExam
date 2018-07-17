package com.example.demo.mapper;

import com.example.demo.dto.FillInBlank;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FillInBlankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FillInBlank record);

    int insertSelective(FillInBlank record);

    FillInBlank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FillInBlank record);

    int updateByPrimaryKey(FillInBlank record);
}