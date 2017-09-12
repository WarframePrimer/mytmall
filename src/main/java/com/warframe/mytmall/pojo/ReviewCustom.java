package com.warframe.mytmall.pojo;

import java.util.Date;

/**
 * @Author warframe[github.com/WarframePrimer]
 * @Date 2017/9/11 15:09
 */
public class ReviewCustom {
    private int id;
    private String content;
    private Date createDate;
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
