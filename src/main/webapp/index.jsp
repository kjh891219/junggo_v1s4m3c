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
 
