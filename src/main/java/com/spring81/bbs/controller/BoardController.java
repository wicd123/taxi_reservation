package com.spring81.bbs.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring81.bbs.commons.EnumGoodBad;
import com.spring81.bbs.commons.PagingHelper;
import com.spring81.bbs.commons.WebConstants;
import com.spring81.bbs.model.ModelArticle;
import com.spring81.bbs.model.ModelArticleRecommend;
import com.spring81.bbs.model.ModelAttachfile;
import com.spring81.bbs.model.ModelBoard;
import com.spring81.bbs.model.ModelComments;
import com.spring81.bbs.model.ModelUser;
import com.spring81.bbs.service.IServiceBoard;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	IServiceBoard boardsrv;
	
	@RequestMapping(value = "/board/boardlist", method = RequestMethod.GET)
	public String boardlist( Model model ) {
		logger.info("/board/boardlist");
		
		List<ModelBoard>  result = boardsrv.getBoardList();
		model.addAttribute("list", result);
		
		return "board/boardlist";
	}
    

    /**
     * http://localhost/board/boardwrite
     */
    @RequestMapping(value = "/board/boardwrite", method = RequestMethod.GET)
    public String boardwrite( Model model ) {
        logger.info("/board/boardwrite : get");
                
        return "board/boardwrite";
    }
    @RequestMapping(value = "/board/boardwrite", method = RequestMethod.POST)
    public String boardwrite( Model model
            , @RequestParam(value="boardcd", defaultValue="") String boardcd
            , @RequestParam(value="boardnm", defaultValue="") String boardnm 
            , @RequestParam(value="UseYN"  , defaultValue="") Boolean useYN ) {
        logger.info("/board/boardwrite : post");
        
        // DB insert
        ModelBoard board  = new ModelBoard();
        board.setBoardcd(boardcd);
        board.setBoardnm(boardnm);
        board.setUseYN  (useYN  );
        
        int result =  boardsrv.insertBoard(board);
        
        if( result == 1 ){
            // /board/boardlist 리다이렉트 
            return "redirect:/board/boardlist";
        }
        else {            
            return "board/boardwrite";            
        }
    }
	
    /**
     * http://localhost/board/boardview?boardcd=qna
     */
    @RequestMapping(value = "/board/boardview", method = RequestMethod.GET)
    public String boardview( Model model 
		, @RequestParam(value="boardcd", required = false, defaultValue = "free") String boardcd 
        , @RequestParam(value="boardnm", required = false, defaultValue = ""    ) String boardnm ) {

        logger.info("/board/boardview");

        if( StringUtils.isEmpty(boardcd) ) boardcd = "free";

        // DB 처리
        ModelBoard board =  boardsrv.getBoardOne(boardcd);
        
        // 모델 바인딩
        model.addAttribute("boardnm", board.getBoardnm() );
        model.addAttribute("board"  ,  board );

        return "board/boardview";
    }	

    /**
     * http://localhost/board/boardview/qna
     */
    @RequestMapping(value = "/board/boardview/{boardcd}", method = RequestMethod.GET)
    public String boardview( Model model, @PathVariable(value="boardcd") String boardcd  ) {

        logger.info("/board/boardview/{boardcd}");

        // DB 처리
        ModelBoard board =  boardsrv.getBoardOne(boardcd);
        
        // 모델 바인딩
        model.addAttribute("boardnm", board.getBoardnm() );
        model.addAttribute("board"  ,  board );

        return "board/boardview";
    }

    /**
     * http://localhost/board/boardmodify?boardcd=qna
     */
    @RequestMapping(value = "/board/boardmodify", method = RequestMethod.GET)
    public String boardmodify( Model model
                             , @RequestParam(value="boardcd", defaultValue = "free") String boardcd 
	    	                 , HttpServletRequest request ) {
        logger.info("/board/boardmodify");
        
        // DB 처리
        ModelBoard board =  boardsrv.getBoardOne(boardcd);
        
        // 모델 바인딩
        model.addAttribute("boardnm", board.getBoardnm() );
        model.addAttribute("board"  , board );

        return "board/boardmodify";
    }   
    
    /**
     * http://localhost/board/boardmodify?boardcd=qna
     */
    @RequestMapping(value = "/board/boardmodify", method = RequestMethod.POST)
    public String boardmodify( Model model 
	                         , @ModelAttribute ModelBoard board
		                     , HttpServletRequest request ) {
        logger.info("/board/boardmodify");

        ModelBoard updateBoard = new ModelBoard();
        updateBoard.setBoardnm(  board.getBoardnm()  );
        updateBoard.setUseYN  (  board.getUseYN() == null ? false:  board.getUseYN()  );
        
        // DB 처리
        ModelBoard searchBoard = new ModelBoard();
        searchBoard.setBoardcd( board.getBoardcd() );
        
        int result =  boardsrv.updateBoard(updateBoard, searchBoard);
        
        if( result >= 1 ){
            // /board/boardlist 리다이렉트 
            return "redirect:/board/boardlist";
        }
        else {            
            return "board/boardmodify/" + board.getBoardcd();            
        }
    }
    

    /**
     * http://localhost/board/boardmodify/qna
     */
    @RequestMapping(value = "/board/boardmodify/{boardcd}", method = RequestMethod.GET)
    public String boardmodifypath( Model model 
            , @PathVariable(value="boardcd")  String boardcd
            , HttpServletRequest request) {
        logger.info("/board/boardmodifypath : GET");
        
        // DB 처리
        ModelBoard board =  boardsrv.getBoardOne(boardcd);
        
        // 모델 바인딩
        model.addAttribute("boardnm", board.getBoardnm() );
        model.addAttribute("board"  , board );

        return "board/boardmodify";
    }
    

    /**
     * http://localhost/board/boardview/qna
     */
    @RequestMapping(value = "/board/boardmodify/{boardcd}", method = RequestMethod.POST)
    public String boardmodifypath( 
          HttpServletRequest request
        , @PathVariable(value="boardcd")  String boardcd
        , @ModelAttribute("board") ModelBoard board
        , Model model )  {

        logger.info("/board/boardmodifypath : POST");

        ModelBoard updateBoard = new ModelBoard();
        updateBoard.setBoardnm(  board.getBoardnm()  );
        updateBoard.setUseYN  (  board.getUseYN() == null ? false:  board.getUseYN()  );
        
        ModelBoard searchBoard = new ModelBoard();
        searchBoard.setBoardcd(  board.getBoardcd()  );
                
        // 디비 업데이트
        int result = boardsrv.updateBoard(updateBoard, searchBoard);
        
        if( result > 0 ) {
            // http://localhost/board/boardlist 가 출력되게. redirect 를 이용 
            return "redirect:/board/boardlist";       
        }
        else {
            // http://localhost/board/boardmodify?boardcd=qna
            return "redirect:/board/boardmodify/" + board.getBoardcd() ;  
        }
    }       

    /**
     * http://localhost/board/boarddelete/qna
     */
    @RequestMapping(value = "/board/boarddelete/{boardcd}", method = RequestMethod.POST)
    public String boarddelete( Model model 
                             , @PathVariable(value="boardcd") String boardcd) {
        logger.info("/board/boarddelete");
        
        ModelBoard board = new ModelBoard(boardcd);
        
        // DB 처리              
        int result =  boardsrv.deleteBoard(board);
        
        if( result > 0 ){
            // /board/boardlist 리다이렉트 
            return "redirect:/board/boardlist";
        }
        else {       
            // /board/boardview 리다이렉트 
            return "redirect:/board/boardview/"+ boardcd;            
        }
    } 
    
    /**
     * http://localhost/board/articlelist/qna?curPage=1&searchWord=
     */
    @RequestMapping(value = "/board/articlelist/{boardcd}", method = RequestMethod.GET)
    public String articlelist( Model model
            , @PathVariable(value="boardcd") String boardcd
            , @RequestParam(value="curPage"   , defaultValue="1") Integer    curPage
            , @RequestParam(value="searchWord", defaultValue="" ) String searchWord) {
        logger.info("/board/articlelist");

        String boardnm          = boardsrv.getBoardName(boardcd);        
        model.addAttribute("boardnm"   , boardnm   );
        model.addAttribute("boardcd"   , boardcd   );
        model.addAttribute("curPage"   , curPage   );
        model.addAttribute("searchWord", searchWord);

        /*
         *  articlelist-table.jsp 와 관련된 처리
         */
        
        // 전체 게시글 갯수 가져오기
        int totalRecord = boardsrv.getArticleTotalRecord(boardcd, searchWord);
        
        // 페이지 처리
        PagingHelper paging = new PagingHelper(totalRecord, curPage);        
        int start = paging.getStartRecord();
        int end   = paging.getEndRecord();
        
        List<ModelArticle> list = boardsrv.getArticleList(boardcd, searchWord, start, end);
        model.addAttribute("list"          , list                       );
        model.addAttribute("no"            , paging.getListNo        () );
        model.addAttribute("totalFirstPage", paging.getTotalFirstPage() );
        model.addAttribute("prevLink"      , paging.getPrevLink      () );
        model.addAttribute("pageLinks"     , paging.getPageLinks     () );
        model.addAttribute("nextLink"      , paging.getNextLink      () );
        model.addAttribute("totalLastPage" , paging.getTotalLastPage () );   
        
        return "board/articlelist";
    }

	// http://localhost/board/articleview/free/17?curPage=1&searchWord=    
    @RequestMapping(value = "/board/articleview/{boardcd}/{articleno}", method = RequestMethod.GET)
    public String articleview( Model model 
            , @PathVariable(value="boardcd"  )  String  boardcd
            , @PathVariable(value="articleno")  Integer articleno
            , @RequestParam(value="curPage"   , defaultValue="1") Integer curPage
            , @RequestParam(value="searchWord", defaultValue="" ) String  searchWord ) {
        logger.info("/board/articleview");
                
        //boardnm
        String boardnm = boardsrv.getBoardName(boardcd);
        model.addAttribute("boardnm", boardnm);
        
        //thisArticle
        ModelArticle thisArticle = boardsrv.transUpdateHitAndGetArticle(articleno);
        model.addAttribute("thisArticle", thisArticle);
        
        // attachFileList
        List<ModelAttachfile> attachFileList = boardsrv.getAttachFileList(articleno);
        model.addAttribute("attachFileList", attachFileList);
        
        //commentList
        List<ModelComments> commentList = boardsrv.getCommentList(articleno);
        model.addAttribute("commentList", commentList);        
        
        //nextArticle
        ModelArticle nextArticle = boardsrv.getNextArticle(articleno, boardcd, searchWord);
        model.addAttribute("nextArticle", nextArticle);
        
        //prevArticle
        ModelArticle prevArticle = boardsrv.getPrevArticle(articleno, boardcd, searchWord);
        model.addAttribute("prevArticle", prevArticle);

        /*
         *  articlelist-table.jsp 와 관련된 처리
         */
                
        // 전체 게시글 갯수 가져오기
        int totalRecord = boardsrv.getArticleTotalRecord(boardcd, searchWord);
        
        // 페이지 처리
        PagingHelper paging = new PagingHelper(totalRecord, curPage);        
        int start = paging.getStartRecord();
        int end   = paging.getEndRecord();
        
        List<ModelArticle> list = boardsrv.getArticleList(boardcd, searchWord, start, end);
        model.addAttribute("list"          , list                       );
        model.addAttribute("no"            , paging.getListNo        () );
        model.addAttribute("totalFirstPage", paging.getTotalFirstPage() );
        model.addAttribute("prevLink"      , paging.getPrevLink      () );
        model.addAttribute("pageLinks"     , paging.getPageLinks     () );
        model.addAttribute("nextLink"      , paging.getNextLink      () );
        model.addAttribute("totalLastPage" , paging.getTotalLastPage () );  
        


        model.addAttribute("articleno"      , articleno );
        model.addAttribute("boardcd"        , boardcd   );
        model.addAttribute("curPage"        , curPage   );
        model.addAttribute("searchWord"     , searchWord);
                
		return "board/articleview";
	}
	

    /**
     * http://localhost/board/articlewirte/qna
     */
    @RequestMapping(value = "/board/articlewrite/{boardcd}", method = RequestMethod.GET)
    public String articlewrite( Model model 
            , @PathVariable(value="boardcd")  String boardcd
            , @RequestParam(value="curPage"   , defaultValue="1") Integer curPage
            , @RequestParam(value="searchWord", defaultValue="" ) String  searchWord ) {
        logger.info("/board/articlewrite : GET");
        
        String boardnm = boardsrv.getBoardName(boardcd);      
        // articleno 존재하지 않음.
        
        model.addAttribute("boardnm"   , boardnm);
        model.addAttribute("boardcd"   , boardcd);
        model.addAttribute("curPage"   , curPage);
        model.addAttribute("searchWord", searchWord);        

        return "board/articlewrite";
    }
    
    /**
     * http://localhost/board/articlewirte/qna
     */
    @RequestMapping(value = "/board/articlewrite", method = RequestMethod.POST)
    public String articlewrite( Model model 
            , @ModelAttribute ModelArticle article
            , MultipartFile uploadfile ) throws IllegalStateException, IOException  {
        logger.info("/board/articlewrite : POST");
    
        // 1. article 테이블에 insert.
        int articleno = boardsrv.insertArticle(article); // articleno 는 inserted 된 pk값 
        
        // 2. 로컬 첨부 파일을 서버로 올리기 위한 코드
        if( !uploadfile.getOriginalFilename().isEmpty() ){
            
            // UPLOAD_PATH 존재 여부 체크. 없으면 폴더 생성.
            java.io.File uploadDir = new java.io.File( WebConstants.UPLOAD_PATH  );
            if( !uploadDir.isDirectory() ) 
                uploadDir.mkdir();            

            // 클라이언트의 첨부 파일을 서버로 복사
            String fileName = uploadfile.getOriginalFilename();
            String tempFileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String filepath =  WebConstants.UPLOAD_PATH + tempFileName;                 
            java.io.File f = new java.io.File( filepath );                
            uploadfile.transferTo( f );       
                           
            // 첨부 파일을 attachfiel 테이블에 insert.
            ModelAttachfile attachfile = new ModelAttachfile();
            attachfile.setFilename    ( fileName    );  // 실제파일명
            attachfile.setTempfilename( f.getName() );  // 임시파일명
            attachfile.setFiletype( FilenameUtils.getExtension(fileName) ); //확장자
            attachfile.setFilesize( f.length() );
            attachfile.setArticleno( articleno );
            int result = boardsrv.insertAttachFile( attachfile );     
            
            // TODO: make an error. 
        }

        if( articleno > 0 ){
            // /board/boardlist 리다이렉트 
            return "redirect:/board/articlelist/"+ article.getBoardcd(); 
        }
        else {       
            // /board/boardview 리다이렉트 
            return "redirect:/board/articlewrite/"+ article.getBoardcd();            
        }
    }

    /**
     * http://localhost/board/articlemodify/qna
     */
    @RequestMapping(value = "/board/articlemodify/{boardcd}/{articleno}", method = RequestMethod.GET)
    public String articlemodify( Model model 
            , @PathVariable(value="boardcd"  )  String boardcd
            , @PathVariable(value="articleno")  Integer articleno
            , @RequestParam(value="curPage"   , defaultValue="1") Integer curPage
            , @RequestParam(value="searchWord", defaultValue="" ) String  searchWord ) {
        logger.info("/board/articlewrite : GET");
        
        String boardnm = boardsrv.getBoardName(boardcd);    
        // attachFileList
        List<ModelAttachfile> attachFileList = boardsrv.getAttachFileList(articleno);
        model.addAttribute("attachFileList", attachFileList);
        
        ModelArticle thisArticle = boardsrv.getArticle(articleno);
        model.addAttribute("thisArticle", thisArticle);   
        
        model.addAttribute("boardnm"    , boardnm);
        model.addAttribute("boardcd"    , boardcd);
        model.addAttribute("articleno"  , articleno);
        model.addAttribute("curPage"    , curPage);
        model.addAttribute("searchWord" , searchWord);       

        return "board/articlemodify";
    }
    
    
    /**
     * http://localhost/board/attachfiledelete
     */
    @RequestMapping(value = "/board/commentadd", method = RequestMethod.POST)
    public String commentadd( Model model
            , @RequestParam(value="articleno" , defaultValue="-1") Integer articleno 
            , @RequestParam(value="memo"      , defaultValue=""  ) String  memo )  {
        logger.info("/board/commentadd : POST");
        
        ModelComments comment = new ModelComments();
        comment.setArticleno(articleno);
    
        comment.setMemo(memo);
        
        int commentno = boardsrv.insertComment(comment);
        
        comment = boardsrv.getComment(commentno);
        model.addAttribute("comment", comment);
        
        return "board/articleview-commentlistbody" ;
    }

