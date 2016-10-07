<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>

</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">

<!-- ----------------------------------------- -->
 
<DIV class='title'>상품등록</DIV>
 
<DIV class='content'>
<FORM name='frm' method='POST' action='./create.do'>
  <fieldset>
    <ul>
       <li>
        <label class='label' for='category'>분류</label>
        <select name = 'category' id = 'category'>
         <option value = "ps4" selected="selected">PS4</option>
         <option value = "nontendo">닌텐도</option>
         <option value = "pc">PC</option>
         <option value = "xbox">xbox</option>
        </select>
      </li>
      <hr/>
      <li>
       <label class='label' for='rname'>아이디</label>
        <input type='text' name='rname' id='rname' value='user1' required="required">
        <label class='label' for='passwd'>패스워드</label>
        <input type='password' name='passwd' id='passwd' value='1234' required="required">
      </li>
      <hr/>
      <li>
        <label class='label' for='email'>이메일</label>
        <input type='text' name='email' id='email' value='abc@naver.com' required="required">
        <label class='label' for='tell'>전화번호</label>
        <input type="tel" name='tell' id='tell' value='010-9999-9999'> 예) 010-0000-0000
      </li>
      <hr/>
      <li>
        <label class='label' for='title'>제목</label>
        <input type='text' name='title' id='title' value='xbox' required="required">
      </li>
      <hr/>
      <li>
        <label class='label' for='content'>상세 설명</label>
        <textarea rows ="10" cols = "40"  name = "content" id="content">싸게 팔아요</textarea>
      </li>
      <hr/>
      <li>
        <label class='label' for='area'>지역</label>
      <select name = 'area' id = 'area'>
           <option value="서울" selected="selected">서울</option>
           <option value="인천">인천</option>
           <option value="대구">대구</option>
           <option value="대전">대전</option>
           <option value="광주">광주</option>
           <option value="울산">울산</option>
           <option value="busan">부산</option>
           <option value="gyunggi">경기</option>
           <option value="gang">강원</option>
           <option value="gbook">경북</option>
           <option value="gnam">경남</option>
           <option value="jbook">전북</option>
           <option value="jnam">전남</option>
           <option value="cnam">충남</option>
           <option value="cbook">충북</option>
           <option value="jeju">제주</option>
        </select>
        <label class='label' for='state'>거래구분</label>
        <select name = 'state' id = 'state'>
         <option value = "sell" selected="selected">팝니다</option>
         <option value = "done">판매완료</option>
         <option value = "buy">삽니다</option>
         <option value = "bdone">구매완료</option>
        </select>
      </li>
       <hr/>
      <li>
        <label class='label' for='purchtime'>구입시기</label>
        <input type='text' name='purchtime' id='purchtime' value='2016년7월' required="required">
        <label class='label' for='purchprice'>구입가격</label>
        <input type='text' name='purchprice' id='purchprice' value='300000원' required="required">
      </li>
       <hr/>
      <li>
        <label class='label' for='goods'>상품구분</label>
        <select name = 'goods' id = 'goods'>
         <option value = "old" selected="selected">중고품</option>
         <option value = "new">신고품</option>
         <option value = "lastest">신상품</option>
        </select>
        <label class='label' for='howdeal'>거래방식</label>
        <select name = 'howdeal' id = 'howdeal'>
         <option value = "직거래" selected="selected">직거래</option>
         <option value = "택배">택배</option>
         <option value = "등기우편">등기우편</option>
         <option value = "안전거래">안전거래</option>
        </select>
       </li>
       <hr/>
      <li>
        <label class='label' for='amount'>수량</label>
        <input type='text'  name ='amount' id = 'amount' value='1' required="required">
        <label class='label' for='price'>희망 가격</label>
        <input type='text'  name ='price' id = 'price' value='270000원' required="required">
      </li>
       <hr/>
      <li>
        <label class='label' for='lev'>평점</label>
         <select name = 'lev' id = 'lev'>
         <option value = "★" selected="selected">★</option>
         <option value = "★★">★★</option>
         <option value = "★★★">★★★</option>
         <option value = "★★★★">★★★★</option>
         <option value = "★★★★★">★★★★★</option>
         </select>
      
        <label class='label' for='genre'>장르</label>
         <select name = 'genre' id = 'genre'>
         <option value = "rpg" selected="selected">RPG</option>
         <option value = "action">ACTION</option>
         <option value = "soprts">SPORTS</option>
         <option value = "svg">전략</option>
        </select>
      </li>
      <hr/>
      <li class='right'>
        <button type="submit">가입</button>
        <button type="button" onclick="location.href='./list.jsp'">취소</button>
      </li>         
    </ul>
  </fieldset>
</FORM>
</DIV>
 
<!-- -------------------------------------------- -->

</body>
<!-- -------------------------------------------- -->
</html> 