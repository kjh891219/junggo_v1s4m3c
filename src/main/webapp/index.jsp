<%@ page contentType="text/html; charset=UTF-8" %> 
<% request.setCharacterEncoding("UTF-8"); %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
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

<body leftmargin="0" topmargin="0">

   <jsp:include page="/menu/top.jsp" flush='false' />
   <jsp:include page="/menu/left.jsp" flush='false' />
   
<div class="container">
   <!-- 본문 내용 -->
   <table class="table table-hover" style='width: 100%;'>
    <thead>
      <colgroup>
        <col style="width: 7%;"></col>
        <col style="width: 30%;"></col>
        <col style="width: 8%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 7%;"></col>
        <col style="width: 8%;"></col>
      </colgroup>
          
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>추천</th>
          <th>등록일</th>
          <th>파일</th>
          <th>업로드 파일</th>
          <th>댓글</th>
          <th>기타</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
      
        <c:forEach var="vo" items="${list }">
          <tr>
            <td>${vo.blogno}</td>
            <td>
              <a href="./read.do?blogno=${vo.blogno}">${vo.title}</a> 
            </td> 
            <td>${vo.good}</td>
            <td>${vo.rdate}</td>
            <td>${vo.file1}</td>
            <td>${vo.file2}</td>
            <td>${vo.replycnt}</td>
            <td>
            </td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    
    <br><br>
   
   <div style='height:5000px;'></div>
   
</div>
   <jsp:include page="/menu/bottom.jsp" flush='false' />
   

 
</body>
</html> 
 
