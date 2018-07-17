package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.*;
import com.example.demo.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperService {
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private SingleChoiceMapper singleChoiceMapper;
    @Autowired
    private FillInBlankMapper fillInBlankMapper;
    @Autowired
    private DiscussionMapper discussionMapper;
    @Autowired
    private DrawingMapper drawingMapper;
    @Autowired
    private QuestionBankService questionBankService;

    private static int SC = 2;
    private static int FILL = 2;
    private static int DIS = 1;
    private static int DRAW = 1;

    public List<Integer> ProduceRandomPaper(String job,String level){
        List<Integer> paperIdList =new ArrayList<Integer>();
        List<Integer> SingleChoice = questionBankService.SelectSingleChoice(job,level);
        /*
        for(int i=0;i<SingleChoice.size();++i){//输出
            System.out.print(SingleChoice.get(i)+" ");
        }
        System.out.println();
        */
        List<Integer> FillInBlank = questionBankService.SelectFillInBlank(job,level);
        List<Integer> Discussion = questionBankService.SelectDiscussion(job,level);
        List<Integer> Drawing =questionBankService.SelectDrawing(job,level);
        paperIdList.addAll(createRandomList(SingleChoice,SC));
        paperIdList.addAll(createRandomList(FillInBlank,FILL));
        paperIdList.addAll(createRandomList(Discussion,DIS));
        paperIdList.addAll(createRandomList(Drawing,DRAW));
        return paperIdList;
    }

    public JSONArray getQuestion(String[] array){
        JSONArray jq = new JSONArray();
        for (int i=0;i<SC;i++){
            SingleChoice singleChoice= singleChoiceMapper.selectByPrimaryKey(Integer.valueOf(array[i]));
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("xvhao",i+1);
            jsonObject.put("title",singleChoice.getQuestion());
            JSONArray xuanxiang =new JSONArray();
            xuanxiang.add(singleChoice.getContenta());
            xuanxiang.add(singleChoice.getContentb());
            xuanxiang.add(singleChoice.getContentc());
            jsonObject.put("xuanxiang",xuanxiang);
            jsonObject.put("answer",singleChoice.getAnswer());
            jq.add(jsonObject);
        }


        for (int i=SC;i<FILL+SC;i++){
            FillInBlank fillInBlank = fillInBlankMapper.selectByPrimaryKey(Integer.valueOf(array[i]));
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("xvhao",i+1);
            jsonObject.put("title",fillInBlank.getQuestion());
            JSONArray answer =new JSONArray();
            answer.add(fillInBlank.getAnswera());
            answer.add(fillInBlank.getAnswerb());
            jsonObject.put("answer",answer);
            jq.add(jsonObject);
        }


        for (int i=SC+FILL;i<SC+FILL+DIS;i++){
//            System.out.println(i);
            Discussion discussion = discussionMapper.selectByPrimaryKey(Integer.valueOf(array[i]));
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("xvhao",i+1);
            jsonObject.put("title",discussion.getQuestion());
            jsonObject.put("answer",discussion.getAnswer());
            jq.add(jsonObject);
        }

        for (int i=SC+FILL+DIS;i<SC+FILL+DIS+DRAW;i++){
            Drawing drawing = drawingMapper.selectByPrimaryKey(Integer.valueOf(array[i]));
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("xvhao",i+1);
            jsonObject.put("title",drawing.getQuestion());
            jsonObject.put("answer",drawing.getPath());
            jq.add(jsonObject);
        }

        return jq;
    }

    public List<Integer> selectByJob(String job){
        List<Paper> paperList ;
        List<Integer> idList =new ArrayList<Integer>();
        if (job!=null){
            paperList = paperMapper.selectByJob(job);
        }else{
            paperList =paperMapper.selectAll();
        }
        for(int i=0;i<paperList.size();i++) {
            idList.add(paperList.get(i).getIdpaper());
        }
        return idList;
    }



    private List createRandomList(List list, int n) {
        Map map = new HashMap();
        List listNew = new ArrayList();
        if(list.size()<=n){
            return list;
        }else{
            while(map.size()<n){
                int random = (int) (Math.random() * list.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");
                   // System.out.println(random+"==========="+list.get(random));
                    listNew.add(list.get(random));
                }
            }
            return listNew;
        }
    }



}
