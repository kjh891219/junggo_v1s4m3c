<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>



</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<!-- ----------------------------------------- -->



<DIV class='center-block'>
<DIV class='title'>글등록</DIV>
<FORM name='frm' method='POST' action='./create.do' class='form-inline'>
  <fieldset>
    <ul>
    <div class="row">
      <div class="col-xs-5">
        <label class='select' for='category'>*분류</label>
        <select name='category' id="category" class="form-control-lg-10-lg-10-lg-10-lg-10">
           <option value="DSLR" selected="selected">DSLR</option>
           <option value="일반디카">일반디카</option>
           <option value="필름카메라">필름카메라</option>
           <option value="렌즈">렌즈</option>
        </select>
      </div>
      <div class="col-xs-5">
      <label for='tel' >*전화번호</label>
        <input type='text' name='tel' id='tel'  required="required" value='010-1111-2222' class="form-control-lg-10-lg-10-lg-10"> 
      </div>
      </div>
      <hr/>
      <div class="row">
      <div class="col-xs-5"> 
        <label for='nickname'>*별명</label>
        <input type='text' name='nickname' id='nickname' value= '${memberVO.nickname }' required="required" readonly="readonly" class='form-control-lg-10-lg-10-lg-10-lg-10'/>
      </div>
     <div class="col-xs-5">    
        <label for='passwd'>*패스워드</label>
        <input type='password' name='passwd' id='passwd' value='1234' required="required" class='form-control-lg-10-lg-10-lg-10'/>
      </div>
      </div>
      <hr/>
     <div class="row">
        <div class="col-xs-5">  
        <label for='deal_code'>*거래구분</label>
        <select name='deal_code' id="deal_code" class="form-control-lg-10-lg-10-lg-10">
           <option value="팝니다" selected="selected">팝니다</option>
           <option value="삽니다">삽니다</option>
        </select>
        </div>
        <div class="col-xs-5">
         <label for='product_code'>*상품구분</label>
        <select name='product_code' id='product_code' class="form-control-lg-10-lg-10-lg-10">
           <option value="중고품" selected="selected">중고품</option>
           <option value="신상품">신상품</option>
        </select>
        </div>
      </div>
      <hr/>
      <div class="row">
       <div class="col-xs-5"> 
         <label for='deal_way'>*거래방식</label>
         <select name='deal_way' id='deal_way' class="form-control-lg-10-lg-10-lg-10">
           <option value="직거래" selected="selected">직거래</option>
           <option value="</">택배</option>
        </select >
        </div>
        <div class="col-xs-5">  
        <label for='region'>*지역</label>
         <select name='region' id='region' class="form-control-lg-10-lg-10-lg-10">
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
      </div>
      </div>
      <hr/>
      <div class="row">
        <div class="col-xs-5"> 
        <label for='hprice'>*희망가격</label>
        <input type='text' name='hprice' id='hprice'  required="required" value='100000' class="form-control-lg-10-lg-10-lg-10">원
        </div>
        <div class="col-xs-5">       
        <label for='purc_date' >구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value='2016년10월' class="form-control-lg-10-lg-10-lg-10"> 
        </div>
     </div>
      <hr/>
      <div class="row">
      <div class="col-xs-5"> 
        <label for='quantity'>수량</label>
        <input type='text' name='quantity' id='quantity' value='1' class="form-control-lg-10-lg-10-lg-10">
      </div>
       <div class="col-xs-5">  
        <label for='email'>*이메일</label>
        <input type='text' name='email' id='email'  required="required" value='${memberVO.email }' class="form-control-lg-10-lg-10-lg-10">
        </div>
       </div>
      <hr/>
      <div class="form-group">
        <label for='title'>*제목</label>
        <input type='text' name='title' id='title' required="required" value='디카팔아요' class="form-control-lg-10-lg-10-lg-10">
      </div>
      <hr/>
      <div class="form-group">
        <label for='content'>상세설명</label>
        <textarea rows="10" cols="100"  name="content" id="content" placeholder="내용을 입력하세요" class="form-group">sqld책 싸게 팔아요</textarea>
      </div>
      <hr/>
      <div>
      <li class='right'>
        <button type="submit" class="btn btn-success btn-lg">등록</button>
        <button type="button" onclick="location.href='./list.jsp'" class="btn btn-danger btn-lg">취소</button>
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

