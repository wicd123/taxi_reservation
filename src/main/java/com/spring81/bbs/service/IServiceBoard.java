package com.spring81.bbs.service;

import com.spring81.bbs.commons.EnumGoodBad;
import com.spring81.bbs.dao.IDaoBoard;
import com.spring81.bbs.model.ModelArticle;

public interface IServiceBoard extends IDaoBoard {
    
    public abstract ModelArticle transUpdateHitAndGetArticle(int articleno);
    public abstract int transDeleteArticle(String boardcd, int articleno);
    
    /*
     *  article에서 좋아요나 나빠요를 클릭했을 때 처리하는 메서드
     *  1 --> 좋아요(good) ->up
     *  1 --> 좋아요(good) ->dwon
     *  1 --> 나빠요(bad) ->up
     *  1 --> 나빠요(bad) ->dwon
     */
    public abstract int transRecommendArticle(int articleno, String userid, EnumGoodBad codeGB, boolean updown);
}
//articledelte 시,
// 1. 관련된 comment 삭제
// 2. 관련된 attachfile 삭제
// 3. 관련된 첨부 파일 삭제
// 4. article 삭제