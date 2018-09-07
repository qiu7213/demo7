package com.example.demo.service;

import com.example.demo.bean.MenuList;
import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    public User getUser(Integer id){
        return userMapper.getUser(id);
    }

    public List<User> findall(){
        return userMapper.findAll();
    }

    public List<User> find5(){
        return userMapper.find5();
    };


    @Cacheable(value="findbystate")
    public List<User> findByState( Integer state) {
        System.out.println("-- service --   findByStte() : "+state);
        return userMapper.findByState(state);
};

    /**
     * 用户登录
     * @param userName
     * @return
     */
//    @Cacheable(value="exsisUser",key="'redis_user'+#userName+'/'+#password")
    public User exsisUser(String userName,String password){
        return userMapper.exsisUser(userName,password);
    }


    public boolean addUser(User user){
        return userMapper.addUser(user);
    }

    public List<MenuList> userMenu(Integer id){
       return  userMapper.userMneu(id);
    }
}
