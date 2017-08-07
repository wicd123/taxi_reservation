package com.spring81.bbs.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Repository;

import com.spring81.bbs.model.ModelUser;

@Repository("daouser")
public class DaoUser implements IDaoUser {

    @Autowired
    @Qualifier("sqlSession")
    private SqlSession session;
    


    @Override
    public ModelUser selectUserOne(Integer userno) {
        return session.selectOne("mapper.mapperUser.selectUserOne", userno);
    }

    @Override
    public List<ModelUser> selectUserList(ModelUser user) {
        return session.selectList("mapper.mapperUser.selectUserList", user);
    }
    
    @Override
    public int insertUser(ModelUser user) {
        return session.insert("mapper.mapperUser.insertUser", user);   
    }

    @Override
    public ModelUser login(String id, String pw ) {

        ModelUser user = new ModelUser(id, pw);
        return session.selectOne("mapper.mapperUser.login", user);
    }

    @Override
    public int logout(String userid) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateUserInfo(ModelUser updateValue, ModelUser searchValue) {
        
        Map<String, ModelUser> parameter = new HashMap<String, ModelUser>();
 
        parameter.put("updateValue",             updateValue);
        parameter.put("searchValue",             searchValue);

        return session.update("mapper.mapperUser.updateUserInfo", parameter);
    }

    @Override
    public int updatePasswd(String newPasswd, String currentPasswd, String userid) {
        Map<String, String> map = new HashMap<>();
        map.put("newPasswd"    , newPasswd);
        map.put("currentPasswd", currentPasswd);
        map.put("userid"       , userid);
                
        return session.update("mapper.mapperUser.updatePasswd", map);
    }

    @Override
    public int deleteUser(ModelUser user) {
        return session.update("mapper.mapperUser.deleteUser", user);
    }
	
    @Override
    public int checkuserid(String userid) {
        return session.selectOne("mapper.mapperUser.checkuserid", userid);
    }
    
}
