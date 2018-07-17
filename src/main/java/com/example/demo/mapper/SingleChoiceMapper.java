package com.example.demo.mapper;

import com.example.demo.dto.SingleChoice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SingleChoiceMapper {
    int insert(SingleChoice record);

    int insertSelective(SingleChoice record);

    @Select("select * from single_choice where idsingle_choice =#{idsingleChoice}")
    SingleChoice selectByPrimaryKey(Integer idsingleChoice);
}