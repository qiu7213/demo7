package com.example.demo.constant;

public enum StateEnum {
    STATE01("100","新用户"),
    STATE02("200","设计"),
    STATE03("300","施工中"),
    STATE04("400","验收"),
    STATE05("500","已完成");//记住要用分号结束

    private String code;
    private String msg;

    StateEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }





}
