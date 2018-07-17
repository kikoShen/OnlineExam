package com.example.demo.service;

import com.example.demo.dto.Teacher;
import com.example.demo.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;


    public Teacher getTeacher(Integer id){
        return teacherMapper.selectByPrimaryKey(id);
    }


    public boolean checkTeacher(Integer id,String Password) {
        Teacher teacher = getTeacher(id);
        Integer IntegerId = teacher.getIdteacher();
        int IntId =  IntegerId.intValue();
        String realId =Integer.toString(IntId);
        //System.out.println(realId);
        if (realId != null) {
            String realPassword = teacher.getPassword();
            //System.out.println(realPassword);
            if (realPassword.equals(Password))
                return true;
            return false;
        }
        return false;
    }
}
