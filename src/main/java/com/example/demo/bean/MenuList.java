package com.example.demo.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class MenuList extends BaseBean implements Serializable{

    private static final long serialVersionUID = 4714490043847475908L;

    private Integer id;
    private Integer menuid;
    private String name;
    private Integer parentid;
    private String type;
    private String icon;
    private String jsAction;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public String getJsAction() {
        return jsAction;
    }

    public void setJsAction(String jsAction) {
        this.jsAction = jsAction;
    }
}
