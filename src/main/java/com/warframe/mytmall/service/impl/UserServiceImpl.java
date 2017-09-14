package com.warframe.mytmall.service.impl;

import com.warframe.mytmall.dao.UserDAO;
import com.warframe.mytmall.pojo.User;
import com.warframe.mytmall.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by warframe on 2017/6/2.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Resource
    private UserDAO userDAO;


    @Override
    public User getUserById(int id) {
        return userDAO.getById(id);
    }

    @Override
    public void addUser(User user) {
        userDAO.add(user);
    }

    @Override
    public int getTotalNumber() {
       return userDAO.getTotalNumber();
    }

    @Override
    public List<User> getUsers() {
        return userDAO.listAll();
    }

    @Override
    public void deleteUser(int id) {
        userDAO.delete(id);
    }

    @Override
    public void updateUser(User user) {
        userDAO.update(user);
    }

    @Override
    public List<User> list(int start, int count) {
        return userDAO.list(start, count);
    }

    @Override
    public boolean isExist(String name) {
        int count = userDAO.isExist(name);
        //如果返回结果大于等于1，表示数据库中已经存在该用户了，返回true
        return  count>=1?true:false;
    }

    @Override
    public boolean checkUser(String name, String password) {
        int count = userDAO.checkPassword(name, password);
        return count==1?true:false;
    }

    @Override
    public User getByUserName(String userName) {
        return userDAO.getByUserName(userName);
    }
}
