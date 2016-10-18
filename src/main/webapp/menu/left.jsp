<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<DIV id="main_left">
   <div id="main_left_left">
   <div id='logo'><img src="${pageContext.request.contextPath}/images/logo_1.png"></div>
   <nav class='left_list_form'>
   <!-- <ul>카테고리</ul> -->
   <UL>
      <li class='left_list'><a href='#'>중고차</a></li>
      <li class='left_list'><a href='#'>의류</a></li>
      <li class='left_list'><a href='#'>컴퓨터</a></li>
      <li class='left_list'><a href='#'>게임</a></li>
      <li class='left_list'><a href='#'>핸드폰</a></li>
      <li class='left_list'><a href='#'>생활용품</a></li>
      <li class='left_list'><a href='#'>스포츠</a></li>
      <li class='left_list'><a href='${pageContext.request.contextPath}/camera/list.do'>카메라</a></li>
   </UL>
   </nav>
   </div>
   <DIV id="main_left_detail">
      <div style=""></div>
   </DIV>
</DIV>