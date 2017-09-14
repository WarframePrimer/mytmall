package com.warframe.mytmall.service;

import com.warframe.mytmall.pojo.User;

import java.util.List;

/**
 * Created by warframe on 2017/6/2.
 */
public interface UserService {

    User getUserById(int id);

    void addUser(User user);

    int getTotalNumber();

    List<User> getUsers();

    void deleteUser(int id);

    void updateUser(User user);

    List<User> list(int start, int count);

    boolean isExist(String name);

    boolean checkUser(String name,String password);

    User getByUserName(String userName);
}
