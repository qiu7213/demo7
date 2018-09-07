package com.example.demo.mapper;

import com.example.demo.bean.MenuList;
import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface UserMapper {
    // IDEA 在class上加一个 @Service 其他地方调用，不会划红线警告


    public User getUser(Integer id);

    public List<User> findAll();

    @Select("select * from user where id < 6")
    public List<User> find5();


    public User loginUser();

/**
 * *****************      经销商平台         *****************************
 */



    /**
     *
     * @param state    用户施工状态
     * @return
     */
    public List<User> findByState(@Param("state") Integer state);


    public User exsisUser(@Param("userName") String userName,
                          @Param("password") String password);

    public boolean addUser(User user);

    public List<MenuList> userMneu(Integer id);




}
