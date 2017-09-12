package com.warframe.mytmall.service.impl;

import com.warframe.mytmall.dao.ReviewDAO;
import com.warframe.mytmall.pojo.Review;
import com.warframe.mytmall.pojo.ReviewCustom;
import com.warframe.mytmall.service.ReviewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    @Resource
    private ReviewDAO reviewDAO;


    @Override
    public Review getReviewById(int id) {
        return reviewDAO.getById(id);
    }

    @Override
    public void addReview(Review review) {
        reviewDAO.add(review);
    }

    @Override
    public int getTotalNumber() {
        return reviewDAO.getTotalNumber();
    }

    @Override
    public List<Review> listAll() {
        return reviewDAO.listAll();
    }

    @Override
    public void deleteReview(int id) {
        reviewDAO.delete(id);
    }

    @Override
    public List<Review> list(int start, int count) {
        return reviewDAO.list(start, count);
    }



    @Override
    public int getReviewCountByProductId(int pid) {
        return reviewDAO.getReviewCountByProductId(pid);
    }

    @Override
    public List<ReviewCustom> getReviewCustomsByProductId(int pid) {
        return reviewDAO.getReviewCustomsByProductId(pid);
    }


}
