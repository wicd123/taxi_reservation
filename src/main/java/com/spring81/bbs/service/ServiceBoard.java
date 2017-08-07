package com.spring81.bbs.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring81.bbs.commons.EnumGoodBad;
import com.spring81.bbs.commons.WebConstants;
import com.spring81.bbs.dao.IDaoBoard;
import com.spring81.bbs.model.ModelArticle;
import com.spring81.bbs.model.ModelArticleRecommend;
import com.spring81.bbs.model.ModelAttachfile;
import com.spring81.bbs.model.ModelBoard;
import com.spring81.bbs.model.ModelComments;

@Service("serviceboard")
public class ServiceBoard implements IServiceBoard {
    
    // SLF4J Logging
    private static Logger logger = LoggerFactory.getLogger(ServiceBoard.class);
    
    @Autowired
    @Qualifier("daoboard")
    private IDaoBoard daoboard;

    @Override
    public String getBoardName(String boardcd) {
        
        String result = null;
        try {
            result = daoboard.getBoardName(boardcd);
        } catch (Exception e) {
            logger.error("getBoardName " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public ModelBoard getBoardOne(String boardcd) {
        ModelBoard result = null;
        try {
            result = daoboard.getBoardOne(boardcd);
        } catch (Exception e) {
            logger.error("getBoardOne " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int getBoardTotalRecord(String boardcd, String searchWord) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<ModelBoard> getBoardList() {
        List<ModelBoard> result = null;
        try {
            result = daoboard.getBoardList();
        } catch (Exception e) {
            logger.error("getBoardOne " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public Map<String, ModelBoard> getBoardListResultMap() {
        Map<String, ModelBoard> result = null;
        try {
            result = daoboard.getBoardListResultMap();
        } catch (Exception e) {
            logger.error("getBoardOne " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int insertBoard(ModelBoard board) {
        
        int result = -1;
        try {
            result = daoboard.insertBoard(board);
        } catch (Exception e) {
            logger.error("insertBoard " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int updateBoard(ModelBoard updateBoard, ModelBoard searchBoard) {
        
        int result = -1;
        try {
            result = daoboard.updateBoard(updateBoard, searchBoard);
        } catch (Exception e) {
            logger.error("updateBoard" + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int deleteBoard(ModelBoard board) {
        int result = -1;
        try {
            result = daoboard.deleteBoard(board);
        } catch (Exception e) {
            logger.error("deleteBoard" + e.getMessage());
        }
        
        return result;
    }

    @Override
    public List<ModelBoard> getBoardSearch(ModelBoard board) {
        
        List<ModelBoard> result = null;
        try {
            result = daoboard.getBoardSearch(board);
        } catch (Exception e) {
            logger.error("getBoardOne " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public List<ModelBoard> getBoardPaging(String boardcd, String searchWord, int start, int end) {
        
        List<ModelBoard> result = null;
        try {
            result = daoboard.getBoardPaging(boardcd, searchWord, start, end);
        } catch (Exception e) {
            logger.error("getBoardPaging  " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int insertBoardList(List<ModelBoard> list) {
        int result = -1;
        try {
            result = daoboard.insertBoardList(list);
            //session.commit();
        } catch (Exception e) {
            logger.error("insertBoardList" + e.getMessage() );
            //session.rollback();
        }
        
        return result;
    }

    @Override
    public int getArticleTotalRecord(String boardcd, String searchWord) {
        int result = 0;
        try {
            result = daoboard.getArticleTotalRecord(boardcd, searchWord);
        } catch (Exception e) {
            logger.error("getArticleTotalRecord  " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public List<ModelArticle> getArticleList(String boardcd, String searchWord, int start, int end) {
        
        List<ModelArticle> result = null;
        try {
            result = daoboard.getArticleList(boardcd, searchWord, start, end);
        } catch (Exception e) {
            logger.error("getArticleList  " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public ModelArticle getArticle(int articleno) {
        ModelArticle result = null;
        try {
            result = daoboard.getArticle ( articleno );
        } catch (Exception e) {
            logger.error("getArticle  " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int insertArticle(ModelArticle article) {
        int result = -1;
        try {
            result = daoboard.insertArticle(article);
        } catch (Exception e) {
            logger.error("insertArticle " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int updateArticle(ModelArticle updateValue, ModelArticle searchValue) {
        
        int result = -1;
        try {
            result = daoboard.updateArticle(updateValue, searchValue);
        } catch (Exception e) {
            logger.error("updateArticle " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int deleteArticle(ModelArticle article) {
        int result = -1;
        try {
                     daoboard.deleteAttachFile(new ModelAttachfile( article.getArticleno() ) );
                     daoboard.deleteComment(new ModelComments( article.getArticleno() ) );
            result = daoboard.deleteArticle(article);
        } catch (Exception e) {
            logger.error("deleteArticle  " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int increaseHit(int articleno) {
        int result = -1;
        try {
            result = daoboard.increaseHit(articleno);
        } catch (Exception e) {
            logger.error("increaseHit  " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public ModelArticle getNextArticle(int articleno, String boardcd, String searchWord) {
        
        ModelArticle result = null;
        try {
            result = daoboard.getNextArticle( articleno, boardcd, searchWord );
        } catch (Exception e) {
            logger.error("getNextArticle  " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public ModelArticle getPrevArticle(int articleno, String boardcd, String searchWord) {
        
        ModelArticle result = null;
        try {
            result = daoboard.getPrevArticle( articleno, boardcd, searchWord );
        } catch (Exception e) {
            logger.error("getPrevArticle  " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public ModelAttachfile getAttachFile(int attachfileno) {
        ModelAttachfile result = null;
        try {
            result = daoboard.getAttachFile( attachfileno );
        } catch (Exception e) {
            logger.error("getAttachFile  " + e.getMessage() );
        }
        
        return result;
    }
    
    @Override
    public List<ModelAttachfile> getAttachFileList(int articleno) {
        List<ModelAttachfile> result = null;
        try {
            result = daoboard.getAttachFileList( articleno );
        } catch (Exception e) {
            logger.error("getAttachFileList  " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int insertAttachFile(ModelAttachfile attachFile) {
        int result = -1;
        try {
            result = daoboard.insertAttachFile(attachFile);
        } catch (Exception e) {
            logger.error("insertAttachFile " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int deleteAttachFile(ModelAttachfile attachFile) {
        int result = -1;
        try {
            result = daoboard.deleteAttachFile(attachFile);
        } catch (Exception e) {
            logger.error("deleteAttachFile " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public ModelComments getComment(int commentno) {
        ModelComments result = null;
        try {
            result = daoboard.getComment( commentno );
        } catch (Exception e) {
            logger.error("getComment  " + e.getMessage() );
        }
        
        return result;
    }


    @Override
    public List<ModelComments> getCommentList(int articleno) {
        List<ModelComments>  result = null;
        try {
            result = daoboard.getCommentList( articleno );
        } catch (Exception e) {
            logger.error("getCommentList  " + e.getMessage() );
        }
        
        return result;
    }
    
    @Override
    public int insertComment(ModelComments comment) {
        int result = -1;
        try {
            result = daoboard.insertComment(comment);
        } catch (Exception e) {
            logger.error("insertComment " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int updateComment(ModelComments updateValue, ModelComments searchValue) {
        int result = -1;
        try {
            result = daoboard.updateComment( updateValue, searchValue );
        } catch (Exception e) {
            logger.error("updateComment " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int deleteComment(ModelComments comment) {
        int result = -1;
        try {
            result = daoboard.deleteComment( comment );
        } catch (Exception e) {
            logger.error("deleteComment " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public ModelArticle transUpdateHitAndGetArticle(int articleno) {
        ModelArticle result = null;
        try {

            // 상세보기를 할때마다 조회수를 1 증가
            // 하단에 목록에서 조회수를 제대로 보기위해서는
            // 목록 레코드를 페치하기 전에 조회수를 먼저 증가시켜야 한다.
            // 사용자 IP 와 시간을 고려해서 조회수를 증가하도록...
            
                     daoboard.increaseHit( articleno );
            result = daoboard.getArticle ( articleno );
        } catch (Exception e) {
            logger.error("getArticle  " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int transDeleteArticle(String boardcd, int articleno) {
     // articledelte 시,
        // 1. 관련된 comment 삭제
        // 2. 관련된 attachfile 삭제
        // 3. 관련된 첨부 파일 삭제
        // 4. article 삭제
        
        int result = -1;
        // 1. 관련된 comment 삭제
        ModelComments comment = new ModelComments( articleno );
        result = daoboard.deleteComment(comment);
        
        //2. 업로드 폴더에 관련된 첨부 파일 삭제
        List<ModelAttachfile> files = daoboard.getAttachFileList(articleno);
        for(int i = 0 ; i < files.size(); i++){
            ModelAttachfile attachfile = files.get(i);
            
            String path = WebConstants.UPLOAD_PATH + attachfile.getTempfilename();
            java.io.File file = new java.io.File(path);
            
            if(file.exists()){
                file.delete();
            }
        }
        
        ModelAttachfile attachFile = new ModelAttachfile();
        attachFile.setAttachfileno(articleno);
        //3. TB_BBS_Attachfile 테이블에서 있는 ataachfile 정보 삭제
        daoboard.deleteAttachFile(attachFile);
        
        
        ModelArticle article = new ModelArticle();
        article.setArticleno(articleno);
        result = daoboard.deleteArticle(article);
        
        
        
        return 0;
    }

    @Override
    public int changeArticletGood(int articleno, int count) {
        int result = -1;
        try {
            result = daoboard.changeArticletGood( articleno, count );
        } catch (Exception e) {
            logger.error("changeArticletGood " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int changeArticlebad(int articleno, int count) {
        int result = -1;
        try {
            result = daoboard.changeArticlebad( articleno, count );
        } catch (Exception e) {
            logger.error("changeArticlebad " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int changeCommentGood(int commentno, int count) {
        int result = -1;
        try {
            result = daoboard.changeCommentGood( commentno, count );
        } catch (Exception e) {
            logger.error("changeCommentGood " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int changeCommentbad(int commentno, int count) {
        int result = -1;
        try {
            result = daoboard.changeCommentbad( commentno, count );
        } catch (Exception e) {
            logger.error("changeCommentbad " + e.getMessage() );
        }
        
        return result;
    }
    @Override
    public int mergeArticleRecommend(ModelArticleRecommend recomend) {
        int result = -1;
        try {
            result = daoboard.mergeArticleRecommend( recomend);
        } catch (Exception e) {
            logger.error("changeCommentbad " + e.getMessage() );
        }
        
        return result;
    }
    
    @Override
    public ModelArticleRecommend getArticleRecommendOne(ModelArticleRecommend recomend) {
        ModelArticleRecommend result = null;
        try {
            result = daoboard.getArticleRecommendOne( recomend);
        } catch (Exception e) {
            logger.error("changeCommentbad " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int updateGoodBadCount(int articleno) {
        int result = -1;
        try {
            result = daoboard.updateGoodBadCount( articleno);
        } catch (Exception e) {
            logger.error("updateGoodBadCount " + e.getMessage() );
        }
        
        return result;
    }
    @Override
    public int deleteArticleRecommend(ModelArticleRecommend recomend) {
        int result = -1;
        try {
            result = daoboard.deleteArticleRecommend( recomend);
        } catch (Exception e) {
            logger.error("deleteArticleRecommend " + e.getMessage() );
        }
        
        return result;
    }

    @Override
    public int transRecommendArticle(int articleno,String userid, EnumGoodBad codeGB, boolean updown) {
        int result = -1;
        try {
            ModelArticleRecommend recommend = new ModelArticleRecommend();
            recommend.setArticleno(articleno);
            recommend.setUserid(userid);
            if( codeGB == EnumGoodBad.GOOD)
                recommend.setGood(updown);
            else if ( codeGB == EnumGoodBad.BAD)
                recommend.setBad(updown);
            
            result = daoboard.mergeArticleRecommend(recommend);
            
            
            result = daoboard.updateGoodBadCount(articleno);
            
        } catch (Exception e) {
            logger.error("deleteArticleRecommend " + e.getMessage() );
        }
        
        return result;
    }

    
}






















