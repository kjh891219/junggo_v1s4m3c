<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript">
$(function(){
 
});
</script>
</head> 
<!-- ----------------------------------------- -->
<body>
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<!-- ----------------------------------------- -->

<DIV class='title'>문의게시판</DIV>

<TABLE class='table'>
<%--   <caption>문의게시판</caption> --%>
  <colgroup>
    <col style='width: 5%;'/>
    <col style='width: 15%;'/>
    <col style='width: 30%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
  </colgroup>
  <TR>
    <TH class='th'>번호</TH>
    <TH class='th'>분류</TH>
    <TH class='th'>제목</TH>
    <TH class='th'>작성자</TH>
    <TH class='th'>작성일</TH>
    <TH class='th'>관리</TH>
<!--     <TH class='th'>등록일</TH> -->
  </TR>
 
   <c:forEach var="vo" items="${list }">
  <TR>
    <TD class='td'>${vo.qnano}</TD>
    <c:choose>
      <c:when test="${vo.categoryno == '1'}">
      <TD class='td'>회원가입 및 로그인</TD>
      </c:when>
      <c:when test="${vo.categoryno == '2'}">
      <TD class='td'>배송문의</TD>
      </c:when>
      <c:when test="${vo.categoryno == '3'}">
      <TD class='td'>기타문의</TD>
      </c:when>
    </c:choose>
    <TD class='td'><A href="./read.do?mno=${vo.qnano}">${vo.title }</A></TD>
    <TD class='td'>${vo.writer }</TD>
    <TD class='td'>${vo.qdate.substring(0,10)}</TD>
    <TD class='td'>
      <A href="./passwd.do?mno=${vo.qnano}"><IMG src='../menu/images/passwd.ico' title='패스워드 변경'></A>
      <A href="./read.do?mno=${vo.qnano}"><IMG src='../menu/images/update.png' width="16px" title='수정'></A>
      <A href="./delete.do?mno=${vo.qnano}"><IMG src='../menu/images/delete.png' width="16px" title='삭제'></A>
    </TD>
     
  </TR>
 </c:forEach>
</TABLE>
 
<DIV class='bottom'>
  <button type='button' onclick="location.href='./create.do'">등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>
<!-- -------------------------------------------- -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</body>
<!-- -------------------------------------------- -->
</html> 