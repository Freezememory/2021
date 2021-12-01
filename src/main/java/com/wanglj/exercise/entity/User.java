package com.wanglj.exercise.entity;

/**
 * @Author: Wanglj
 * @Date: 2021/12/1 15:18
 * @Description :
 */
public class User {

    private Integer id;

    private String username;

    private Integer age;

    private String sex;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public User(Integer id, String username, Integer age, String sex) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.sex = sex;

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