/**
     * http://localhost/board/attachfiledelete
     */
    @RequestMapping(value = "/board/attachfiledelete", method = RequestMethod.POST)
    @ResponseBody
    public int attachfiledelete( Model model
            , @RequestParam(value="attachfileno" , defaultValue="-1") Integer attachfileno )  {
        logger.info("/board/attachfiledelete : POST");
        
	    ModelAttachfile attachFile = new ModelAttachfile();
	    attachFile.setAttachfileno(attachfileno);
        int result = boardsrv.deleteAttachFile(attachFile);
        
        return result ;
    }
    @RequestMapping(value = "/board/articledelete", method = RequestMethod.POST)
    public String articledelete( Model model 
            , @RequestParam(value="boardcd",  defaultValue="" )  String boardcd
            , @RequestParam(value="articleno", defaultValue="")  Integer articleno
            , @RequestParam(value="curPage"   , defaultValue="1") Integer curPage
            , @RequestParam(value="searchWord", defaultValue="" ) String  searchWord ) {
        logger.info("/board/articledelete : GET");
        
        // articledelte 시,
        // 1. 관련된 comment 삭제
        // 2. 관련된 attachfile 삭제
        // 3. 관련된 첨부 파일 삭제
        // 4. article 삭제
        
        boardsrv.transDeleteArticle(boardcd, articleno);
        
        
        return "redirect:/board/articlelist/"+ boardcd;
    }
    
    @RequestMapping(value = "/board/aticlegoodbad", method = RequestMethod.POST)
    @ResponseBody
    public int aticlegoodbad( Model model
            , HttpSession sessioon
            , @RequestParam(value="codegb" , defaultValue="") String codegb
            , @RequestParam(value="updown" , defaultValue="false") Boolean updown
            , @RequestParam(value="articleno" , defaultValue="") Integer articleno)  {
        logger.info("/board/aticlegoodbad : POST");
        
        // 로그인이 안된 경우에 대한 처리
        
        if(sessioon.getAttribute(WebConstants.SESSION_NAME) != null)
            return -1;
        
        //
        ModelUser user = (ModelUser)sessioon.getAttribute("user");
        String userid = user.getUserid();
        
        ModelArticleRecommend recommend = null;
        
        recommend = new ModelArticleRecommend();
        recommend.setArticleno(articleno);
        recommend.setUserid(userid);
        
        /*  1 --> 좋아요(good) ->up */
        int result = boardsrv.transRecommendArticle(articleno
                ,userid
                ,EnumGoodBad.valueOf(codegb.toUpperCase())
                ,updown);
        
        
        return result ;
    }
    
    
    
    
    
}
