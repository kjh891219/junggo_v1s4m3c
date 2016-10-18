<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Enumeration" %>
<%@page import="java.net.*"%>
<%@ page import="web.tool.Tool" %>

<%
   // ----------------------------------------------------------------------------
 // 요청된 주소로 자동 이동
 // ----------------------------------------------------------------------------
 // String url= request.getRequestURL().toString();
 // System.out.println("--> url: " + url);
 // --> url: http://localhost:9090/artbox_v1jq/noticev3/update_form.jsp

 Enumeration<String> enumeration = request.getParameterNames();
 String params = "";

 while(enumeration.hasMoreElements()){
   // System.out.println("-->" + enumeration.nextElement());
   String name = enumeration.nextElement();
   String value = request.getParameter(name);
   params = params + name + "=" + value + "&";
   // System.out.println("--> params: " + params);
 }

   String url_address = request.getRequestURL() + "?" + params;
   System.out.println("--> 1) auth.jsp: url_address: " + url_address);
   url_address = URLEncoder.encode(url_address, "UTF-8");
   System.out.println("--> 2) auth.jsp: url_address: " + url_address);
   /* 
   --> 1) auth.jsp: url_address: http://localhost:9090/admin_v1jq/admin1/update_form.jsp?admin1no=1&email=test1@mail.com&
--> 2) auth.jsp: url_address: http%3A%2F%2Flocalhost%3A9090%2Fadmin_v1jq%2Fadmin1%2Fupdate_form.jsp%3Fadmin1no%3D1%26email%3Dtest1%40mail.com%26
   */
   //----------------------------------------------------------------------------

   boolean sw = Tool.isMaster(request) || Tool.isAdmin(request);
   // System.out.println("--> 인증: " + sw);
   if (sw == false) {
     String root = request.getContextPath();
     response.sendRedirect(root + "/member/login_ck_form.jsp?url_address="
         + url_address);
     return;
   }
 %>