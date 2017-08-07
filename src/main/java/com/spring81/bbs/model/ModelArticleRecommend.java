package com.spring81.bbs.model;

import java.util.Date;

public class ModelArticleRecommend {
    
    private String  userid    = "";  // VARCHAR(50) NOT NULL
    private Integer articleno = null;// INTEGER NOT NULL
    private Boolean good      = null;// TINYINT(1) NULL DEFAULT 0
    private Boolean bad       = null;// TINYINT(1) NULL DEFAULT 0
    
    public String getUserid() {
        return userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public Integer getArticleno() {
        return articleno;
    }
    
    public void setArticleno(Integer articleno) {
        this.articleno = articleno;
    }
    
    public Boolean getGood() {
        return good;
    }
    
    public void setGood(Boolean good) {
        this.good = good;
    }
    
    public Boolean getBad() {
        return bad;
    }
    
    public void setBad(Boolean bad) {
        this.bad = bad;
    }
    
    @Override
    public String toString() {
        return "ModelArticleRecommend [userid=" + userid + ", articleno="
                + articleno + ", good=" + good + ", bad=" + bad + "]";
    }
    
    public ModelArticleRecommend(String userid, Integer articleno, Boolean good,
            Boolean bad) {
        super();
        this.userid = userid;
        this.articleno = articleno;
        this.good = good;
        this.bad = bad;
    }
    
    public ModelArticleRecommend() {
        super();
    }
    
}
