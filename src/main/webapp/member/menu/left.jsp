<%@ page contentType="text/html; charset=UTF-8" %>
<%
String root = request.getContextPath();
%>

<DIV id="main_left">
   <div id="main_left_left">
   <div id='logo'><img src="./images/logo_1.png"></div>
   <nav class='left_list_form'>
   <!-- <ul>카테고리</ul> -->
   <UL>
      <li class='left_list'><a href='<%=root %>/member/checkPwd.do?mno=${mno}&flag=1'>정보 수정</a></li>
      <li class='left_list'><a href='<%=root %>/member/checkPwd.do?mno=${mno}&flag=2'>회원 탈퇴</a></li>
      <li class='left_list'><a href='#'>내가 쓴 글</a></li>
   </UL>
   </nav>
   </div>
   <DIV id="main_left_detail">
      <div style=""></div>
   </DIV>
</DIV>