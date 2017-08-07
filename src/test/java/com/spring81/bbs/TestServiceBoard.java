package com.spring81.bbs;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring81.bbs.commons.EnumGoodBad;
import com.spring81.bbs.model.ModelArticle;
import com.spring81.bbs.model.ModelArticleRecommend;
import com.spring81.bbs.service.IServiceBoard;

public class TestServiceBoard {
    // SLF4J Logging
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    static ApplicationContext context = null;
    static IServiceBoard service = null;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml");
        service =context.getBean("serviceboard", IServiceBoard.class);
    }
    
    @Test
    public void testGetBoardName() {
        String result = service.getBoardName("free");
        
        assertEquals("자유게시판", result);
    }
    
    @Test
    public void testGetBoardOne() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetBoardTotalRecord() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetBoardList() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetBoardListResultMap() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testInsertBoard() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testUpdateBoard() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testDeleteBoard() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetBoardSearch() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetBoardPaging() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testInsertBoardList() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetArticleTotalRecord() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetArticleList() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetArticle() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testInsertArticle() {
        ModelArticle article = new ModelArticle();
        article.setBoardcd("free");
        
        int result = service.insertArticle(article);
        assertTrue( result > 1 );
    }
    
    @Test
    public void testUpdateArticle() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testDeleteArticle() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testIncreaseHit() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetNextArticle() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetPrevArticle() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetAttachFile() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetAttachFileList() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testInsertAttachFile() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testDeleteAttachFile() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetComment() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetCommentList() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testInsertComment() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testUpdateComment() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testDeleteComment() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testChangeArticleGood_up() {
        int articleno = 1;
        int count = +1;
        
        ModelArticle articleBefore = service.getArticle(articleno);
        
        service.changeArticletGood(articleno, count );
        
        ModelArticle articleafter = service.getArticle(articleno);
        
        assertSame(articleBefore.getCountgood() +1, articleafter.getCountgood());
    }
    @Test
    public void testChangeArticleGood_dn() {
        int articleno = 1;
        int count = -1;
        
        ModelArticle articleBefore = service.getArticle(articleno);
        
        service.changeArticletGood(articleno, count );
        
        ModelArticle articleafter = service.getArticle(articleno);
        
        assertSame(articleBefore.getCountgood() -1, articleafter.getCountgood());
    }
    @Test
    public void testChangeArticleBad_up() {
        int articleno = 1;
        int count = +1;
        
        ModelArticle articleBefore = service.getArticle(articleno);
        
        service.changeArticletGood(articleno, count);
        
        ModelArticle articleafter = service.getArticle(articleno);
        
        assertSame(articleBefore.getCountbad() +1, articleafter.getCountbad());
    }
    public void testChangeArticleBad_dn() {
        int articleno = 1;
        int count = -1;
        
        ModelArticle articleBefore = service.getArticle(articleno);
        
        service.changeArticletGood(articleno, count);
        
        ModelArticle articleafter = service.getArticle(articleno);
        
        assertSame(articleBefore.getCountbad() -1, articleafter.getCountbad());
    }
    @Test
    public void testChangeCommentGood_up() {
        int articleno = 1;
        int count = +1;
        
        ModelArticle articleBefore = service.getArticle(articleno);
        
        service.changeCommentGood(articleno, count);
        
        ModelArticle articleafter = service.getArticle(articleno);
        
        assertSame(articleBefore.getCountgood() +1, articleafter.getCountgood());
    }
    @Test
    public void testChangeCommentGood_dn() {
        int commentno = 1;
        int count = -1;
        
        ModelArticle articleBefore = service.getArticle(commentno);
        
        service.changeCommentGood(commentno, count);
        
        ModelArticle articleafter = service.getArticle(commentno);
        
        assertSame(articleBefore.getCountgood() -1, articleafter.getCountgood());
    }
    @Test
    public void testChangeCommentBad_up() {
        int commentno = 1;
        int count = +1;
        
        ModelArticle articleBefore = service.getArticle(commentno);
        
        service.changeCommentbad(commentno, count);
        
        ModelArticle articleafter = service.getArticle(commentno);
        
        assertSame(articleBefore.getCountbad() +1, articleafter.getCountbad());
    }
    @Test
    public void testChangeCommentBad_dn() {
        int commentno = 1;
        int count = -1;
        
        ModelArticle articleBefore = service.getArticle(commentno);
        
        service.changeCommentbad(commentno, count);
        
        ModelArticle articleafter = service.getArticle(commentno);
        
        assertSame(articleBefore.getCountbad() -1, articleafter.getCountbad());
    }
    @Test
    public void testmergeArticleRecommend_insert() {
        ModelArticleRecommend re1, re2 = null;
        int result = -1;
        
        ModelArticleRecommend recomend = new ModelArticleRecommend();
        recomend.setArticleno(2);
        recomend.setUserid("222");
        
        //select 테스트
        
        re1 = service.getArticleRecommendOne(recomend);
        
        //inserttest
        result = service.mergeArticleRecommend(recomend);
        assertTrue(result >= 1 );
        
        //update 테스트 : good + 1;
        recomend.setGood(true);
        result = service.mergeArticleRecommend(recomend);
        re2 = service.getArticleRecommendOne(recomend);
        assertTrue(re2.getGood() );;
        
      //update 테스트 : good - 1;
        recomend.setGood(false);
        result = service.mergeArticleRecommend(recomend);
        re2 = service.getArticleRecommendOne(recomend);
        assertTrue( re2.getGood() );;
        
        //update 테스트 : bad + 1;
        recomend.setBad(true);
        result = service.mergeArticleRecommend(recomend);
        re2 = service.getArticleRecommendOne(recomend);
        assertTrue(re2.getBad() );;
        
      //update 테스트 : bad - 1;
        recomend.setBad(false);
        result = service.mergeArticleRecommend(recomend);
        re2 = service.getArticleRecommendOne(recomend);
        assertFalse(re2.getBad() );;
    }
    @Test
    public void testUpdateGoodBadCount() {
        ModelArticleRecommend re1, re2 = null;
        int result = -1;
        int articleno = 2;
        
        ModelArticleRecommend recomend = new ModelArticleRecommend();
        recomend.setArticleno(2);
        recomend.setUserid("222");
        
        service.deleteArticleRecommend(recomend);
        
        
        recomend.setGood(true);
        recomend.setBad(false);
        result = service.mergeArticleRecommend(recomend);
        re2 = service.getArticleRecommendOne(recomend);
        assertTrue( re2.getGood());
        assertFalse( re2.getBad() );
        
        service.updateGoodBadCount(articleno);
        ModelArticle article = service.getArticle(articleno);
        assertSame(article.getCountgood(), 1);
        assertSame(article.getCountbad(), 0);
    }
