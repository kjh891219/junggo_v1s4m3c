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
	  $('#panel_frm').hide();
	  $('#panel_frm_delete').hide();
	});
	
	
function create(){
	  $('#panel_frm').show();
	  $('#frm').attr('action', './create.do');
	  $('#submit').html('등록');
	}
	 
	function create_cancel(){
	  $('#panel_frm').hide();
	}

</script>
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
 
 
<TABLE class='table' style='width: 70%;'>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 18%;'/>
    <col style='width: 22%;'/>
    <col style='width: 10%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
  </colgroup>
  <TR>
    <TH class='th'>글번호</TH>
    <TH class='th'>상품구분</TH>
    <TH class='th'>글제목</TH>
    <TH class='th'>가격</TH>
    <TH class='th'>거래지역</TH>
    <TH class='th'>거래방식</TH>
    <TH class='th'>글쓴이</TH>
  </TR>
 
  <c:forEach var="vo" items="${list }">
  <TR>
    <TD class='td'>${vo.bno}</TD>
    <TD class='td'>${vo.category}</TD>
    <TD class='td'>${vo.title}</TD>
    <TD class='td'>${vo.price}</TD>
    <TD class='td'>${vo.deal_region}</TD>
    <TD class='td'>${vo.deal_way}</TD>
    <TD class='td'>${vo.writer}</TD>
    
  </TR>
  </c:forEach>
 
</TABLE>
 
<DIV class='bottom'>
  <button type='button' onclick="location.href='./create.do';" >등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
 