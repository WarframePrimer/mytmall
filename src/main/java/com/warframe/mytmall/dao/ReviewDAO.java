package com.warframe.mytmall.dao;

import com.warframe.mytmall.pojo.Review;
import com.warframe.mytmall.pojo.ReviewCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
@Repository
public interface ReviewDAO extends BaseDAO {
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

    List<ReviewCustom> getReviewCustomsByProductId(int pid);

    int getReviewCountByProductId(int pid);


}
