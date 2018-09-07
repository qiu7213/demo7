package com.example.demo.constant;

public enum RoleEnum {

    //超级管理员
    role01(10),

    //管理员
    role02(20),

    //经销商
    role03(30),

    //经销商下的员工
    role04(40),

    //普通用户
    role05(50);

   private Integer code;

   RoleEnum(Integer code){
    this.code = code;
   }


}
