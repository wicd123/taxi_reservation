package com.spring81.bbs.model;

import java.util.Date;

public class ModelComments {
    
    private Integer commentno; // INT(10) UNSIGNED NOT NULL AUTO_INCREMENT
    private Integer articleno; // INT(10) UNSIGNED NOT NULL
    private String  email;     // VARCHAR(60) NULL DEFAULT NULL
    private String  memo;      // VARCHAR(4000) NULL DEFAULT NULL
    private Date    regdate;   // DATETIME NULL DEFAULT NULL
    private Boolean UseYN;     // TINYINT(1) NULL DEFAULT '1'
    private String  InsertUID; // VARCHAR(40) ULL DEFAULT NULL
    private Date    InsertDT;  // DATETIME NULL DEFAULT NULL
    private String  UpdateUID; // VARCHAR(40) NULL DEFAULT NULL
    private Date    UpdateDT;  // DATETIME NULL DEFAULT NULL
    private Integer countgood = null;
    private Integer countbad = null;
    public Integer getCommentno() {
        return commentno;
    }
    public void setCommentno(Integer commentno) {
        this.commentno = commentno;
    }
    public Integer getArticleno() {
        return articleno;
    }
    public void setArticleno(Integer articleno) {
        this.articleno = articleno;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
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
    @Override
    public String toString() {
        return "ModelComments [commentno=" + commentno + ", articleno="
                + articleno + ", email=" + email + ", memo=" + memo
                + ", regdate=" + regdate + ", UseYN=" + UseYN + ", InsertUID="
                + InsertUID + ", InsertDT=" + InsertDT + ", UpdateUID="
                + UpdateUID + ", UpdateDT=" + UpdateDT + ", countgood="
                + countgood + ", countbad=" + countbad + "]";
    }
    public ModelComments(Integer commentno, Integer articleno, String email,
            String memo, Date regdate, Boolean useYN, String insertUID,
            Date insertDT, String updateUID, Date updateDT, Integer countgood,
            Integer countbad) {
        super();
        this.commentno = commentno;
        this.articleno = articleno;
        this.email = email;
        this.memo = memo;
        this.regdate = regdate;
        UseYN = useYN;
        InsertUID = insertUID;
        InsertDT = insertDT;
        UpdateUID = updateUID;
        UpdateDT = updateDT;
        this.countgood = countgood;
        this.countbad = countbad;
    }
    public ModelComments() {
        super();
    }
    public ModelComments(Integer articleno ) {
        super();
        this.articleno = articleno;
    }
    
    
}
