package com.example.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = 10125567610925057L;

    /**
     * ID
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 登录名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 当前登录的ID
     */
    private String appId;
    /**
     * 该用户所拥有的appIds
     */
    private List<String> appIdsOwn;

    public User(Integer id, String name, String username, String password, String appId) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.appId = appId;
    }

    public User(Integer id, String name, String username, String password, List<String> appIdsOwn) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.appIdsOwn = appIdsOwn;
    }

    public User(Integer id, String name, String username, String password, String appId, List<String> appIdsOwn) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.appId = appId;
        this.appIdsOwn = appIdsOwn;
    }

    public User() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<String> getAppIdsOwn() {
        return appIdsOwn;
    }

    public void setAppIdsOwn(List<String> appIdsOwn) {
        this.appIdsOwn = appIdsOwn;
    }
}
