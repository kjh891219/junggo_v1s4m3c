<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
 
<DIV class='title'>본인 인증</DIV>
<DIV class='title'>비밀번호 확인</DIV>
 
<DIV class='content' style='width: 60%;'>
<FORM name='frm' method='POST' action='./checkPwd.do'
           onsubmit = 'return send();'>
  <input type='hidden' name='mno' value='${mno }'>         
  <input type='hidden' name='userid' value='${userid }'>         
  <input type='hidden' name='flag' value='${flag }'> 
  <fieldset>
    <ul>
      <li>
        <label class='label' for='pwd' style='width: 200px;'>현재 패스워드</label>
        <input type='password' name='pwd' id='pwd' value='1234' required="required">
      </li>  
      <li class='right'>
        <button type="submit">확인</button>
      </li>         
    </ul>
  </fieldset>
</FORM>
</DIV>
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 