package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.Paper;
import com.example.demo.dto.Teacher;
import com.example.demo.mapper.PaperMapper;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PaperMapper paperMapper;

    @ModelAttribute
    public Teacher getTeacher(){
        return new Teacher();
    }

    @RequestMapping("/login")
    public JSONObject Login(@RequestBody Teacher teacher, HttpServletRequest request, HttpServletResponse response){
        Integer teacherId =teacher.getIdteacher();
        boolean bool = teacherService.checkTeacher(teacherId,teacher.getPassword());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",bool);
        JSONArray jsonArray =new JSONArray();
        if (bool){
            List<Integer> paperIdList = paperMapper.selectIdByTeacherId(teacherId);
            if (paperIdList.size()==0){
                jsonObject.put("paper",null);
            }else {
                for (int i = 0; i < paperIdList.size(); i++) {
                    Paper singlePaper = paperMapper.selectByPrimaryKey(paperIdList.get(i));
                    JSONObject jsonPaper = (JSONObject) JSONObject.toJSON(singlePaper);
                    jsonArray.add(jsonPaper);
                }
                jsonObject.put("paper", jsonArray);
            }
        }else{
            jsonObject.put("paper",null);
        }

        return jsonObject;
    }



}
