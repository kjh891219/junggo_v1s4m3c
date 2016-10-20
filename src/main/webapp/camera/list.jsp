<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title> 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link href="./css/style.css" rel="Stylesheet" type="text/css">
<script>
$(document).ready(function(){
   $('.left_list').mouseenter(function(){
     $("#main_left_detail").show();
     /* Toggle('on'); */
   })
   $('#main_left_detail').mouseleave(function(){
     $("#main_left_detail").hide();
   });
   
});
</script>
<style type="text/css">

/* 전체 스타일 */
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
  *{ 
    font-family: 'Nanum Gothic', serif;
    font-size: 15px;
    margin: 0px;
    padding: 0px;  
  }
  
  a{
   color:white;
  }
  
/* left를 제외한 스타일 */
  body{
   width:80%;
   margin-left:130px;
  }
  
/* top 스타일 */
  
  header{ 
    height: 35px; 
    background-color: #e6e6e6; 
    font-family: 맑은 고딕;  
    text-align: center;
  }
  .member-list {
    margin:5px 8px 0 0;
  
  }
  
 .member-list li {
    float:left;
    list-style: none;
    padding-left:8px;
  }
 .member-list li a {
    font-size:12px;
  }

/* left */  

   /* 로고 */
   #logo {
      width:70px;
      margin:20px auto;
   }
   #logo img {
      width:70px;
   }
   
  #main_left {
    position:fixed; 
    top:0;
    left:0;
  }
  
  #main_left_left{
    width:130px; 
    height:100%;
    float:left;
    color:white;
    background-color: #737373;
  }
  
   #main_left_detail{
      display:none;
      position:absolute;
      left:130px;
      width:130px;
      height:100%;
      
      background-color:#575757;
   }
  
  .left_list_form {
    padding:10px;
  }
  
  .left_list{
    padding-bottom:8px;
  }

/* index 안에 있는 태그 스타일 */

   .container{
      width:100%;
   }
   
   nav ul li {
      list-style:none;
      margin-left: 20px;
   }
   nav {
      margin-top:30px;
   }
   footer{
      text-align: center;
   }
 
 
</style>
</head> 
 <div class="container">
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />

<<<<<<< HEAD

  <form name="frmSearch" method="get" action="./list.do"> 
    <div class='content_menu' style='width: 100%;'>
      <select name="col"> 
        <option value="">선택</option> 
        <option value="category" ${searchDTO.col == "category" ? "selected=selected" : "" }>카테고리</option>
        <option value="title" ${searchDTO.col == "title" ? "selected=selected" : "" }>제목</option> 
        <option value="content" ${searchDTO.col == "content" ? "selected=selected" : "" }>내용</option> 
        <option value="title_content" ${searchDTO.col == "title_content" ? "selected=selected" : "" }>제목+내용</option> 
        <option value="total" ${searchDTO.col == "" ? "selected=selected" : "" }>전체 목록</option>
      </select>
      <c:choose>
        <c:when test="${searchDTO.col != 'total' }"> <!-- 검색 상태 -->
          <input type="text" name="word" size="15" value="${searchDTO.word }">
        </c:when>
        <c:when test="${searchDTO.col == 'total' }"> <!-- 전체 레코드 -->
          <input type="text" name="word" size="15" value="">
        </c:when>
      </c:choose>
     
      <input type="submit" value="검색">
    </div>
  </form> 



<div class="container" style="margin: auto;">
 <table class="table table-hover" style='width: 100%;'>
    <thead>
    <TR>
    <TH>글번호</TH>
    <TH>거래구분</TH>
    <TH>제목</TH>
    <TH>희망가격</TH>
    <TH>거래방식</TH>
    <TH>지역</TH>
    <TH>닉네임</TH>
    <TH>이미지</TH>
    <TH>날짜</TH>
    <TH>조회수</TH>  
  </TR>
  </thead>
 
<tbody>
<c:forEach var="vo" items="${list }">
  <TR>
    <TD>${vo.ctno}</TD>
    <TD>${vo.deal_code }</TD>
    <TD style="color: black;"><A href="./read.do?ctno=${vo.ctno}" style="color: black;">${vo.title}</A></TD>
    <TD>${vo.hprice}</TD>
    <TD>${vo.deal_way}</TD>
    <TD>${vo.region}</TD>
    <TD>${vo.nickname}</TD>
     <td>
            <c:choose>
              <c:when test="${vo.file1 == null}"></c:when>
              <c:when test="${vo.file1 != null}">
                <c:set var='file2' value="${fn:toLowerCase(vo.file1)}" />
                <c:choose>
                  <c:when test="${fn:endsWith(file2, '.jpg')}">
                    <IMG id='file2' src='./storage/${vo.file1}' >
                  </c:when>
                  <c:when test="${fn:endsWith(file2, '.gif')}">
                    <IMG id='file2'  src='./storage/${vo.file1}' >
                  </c:when>
                  <c:when test="${fn:endsWith(file2, '.png')}">
                    <IMG id='file2'  src='./storage/${vo.file1}' >
                  </c:when>
                  <c:otherwise>
                    ${vo.file1}
                  </c:otherwise>
                </c:choose>
                </c:when>
            </c:choose>
            </td>
     <TD>${fn:substring(vo.wdate, 0, 10) }</TD>
     <TD>${vo.cnt}</TD>
  </TR>
</c:forEach>
</tbody>  
</TABLE>

</div>
<div style="text-align: center;">
<button type='button' onclick="location.href='./create.do'" class="btn btn-success btn-lg">등록</button>
<button type='button' onclick="location.reload();" class="btn btn-danger btn-lg">새로 고침</button>
</div>
</div>
<DIV class='bottom'>${paging}</DIV>
=======
<div class="container" style="margin: auto;">
 <table class="table table-hover" style='width: 100%;'>
    <thead>
    <TR>
    <TH>글번호</TH>
    <TH>거래구분</TH>
    <TH>제목</TH>
    <TH>희망가격</TH>
    <TH>거래방식</TH>
    <TH>지역</TH>
    <TH>닉네임</TH>  
  </TR>
  </thead>
 
<tbody>
<c:forEach var="vo" items="${list }">
  <TR>
    <TD>${vo.ctno}</TD>
    <TD>${vo.deal_code }</TD>
    <TD style="color: black;"><A href="./read.do?ctno=${vo.ctno}" style="color: black;">${vo.title}</A></TD>
    <TD>${vo.hprice}</TD>
    <TD>${vo.deal_way}</TD>
    <TD>${vo.region}</TD>
    <TD>${vo.nickname}</TD>
  </TR>
</c:forEach>
</tbody>  
</TABLE>

</div>
<div style="text-align: center;">
<button type='button' onclick="location.href='./create.do'" class="btn btn-success btn-lg">등록</button>
<button type='button' onclick="location.reload();" class="btn btn-danger btn-lg">새로 고침</button>
</div>
</div>

>>>>>>> branch 'master' of https://github.com/kjh891219/junggo_v1s4m3c.git

  </div>
<jsp:include page="/menu/bottom.jsp" flush='false' />     

</body>
<!-- -------------------------------------------- -->
</html> 