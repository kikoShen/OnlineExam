package com.example.demo.mapper;

import com.example.demo.dto.Drawing;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DrawingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Drawing record);

    int insertSelective(Drawing record);

    Drawing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Drawing record);

    int updateByPrimaryKey(Drawing record);
}