package com.warframe.mytmall.dao;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
@Repository
public interface PropertyValueDAO extends BaseDAO {

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
    <T> List<T> list(int start, int count);

    @Override
    int getTotalNumber();
}
