package com.warframe.mytmall.dao;

import com.warframe.mytmall.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by warframe on 2017/6/2.
 */
@Repository
public interface UserDAO extends BaseDAO {
    @Override
    <T> void add(T t);

    @Override
    void delete(int id);

    @Override
    <T> void update(T t);

    @Override
    <T> T getById(int id);

    @Override
    <T> List<T> listAll();

    @Override
    <T> List<T> list(@Param("start") int start, @Param("count") int count);

    @Override
    int getTotalNumber();

    //    User getUserById(int id);
//
//    List<User> getUsers();
//
//    void addUser(User user);
//
//    void updateUser(User user);
//
//    void deleteUser(int id);
//
//    int getTotalNumber();


}
