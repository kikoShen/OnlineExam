package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.Paper;
import com.example.demo.mapper.PaperMapper;
import com.example.demo.service.PaperService;

import com.example.demo.service.QuestionBankService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController

public class PaperController {
    @Autowired
    private PaperService paperService;
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private QuestionBankService questionBankService;
    @ModelAttribute
    public Paper getPaper(){
        return new Paper();
    }

    @RequestMapping("/addPaper")
    public JSONObject AddPaper(@RequestBody Paper paper, HttpServletRequest request, HttpServletResponse response){
        Boolean bool= false;
        System.out.println(paper.getJob());
        paper.setLevel(questionBankService.TransferLevel(paper.getLevel()));
        List<Integer> paperId= paperService.ProduceRandomPaper(paper.getJob(),paper.getLevel());
        JSONArray jsonPaperId = (JSONArray) JSONArray.toJSON(paperId);
        System.out.println(jsonPaperId);
        String jsonString = JSONArray.toJSONString(jsonPaperId);
        paper.setEndtime(getEndTime(paper.getStarttime()));
        paper.setQarray(jsonString);
        if(paperMapper.insertSelective(paper)==1){
            bool= true;
        }
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("result",bool);
        return jsonObject;
    }

    @RequestMapping("/selectByJob")
    public  JSONArray selectByjob(@RequestBody Paper paper, HttpServletRequest request, HttpServletResponse response){
        String job =paper.getJob();
        List<Integer> idList =paperService.selectByJob(job);
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<idList.size();i++){
            Paper paper1 = paperMapper.selectByPrimaryKey(idList.get(i));
            JSONObject jo =(JSONObject) JSONObject.toJSON(paper1);
            jsonArray.add(jo);
        }
        return  jsonArray;
    }

    @RequestMapping("/deletePaper")
    public JSONObject deletePaper(@RequestBody Paper paper, HttpServletRequest request, HttpServletResponse response){
        Boolean bool= false;
        if(paperMapper.deleteByPrimaryKey(paper.getIdpaper())==1){
            bool= true;
        }
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("result",bool);
        return jsonObject;
    }

    @RequestMapping("/startAnswerPaper")
    public JSONArray startAnswerPaper(@RequestBody Paper paper, HttpServletRequest request, HttpServletResponse response){
        JSONArray jsonArray ;
        String question_array = paperMapper.selectQuestionIdArray(paper.getIdpaper());
        String sub = StringUtils.deleteWhitespace(question_array);
        sub = sub.substring(1,sub.length()-1);
        //System.out.println(sub);
        String[] array =sub.split(",");
        jsonArray = paperService.getQuestion(array);
        return jsonArray;
    }


    @RequestMapping("/selectOne")
    public  JSONArray selectOne(){
        JSONArray jsonArray = new JSONArray();
        Paper paper = paperMapper.selectByPrimaryKey(1);
        JSONObject jo = new JSONObject();
        jo.put("job", paper.getJob());
        jo.put("level", paper.getLevel());
        //String startTime =paper.getStartTime();
        String startTime =paper.getStarttime();
        jo.put("startTime", startTime);
        //String endTime =paper.getEndTime();
        String endTime =paper.getEndtime();
        jo.put("endTime",endTime);
        jo.put("questionIdArray",paper.getQarray());
        jsonArray.add(jo);
        return jsonArray;
    }


    public String getEndTime(String startTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date formatDate = null;
        try {
            formatDate = sdf.parse(startTime);
        }catch (ParseException e){
            e.printStackTrace();
        }
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(formatDate);
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        Date endTime = calendar.getTime();
        //System.out.println(calendar.getTime());
        String s =null;
        s = sdf.format(endTime);
        return s;
    }


    public static String DateToString(Date date){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        String dateString = null;
        try {
            Date newDate = sdf2.parse(date.toString());
            dateString = sdf1.format(newDate);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return dateString;
    }



}
