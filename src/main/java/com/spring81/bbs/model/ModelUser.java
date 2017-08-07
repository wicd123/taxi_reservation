package com.spring81.bbs.model;

import java.util.Date;

public class ModelUser {
    
    private Integer userno    = null; // INT(10) UNSIGNED NOT NULL AUTO_INCREMENT
    private String  userid    = "";   // VARCHAR(50) NOT NULL
    private String  email     = "";   // VARCHAR(100) NOT NULL
    private String  passwd    = "";   // VARCHAR(30) NULL DEFAULT NULL
    private String  name      = "";   // VARCHAR(30) NULL DEFAULT NULL
    private String  mobile    = "";   // VARCHAR(20) NULL DEFAULT NULL
    private Boolean retireYN  = null; // TINYINT(1) NULL DEFAULT '1'
    private String  InsertUID = "";   // VARCHAR(40) NULL DEFAULT NULL
    private Date    InsertDT  = null; // DATETIME NULL DEFAULT NULL
    private String  UpdateUID = "";   // VARCHAR(40) NULL DEFAULT NULL
    private Date    UpdateDT  = null; // DATETIME NULL DEFAULT NULL
    
    public Integer getUserno() {
        return userno;
    }
    
    public void setUserno(Integer userno) {
        this.userno = userno;
    }
    
    public String getUserid() {
        return userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPasswd() {
        return passwd;
    }
    
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public Boolean getRetireYN() {
        return retireYN;
    }
    
    public void setRetireYN(Boolean retireYN) {
        this.retireYN = retireYN;
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
    
    @Override
    public String toString() {
        return "ModelUser [userno=" + userno + ", userid=" + userid + ", email="
                + email + ", passwd=" + passwd + ", name=" + name + ", mobile="
                + mobile + ", retireYN=" + retireYN + ", InsertUID=" + InsertUID
                + ", InsertDT=" + InsertDT + ", UpdateUID=" + UpdateUID
                + ", UpdateDT=" + UpdateDT + "]";
    }
    
    public ModelUser() {
        super();
    }
    
    public ModelUser(String id, String pw) {
        super();
        this.userid = id;
        this.passwd = pw;
    }
    
    public ModelUser(String id, String name, String email, String password) {
        super();

        this.userid = id;
        this.name = name;
        this.email = email;
        this.passwd = password;
    }

    public ModelUser(String userid) {
        
        super();

        this.userid = userid;
        
        // TODO Auto-generated constructor stub
    }

    public ModelUser(Integer userno) {
        super();
        this.userno = userno;
        // TODO Auto-generated constructor stub
    } 
    
}
