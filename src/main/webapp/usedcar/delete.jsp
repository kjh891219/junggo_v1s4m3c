<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.usedcar.UsedcarVO" %>

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


</script>
 
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
 
    <div class='content_menu' style='width: 100%;'>
    <A href='../usedcar/list.do'>게시판 목록</A> > 
    </div>
  
    <DIV class = 'content'>
      <FORM name='frm' method='POST' action='./delete.do'
                 onsubmit = 'return send();'>
        <input type='hidden' name='u_no' value='${u_no}'>
    
        <div class="form-group">   
          <div class="col-xs-12 col-lg-12" style='text-align: center; margin: 30px;'>
            삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.<br>
            <button type = "submit">삭제 진행</button>
            <button type = "button" onclick = "history.back()">취소</button>
          </div>
        </div>   
      </FORM>
    </DIV>
 
     <jsp:include page="/menu/bottom.jsp" flush='false' />     

</body>
 
</html> 