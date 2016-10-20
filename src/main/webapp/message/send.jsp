<%@ page contentType="text/html; charset=UTF-8" %>
 
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

<body>
<DIV class='title'>쪽지 보내기</DIV>
 
<DIV class='content'>
<FORM name='frm' method='POST' action='./create.do'
           onsubmit = 'return send()'>
  <fieldset>
    <ul>
      <li>
        <label class='label' for='receiveid'>받는 사람</label>
        <input type='text' name='receiveid' id='receiveid' value='receiveid' required="required">
      </li>
      <li>
        <label class='label' for='title'>제목</label>
        <input type='text' name='title' id='title' value='title' required="required">
      </li>
      <li>
        <label class='label' for='content'>내용</label> <br>
        <textarea name='content' id='content' value='content'></textarea> 
      </li>
      <li class='right'>
        <button type="submit">전송</button>
      </li>         
    </ul>
  </fieldset>
</FORM>
</DIV>
</body> 

</html> 
 