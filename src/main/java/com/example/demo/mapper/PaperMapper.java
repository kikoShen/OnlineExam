package com.example.demo.mapper;

import com.example.demo.dto.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PaperMapper {
    int deleteByPrimaryKey(Integer idpaper);

    int insert(Paper record);

    int insertSelective(Paper record);

    Paper selectByPrimaryKey(Integer idpaper);

    int updateByPrimaryKeySelective(Paper record);

    int updateByPrimaryKey(Paper record);

    @Select("select * from paper where job = #{job}")
    List<Paper> selectByJob(String job);

    @Select("select * from paper")
    List<Paper> selectAll();

    @Select("select qarray from paper where idpaper =#{idpaper}")
    String selectQuestionIdArray(Integer idpaper);

    @Select("select idpaper from paper where idteacher =#{idteacher}")
    List<Integer> selectIdByTeacherId(Integer idteacher);
}