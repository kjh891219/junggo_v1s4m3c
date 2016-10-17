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
$(function(){
  $('#panel_frm').hide();
});
 
function create(){
  $('#panel_frm').show();
  $('#frm').attr('action', './create.do');
  $('#sort').val('');
  $('#submit').html('등록');
  $('#sort').focus();
}
 
function create_cancel(){
  $('#panel_frm').hide();
}

function update(codeno, sort, seqno){
  $('#panel_frm').show();
  $('#frm').attr('action', './update.do');
  // $('#codeno').val(codeno); // Chrome Elements에 변경이 안됨
  $('#codeno').attr('value', codeno);
  // $('#sort').val(sort);          // Chrome Elements에 변경이 안됨
  $('#sort').attr('value', sort);
  // $('#seqno').val(seqno); // Chrome Elements에 변경이 안됨
  $('#seqno').attr('value', seqno);
  $('#submit').html('저장');
  $('#sort').focus();
}


</script>
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->

<DIV class='title'>중고차 목록</DIV>
 
<DIV id='panel_frm' class='content' style='padding: 10px 0px 10px 0px; background-color: #DDDDDD; width: 70%; text-align: center;'>
<FORM name='frm' id='frm' method='POST' >
  <input type='hidden' name='u_no' id='u_no' value='0'> 
  
  <label for=nickname>닉네임</label>
  <input type='text' name='nickname' id='nickname' size='20' value='아이디' required="required">
 
  <label for='category'>카테고리</label>
  <input type='text' name='category' id='category' value='A' required="required">

  <label for='deal_code'>거래구분</label>
  <input type='text' name='deal_code' id='deal_code' value='A' required="required">
   
  <button type="submit" id='submit'>등록</button>
  <button type="button" onclick="create_cancel()">취소</button>
</FORM>
</DIV>
 
<TABLE class='table' style='width: 100%;'>
  <colgroup>
    <col style='width: 5%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 5%;'/>
    <col style='width: 5%;'/>
    <col style='width: 5%;'/>
  </colgroup>
  <TR>
    <TH class='th'>번호</TH>
    <TH class='th'>제목</TH>
    <TH class='th'>카테고리</TH>
    <TH class='th'>거래구분</TH>
    <TH class='th'>거래지역</TH>
    <TH class='th'>거래방식</TH>
    <TH class='th'>희망가격</TH>
    <TH class='th'>아이디</TH>
    <TH class='th'>거래완료여부</TH>
    <TH class='th'>조회수</TH>
    <TH class='th'>기타</TH>
  </TR>
  
  <c:forEach var="vo" items="${list}">
  <TR>
    <TD class='td'>${vo.u_no}</TD>
    <TD class='td'>${vo.title}</TD>
    <TD class='td'>${vo.category}</TD>
    <TD class='td'>${vo.deal_code}</TD>
    <TD class='td'>${vo.region}</TD>
    <TD class='td'>${vo.deal_way}</TD>
    <TD class='td'>${vo.h_price}</TD>
    <TD class='td'>${vo.nickname}</TD>
    <TD class='td'>${vo.product_code}</TD>
    <TD class='td'>${vo.u_cnt}</TD>
    <TD class='td'>
      <A href="javascript:update( '${vo.u_no}' , ' ${vo.title}', ' ${vo.category}', ' ${vo.deal_code}', '${vo.region}' ,' ${vo.deal_way}', ' ${vo.h_price}' , ' ${vo.email}')"><IMG src='./images/update.png' title='수정'></A>
      <A href="javascript:deleteOne(${vo.u_no})"><IMG src='./images/delete.png' title='삭제'></A>

    </TD>
    
  </TR>
  </c:forEach>
  


</TABLE>
 
<DIV class='bottom'>
  <button type='button' onclick="location.href='./create.do'">등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 