package com.example.demo.service;

import com.example.demo.mapper.QuestionBankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionBankService {

    @Autowired
    private QuestionBankMapper questionBankMapper;

    public List<Integer> SelectSingleChoice(String job,String level){
        return questionBankMapper.selectByJobLevelType(job,TransferLevel(level),"选择");
    }

    public List<Integer> SelectFillInBlank(String job,String level){
        return questionBankMapper.selectByJobLevelType(job,TransferLevel(level),"填空");
    }

    public List<Integer> SelectDiscussion(String job,String level){
        return questionBankMapper.selectByJobLevelType(job,TransferLevel(level),"简答");
    }

    public List<Integer> SelectDrawing(String job,String level){
        return questionBankMapper.selectByJobLevelType(job,TransferLevel(level),"作图");
    }

    public  static String TransferLevel(String level){
        switch (level){
            case "A级":
                level = "难";
                break;
            case "B级":
                level = "中";
                break;
            case "C级":
                level = "易";
                break;
            default:
                break;
        }
        return level;
    }

}
