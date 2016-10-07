<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 
<fieldset style='width: 20%; margin: 0px auto; border-left: none; border-right: none;'>
  <legend>카테고리</legend>
  <UL>
    <c:forEach var="vo" items="${list}">
      <LI>${vo.sort }</LI>
    </c:forEach>
  </UL>
</fieldset>
<br>