/*    *  1 --> 좋아요(good) ->up
    *  1 --> 좋아요(good) ->dwon
    *  1 --> 나빠요(bad) ->up
    *  1 --> 나빠요(bad) ->dwon*/
    @Test
    public void transRecommendArticle() {
        ModelArticleRecommend recommend = null, re1 =null;
        String userid = "222";
        int result = -1;
        int articleno = 2;
        
        
        
        
        recommend = new ModelArticleRecommend();
        recommend.setArticleno(articleno);
        recommend.setUserid(userid);
        
        /*  1 --> 좋아요(good) ->up */
        result = service.transRecommendArticle(articleno, userid, EnumGoodBad.GOOD, true);
        assertTrue(result >= 1);
        
        
        re1 = service.getArticleRecommendOne(recommend);
        assertEquals(re1.getGood(), true);
        assertEquals(re1.getBad(), false);
        
        
        /*  2 --> 좋아요(good) ->down */
        
        result = service.transRecommendArticle(articleno, userid, EnumGoodBad.GOOD, false);
        assertTrue(result >= 1);
        
        
        re1 = service.getArticleRecommendOne(recommend);
        assertEquals(re1.getGood(), false);
        assertEquals(re1.getBad(), false);
        
        
        /* 3 --> 나빠요(bad) ->up */
        result = service.transRecommendArticle(articleno, userid, EnumGoodBad.BAD, true);
        assertTrue(result >= 1);
        
        
        re1 = service.getArticleRecommendOne(recommend);
        assertEquals(re1.getGood(), false);
        assertEquals(re1.getBad(), true);
        
        
        /* 4 --> 나빠요(bad) ->dwon */
        result = service.transRecommendArticle(articleno, userid, EnumGoodBad.BAD, false);
        assertTrue(result >= 1);
        
        
        re1 = service.getArticleRecommendOne(recommend);
        assertEquals(re1.getGood(), false);
        assertEquals(re1.getBad(), false);
        
    }
    @Test
    public void testEnumGoodBad_valueOf(){
        String codegb = "Good";
        EnumGoodBad value = EnumGoodBad.valueOf(codegb.toUpperCase());
        assertEquals(value, EnumGoodBad.GOOD);
    }
}
