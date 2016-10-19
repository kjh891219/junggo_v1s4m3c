<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/tool.js"></script>

<script type="text/javascript">

</script>

</head> 

<body>
  <div class="container">
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />
     

    
  
    <DIV class = 'content'>
      <FORM name='frm' method='POST' action='./delete.do'
                 onsubmit = 'return send();'>
        <input type='hidden' name='ctno' value='${ctno}'>
       
    
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
  </div>
</body>

</html> 
