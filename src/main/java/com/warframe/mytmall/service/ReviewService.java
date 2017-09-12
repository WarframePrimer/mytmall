package com.warframe.mytmall.service;

import com.warframe.mytmall.pojo.Review;
import com.warframe.mytmall.pojo.ReviewCustom;

import java.util.List;

/**
 * Created by warframe on 2017/6/3.
 */
public interface ReviewService {
    Review getReviewById(int id);

    void addReview(Review review);

    int getTotalNumber();

    List<Review> listAll();

    void deleteReview(int id);
    //void updateUser(User user);

    List<Review> list(int start, int count);


    int getReviewCountByProductId(int pid);

    List<ReviewCustom> getReviewCustomsByProductId(int pid);

}


