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
$(function(){

});
</script>

</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<!-- ----------------------------------------- -->

<DIV class='title'>물품 등록</DIV>

<DIV class='content' >
<FORM name='frm' method='POST' action='./create.do'>
  <fieldset>
    <ul>
      <li>
        <label class='label' for='category'>분류</label>
           <select name='category' id='category'>
            <option value="여성화장품" selected="selected">여성화장품</option>
            <option value="남성화장품">남성화장품</option>
            <option value="메이크업/클렌징">메이크업/클렌징</option>
            <option value="향수/아로마">향수/아로마</option>
            <option value="헤어/바디케어">헤어/바디케어</option>
            <option value="기타미용제품">기타미용제품</option>
           </select>
      </li>
      <li> 
        <label class='label' for='writer'>글쓴이</label>
        <input type='text' name='writer' id='writer' value='글쓴이' required="required">
        <label  class='label' for='passwd'>비밀번호</label>
        <input type='password' name='passwd' id='passwd' value='1234' required="required">
      </li>
      <li>  
        <label class='label' for='deal_sort' >거래 구분</label>
        <select name='deal_sort' id='deal_sort'>
            <option value="팝니다" selected="selected">팝니다</option>
            <option value="삽니다">삽니다</option>
            <option value="교환">교환</option>
            <option value="무료드림">무료드림</option>
            <option value="판매완료">판매완료</option>
            <option value="구매완료">구매완료</option>
        </select>        
      </li>   
      <li>  
        <label class='label' for='product_sort'>상품 구분</label>
        <select name='product_sort' id='product_sort'>
            <option value="old_product" selected="selected">중고품</option>
            <option value="new_product">신상품</option>
        </select>        
      </li>
     
      <li>  
        <label class='label' for='deal_region'>거래 지역</label>
        <select name='deal_region' id='deal_region'>
           <option value="서울" selected="selected">서울</option>
           <option value="인천">인천</option>
           <option value="대구">대구</option>
           <option value="대전">대전</option>
           <option value="광주">광주</option>
           <option value="울산">울산</option>
           <option value="부산">부산</option>
           <option value="경기">경기</option>
           <option value="강원">강원</option>
           <option value="경북">경북</option>
           <option value="경남">경남</option>
           <option value="전북">전북</option>
           <option value="전남">전남</option>
           <option value="충남">충남</option>
           <option value="충북">충북</option>
           <option value="제주">제주</option>
        </select>
      </li> 
    
      <li>  
        <label class='label' for='deal_way' >거래방식</label>
        <select name='deal_way' id='deal_way'>
            <option value="직거래" selected="selected">직거래</option>
            <option value="택배">택배</option>
            <option value="등기우편">등기우편</option>
            <option value="안전거래">안전거래</option>
        </select>        
      </li>     
      <li> 
        <label class='label' for='wish_price'>구입가격</label>
        <input type='text' name='wish_price' id='wish_price' value='1000' required="required">
      </li>
      
      <li> 
        <label class='label' for='price'>희망가격</label>
        <input type='text' name='price' id='price' value='1000' required="required">
      </li>
      
      <li> 
        <label class='label' for='deal_date'>구입시기</label>
        <input type='text' name='deal_date' id='deal_date' value='xxxx-xx-xx' required="required">
      </li>
      
      <li> 
        <label class='label' for='quantity'>수량</label>
        <input type='text' name='quantity' id='quantity' value='1' required="required">
      </li>
      
      <li> 
        <label class='label' for='email'>이메일</label>
        <input type='text' name='email' id='email' value='xxx@xxx.com' required="required">
      </li>
      
      <li> 
        <label class='label' for='phone'>연락처</label>
        <input type='text' name='phone' id='phone' value='000-0000-0000' required="required">
      </li>
      
      <li> 
        <label class='label' for='title'>제목</label>
        <input type='text' name='title' id='title' value='내용을 입력하세요' required="required">
      </li>
      
      <li> 
        <label class='label' for='content'>글내용</label>
        <textarea rows="10" cols="40" name='content' id='content'  required="required"></textarea>
      </li>            
      
       <li class='right'>
        <button type="submit">등록</button>
        <button type="button" onclick="location.href='./list.do'">목록</button>
      </li> 
    </ul>
  </fieldset>
</FORM>
</DIV>

<!-- -------------------------------------------- -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</body>
<!-- -------------------------------------------- -->
</html> 