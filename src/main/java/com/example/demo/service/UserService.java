package com.example.demo.service;
import com.example.demo.dto.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private  UserMapper userMapper;
    private static final int FIND_FAILED= -1;

    public User getUser(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    public boolean checkUser(String Email,String Password) {
        Integer id = SelectIdFromEmail(Email);
        User user = getUser(id);
        Integer IntegerId = user.getIduser();
        int IntId =  IntegerId.intValue();
        String realId =Integer.toString(IntId);
        //System.out.println(realId);
        String realPassword = user.getPassword();
        //System.out.println(realPassword);
        if (realPassword.equals(Password)){
            return true;
        }
        return false;
    }

    public Integer SelectIdFromEmail(String Email){
        Integer id =userMapper.selectUserByEmail(Email).getIduser();
        if (id==null)
            return FIND_FAILED;
        return id;
    }

    public boolean UserExist(String Email){
       if(userMapper.selectUserByEmail(Email)!=null){
           //System.out.println("用户已存在！");
           return  false;
       }
       return true;
    }

    public boolean Register(String Email,String Password){
        if(UserExist(Email)==true){
            User user = new User();
            user.setEmail(Email);
            user.setPassword(Password);
            userMapper.insertSelective(user);
            return true;
        }
        return false;
    }


}
