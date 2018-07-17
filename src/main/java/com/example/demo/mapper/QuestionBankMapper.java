package com.example.demo.mapper;

import com.example.demo.dto.QuestionBank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionBankMapper {
    int deleteByPrimaryKey(Integer idquestionBank);

    int insert(QuestionBank record);

    int insertSelective(QuestionBank record);

    QuestionBank selectByPrimaryKey(Integer idquestionBank);

    int updateByPrimaryKeySelective(QuestionBank record);

    int updateByPrimaryKey(QuestionBank record);

    @Select("select idquestion_bank from question_bank where job =#{job} and level =#{level} and type = #{type}")
    List<Integer> selectByJobLevelType(@Param("job") String job, @Param("level") String level , @Param("type") String type);
}