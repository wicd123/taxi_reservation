package com.spring81.bbs.model;

import java.util.Date;

public class ModelArticle {
    
    private Integer articleno = null; // INT(10) UNSIGNED NOT NULL AUTO_INCREMENT
    private String  boardcd   = "";   // VARCHAR(20) NULL DEFAULT NULL
    private String  title     = "";   // VARCHAR(200) NOT NULL
    private String  content   = "";   // MEDIUMTEXT NULL
    private String  email     = "";   // VARCHAR(60) NULL DEFAULT NULL
    private Integer hit       = null; // INT(10) UNSIGNED NULL DEFAULT '0'
    private Date    regdate   = null; // DATETIME NULL DEFAULT NULL
    private Boolean UseYN     = null; // TINYINT(1) NOT NULL DEFAULT '1'
    private String  InsertUID = "";   // VARCHAR(40) NULL DEFAULT NULL
    private Date    InsertDT  = null; // DATETIME NULL DEFAULT NULL
    private String  UpdateUID = "";   // VARCHAR(40) NULL DEFAULT NULL
    private Date    UpdateDT  = null; // DATETIME NULL DEFAULT NULL
    private Integer countgood = null;
    private Integer countbad = null;
    
    // 추가 컬럼
    private Integer attachfileNum = null;
    private Integer commentNum    = null;
    public Integer getArticleno() {
        return articleno;
    }
    public void setArticleno(Integer articleno) {
        this.articleno = articleno;
    }
    public String getBoardcd() {
        return boardcd;
    }
    public void setBoardcd(String boardcd) {
        this.boardcd = boardcd;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getHit() {
        return hit;
    }
    public void setHit(Integer hit) {
        this.hit = hit;
    }
    public Date getRegdate() {
        return regdate;
    }
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
    public Boolean getUseYN() {
        return UseYN;
    }
    public void setUseYN(Boolean useYN) {
        UseYN = useYN;
    }
    public String getInsertUID() {
        return InsertUID;
    }
    public void setInsertUID(String insertUID) {
        InsertUID = insertUID;
    }
    public Date getInsertDT() {
        return InsertDT;
    }
    public void setInsertDT(Date insertDT) {
        InsertDT = insertDT;
    }
    public String getUpdateUID() {
        return UpdateUID;
    }
    public void setUpdateUID(String updateUID) {
        UpdateUID = updateUID;
    }
    public Date getUpdateDT() {
        return UpdateDT;
    }
    public void setUpdateDT(Date updateDT) {
        UpdateDT = updateDT;
    }
    public Integer getCountgood() {
        return countgood;
    }
    public void setCountgood(Integer countgood) {
        this.countgood = countgood;
    }
    public Integer getCountbad() {
        return countbad;
    }
    public void setCountbad(Integer countbad) {
        this.countbad = countbad;
    }
    public Integer getAttachfileNum() {
        return attachfileNum;
    }
    public void setAttachfileNum(Integer attachfileNum) {
        this.attachfileNum = attachfileNum;
    }
    public Integer getCommentNum() {
        return commentNum;
    }
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }
    @Override
    public String toString() {
        return "ModelArticle [articleno=" + articleno + ", boardcd=" + boardcd
                + ", title=" + title + ", content=" + content + ", email="
                + email + ", hit=" + hit + ", regdate=" + regdate + ", UseYN="
                + UseYN + ", InsertUID=" + InsertUID + ", InsertDT=" + InsertDT
                + ", UpdateUID=" + UpdateUID + ", UpdateDT=" + UpdateDT
                + ", countgood=" + countgood + ", countbad=" + countbad
                + ", attachfileNum=" + attachfileNum + ", commentNum="
                + commentNum + "]";
    }
    public ModelArticle(Integer articleno, String boardcd, String title,
            String content, String email, Integer hit, Date regdate,
            Boolean useYN, String insertUID, Date insertDT, String updateUID,
            Date updateDT, Integer countgood, Integer countbad,
            Integer attachfileNum, Integer commentNum) {
        super();
        this.articleno = articleno;
        this.boardcd = boardcd;
        this.title = title;
        this.content = content;
        this.email = email;
        this.hit = hit;
        this.regdate = regdate;
        UseYN = useYN;
        InsertUID = insertUID;
        InsertDT = insertDT;
        UpdateUID = updateUID;
        UpdateDT = updateDT;
        this.countgood = countgood;
        this.countbad = countbad;
        this.attachfileNum = attachfileNum;
        this.commentNum = commentNum;
    }
    public ModelArticle() {
        super();
    }
        
    //
    
    
}
