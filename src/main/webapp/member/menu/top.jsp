<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="web.tool.*" %>
<%
String root = request.getContextPath();
%>
<header>
   <DIV style='float:right;'>
            <ul class="member-list">
               <% if(session.getAttribute("userid") == null) { // 회원 로그인 여부 검사 %>
               <li class="login"><a href="<%=root %>/member/login.do"><span></span>LOGIN</a></li>
               <li class="join"><a href="<%=root %>/member/create.do"><span></span>JOIN</a></li>
               <% } else { %>
               <li class="logout"><a href="<%=root %>/member/logout.do">${userid }님 로그아웃</a></li>
               <% } %>   
               <li class="mytm"><a href="#"><span></span>MY토니모리</a></li>
               <li class="orderDelivery"><a href="#"><span></span>주문/배송</a></li>
               <li class="cart"><a href="#"><span></span>장바구니</a></li>
               <!--
               <li class="cs"><a href="#none" class="on"><span></span>고객센터</a></li><!-- //활성화 시 class on 추가 -->
               <li class="cs"><a href="#"><span></span>고객센터</a></li><!-- //활성화 시 class on 추가 -->
               <%
                String act = (String) session.getAttribute("act");
                if ((Tool.checkNull(act)).equals("M")){
                %>
                  <li><a class='menu_link' href='<%=root %>/member/list.do'><span></span>관리자다</a></li>
                <%
                }else{ }
                %>
            </ul>
            <div style='clear:both;'></div>
      
   </DIV>
            <div style='clear:both;'></div>
</header>