package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.MenuList;
import com.example.demo.bean.User;

import com.example.demo.config.RedisComponent;
import com.example.demo.constant.*;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    RedisComponent redisComponent;

    @Autowired
    ResponeMessage responeMessage;

    @Autowired
    MenuList menuList;

    @PostMapping("/loginUser")
    @ResponseBody
    public ResponeMessage login(@RequestParam("username") String userName,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpServletResponse response){
        String new_pas = degist(password);
        User user = userService.exsisUser(userName,new_pas);

        if(null == user){
            responeMessage.setCode(Constant.LOGIN_FAIL1);
            responeMessage.setInfo(Constant.LOGIN_FAIL1_INFO);
            return responeMessage;
        }
        String token = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        user.setToken(token);
        System.out.println("token : "+token);
        redisComponent.setLogin(token, JSON.toJSONString(user),Constant.SESSION_TIME_OUT, TimeUnit.MINUTES);
        responeMessage.setCode(Constant.LOGIN_SUCCESS);responeMessage.setObject(user);responeMessage.setRoleEnum(RoleEnum.role03);
        responeMessage.setInfo(Constant.LOGIN_SUCCESS_INFO);
        response.addCookie(new Cookie("logininfo",user.getId().toString()));
        return responeMessage;
    }

    @GetMapping("/getuser/{id}")
    @ResponseBody
    public User getUserByid(@PathVariable("id") Integer id){
        System.out.println("查询。。用户："+id);
        return userService.getUser(id);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<User> findAll(){
        System.out.println("查询所有用户：");
        return userService.findall();
    }

    @GetMapping("/find5")
    @ResponseBody
    public List<User> find5()
    { System.out.println("查询前 5 用户：");
        return userMapper.find5();
    };



    /**
     * 把加密后的 数组 转化为 String
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }

    private String degist(String password){
        MessageDigest md = null;
        String new_pas = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digesta  = md.digest(password.toString().getBytes());
            new_pas = byte2hex(digesta).toUpperCase();
            System.out.println("SHA1  ---------------------"+new_pas);
            MD5 md5=new MD5();
            new_pas = md5.getMD5ofStr(new_pas);
            System.out.println("md5  ---------------------"+new_pas);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
            return new_pas;
    }

    /**
     *************************    经销商平台   ******************************
     */
    @GetMapping("/findByState/{state}")
    @ResponseBody
    public List<User> findByState(@PathVariable("state") Integer state, HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String time1 = sdf.format(new Date());
        System.out.println("[controller]  star  findByState  : "+state + "       -s  " + time1);
        List<User>  list = userService.findByState(state);
        String time2 = sdf.format(new Date());
        System.out.println("[controller]  end  findByState  : "+state + "       -e  " + time2);
        return list;
    };


    @RequestMapping("/addUser/")
    @ResponseBody
    public boolean addUser(User user ,HttpServletRequest request){
        user.setPassword(degist(user.getPassword()));
        user.setCreateTime(CommonUtil.getNowTime());
        String token = user.getToken();
        String data = redisComponent.getLogin(token);
        JSONObject jso=JSON.parseObject(data);//json字符串转换成jsonobject对象
        user.setCreateID(jso.getInteger("id"));

        return userService.addUser(user);

    }

    @RequestMapping("/userMenu/{id}")
    @ResponseBody
    public List<MenuList> userMenu(@PathVariable("id") Integer id ){
        return userService.userMenu(id);

    }

}
