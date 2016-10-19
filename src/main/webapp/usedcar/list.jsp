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
 <form name="frmSearch" method="get" action="./list.do"> 
    <div class='content_menu' style='width: 100%;'>
 <A href='../usedcar/list.do'>중고차 목록</A>>
 <A href='./create.do?'>등록</A>｜
 <A href="javascript:location.reload();">새로고침</A>
      <select name="col"> 
        <option value="">선택</option> 
        <option value="title" ${searchDTO.col == "title" ? "selected=selected" : "" }>제목</option> 
        <option value="category" ${searchDTO.col == "category" ? "selected=selected" : "" }>카테고리</option> 
        <option value="nickname" ${searchDTO.col == "nickname" ? "selected=selected" : "" }>닉네임</option> 
        <option value="total" ${searchDTO.col == "" ? "selected=selected" : "" }>전체 목록</option>
      </select>
      <c:choose>
        <c:when test="${searchDTO.col != 'total' }"> <!-- 검색 상태 -->
          <input type="text" name="word" size="15" value="${searchDTO.word }">
        </c:when>
        <c:when test="${searchDTO.col == 'total' }"> <!-- 전체 레코드 -->
          <input type="text" name="word" size="15" value="">
        </c:when>
      </c:choose>
     
      <input type="submit" value="검색">
    </div>
  </form> 
 
<DIV id='panel_frm' class='content' style='padding: 10px 0px 10px 0px; background-color: #DDDDDD; width: 70%; text-align: center;'>
<FORM name='frm' id='frm' method='POST' >
  
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
    <col style='width: 5%;'/>
    <col style='width: 10%;'/>
    <col style='width: 5%;'/>
    <col style='width: 5%;'/>
    <col style='width: 10%;'/>
  </colgroup>
  <TR>
    <TH class='th'>번호</TH>
    <TH class='th'>제목</TH>
    <TH class='th'>카테고리</TH>
    <TH class='th'>거래구분</TH>
    <TH class='th'>거래지역</TH>
    <TH class='th'>거래방식</TH>
    <TH class='th'>희망가격</TH>
    <TH class='th'>닉네임</TH>
    <TH class='th'>상품코드</TH>
    <TH class='th'>조회수</TH>
    <TH class='th'>글 등록일</TH>

  </TR>
  
  <c:forEach var="vo" items="${list}">
  <TR>
    <TD class='td'>${vo.u_no}</TD>
    <TD class='td'>
    <a href="./read.do?u_no=${vo.u_no}">${vo.title}</a>
    </TD>
    <TD class='td'>${vo.category}</TD>
    <TD class='td'>${vo.deal_code}</TD>
    <TD class='td'>${vo.region}</TD>
    <TD class='td'>${vo.deal_way}</TD>
    <TD class='td'>${vo.h_price}</TD>
    <TD class='td'>${vo.nickname}</TD>
    <TD class='td'>${vo.product_code}</TD>
    <TD class='td'>${vo.u_cnt}</TD>
    <TD class='td'>${vo.wdate}</TD>

    
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