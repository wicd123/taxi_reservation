<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <meta charset="utf-8" /> 
    <meta name="Keywords" content="새글쓰기" />

    <title>새글쓰기</title>

    <link rel="stylesheet" href="/resources/css/screen.css" type="text/css" media="screen" />
    <style>
        #content { min-height: 800px; }
        p { margin: 0; padding: 0; }
        form div {text-align: left; padding-bottom: 15px; }
    </style>    
    
    <script src="/resources/js/jquery/jquery-3.1.1.js"></script>
    <script src="/resources/js/ajaxsetup.js"></script>
    <script>    
        $(document).ready( function(){

            $('#writeForm').submit( function(event){     
                //TODO 유효성 검사 
                
                return true;
            }); 

            $('#btnView').click( function(event){            
                window.location='/board/boardview';
                return false;
            });

            $('#btnList').click( function(event){            
                window.location='/board/boardlist';
                return false;
            });
        });
    </script>    
</head>
<body>

    <div id="wrap">
    

        <div id="header">
            <%@ include file="../inc/header.jsp" %>
        </div>
    
        <div id="main-menu">
            <%@ include file="../inc/main-menu.jsp" %>
        </div> 
        
    
    
        <div id="container">
            <div id="content" >

                <!-- 본문 시작 -->
                <div id="bbs">
                    <h2>글쓰기</h2>
                    <form id="writeForm" action="/board/boardwrite" method="post" enctype="application/x-www-form-urlencoded" >
                        <div>                        
                            <p> Borad Code :
                                <input type="text" id="boardcd"  name="boardcd" />
                            </p>
                        </div>
                        
                        <div>
                            <p> Board Name : 
                                <input type="text" id="boardnm" name="boardnm"  />
                            </p>
                        </div>
                        
                        <div>
                            <p> Use YN : 
                                <input type="checkbox" id="UseYN" name="UseYN"  />
                            </p>
                        </div>

                        <div>
                            <input id="btnSubmit" type="submit" value="전송" /> 
                            <input id="btnView"   type="button" value="상세보기" /> 
                            <input id="btnList"   type="button" value="목록" />
                            <input id="btnReset"  type="reset" value="취소" />
                        </div>
                    </form>
                </div> <!--  본문 끝 -->

            </div> <!-- content 끝 -->
            
        </div> <!--  container 끝 -->


        <div id="sidebar">
            <%@ include file="bbs-menu.jsp" %>
        </div>
        
        <div id="extra">
            <%@ include file="../inc/extra.jsp" %>
        </div>
    
        <div id="footer">
            <%@ include file="../inc/footer.jsp" %>
        </div> 

    </div>

</body>
</html>
