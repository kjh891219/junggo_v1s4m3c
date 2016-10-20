<%@ page contentType="text/html; charset=UTF-8" %>
<<<<<<< HEAD
 
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

window.onload = function(){
  find('email_dns').addEventListener('change', email_dns_p);  
}

function email_dns_p(){
  // alert('변경됨 ' + find('email_dns').value);
  // find('email').value = find('email_dns').value;
  var email = find('email').value;
  var position = email.indexOf('@'); // test1@mail.com
  if (position >= 0){
    email = email.substring(0, position); // test1
  }
  var email_dns = find('email_dns').value;
  if (email_dns == 'none'){ // 직접 입력
    find('email').value = email + '@';
    find('email').focus(); // 입력 커서 이동
  }else{
    find('email').value = email + '@' + email_dns;
  }
}
</script>
 
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<!-- ----------------------------------------- -->
 
<DIV class='title'>중고차 등록</DIV>
 
<DIV class='content' style='width: 50%;'>
<FORM name='frm' method='POST' action='./create.do'>
  <fieldset>
    <ul>
      <li>
        <label class='label' for='userid'>아이디</label>
        <input type='text' name='userid' id='userid' value='' required="required">
        <label class='label' for='nickname'>닉네임</label>
        <input type='text' name='nickname' id='nickname' value='' required="required">
         <label class='label' for='passwd'>비밀번호</label>
        <input type='password' name='passwd' id='passwd' value='1234' required="required">
      </li>
      <li>
        <label class='label' for='category'>카테고리 코드</label>
        <select name='category' id='category'>
          <option value="hyundai" selected="selected">HYUNDAI</option>
          <option value="kia" >KIA</option>
          <option value="chevrolet" >쉐보레</option>
          <option value="benz" >BENZ</option>
        </select>
        <label class='label' for='deal_code'>거래구분 코드</label>
        <select name='deal_code' id='deal_code'>
          <option value="sale" selected="selected">팝니다</option>
          <option value="buy">삽니다</option>
        </select>
      </li>
      <li>
        <label class='label' for='region'>거래 지역</label>
        <select name='region' id='region' >
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
          <label class='label' for='deal_way'>거래방식</label>
        <select name='deal_way' id='deal_way'>
          <option value="field" selected="selected">현장거래</option>
          <option value="delivery">택배</option>
        </select>
      </li>
      <li>
        <label class='label' for='h_price'>희망가격</label>
        <input type='text' name='h_price' id='h_price' value='0' >
      </li>
      
      <li>
        <label class='label' for='purc_date'>구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value='' >
      </li>
      
      <li>
        <label class='label' for='title'>제목</label>
        <input type='text' name='title' id='title' size='50' value='' required="required">
      </li>
    <li>
        <label for='content'>내용</label><br>
        <textarea name='content' id='content' rows='10' style='width: 100%;' placeholder="내용을 입력해주세요" required="required"></textarea>
      </li>
    <li>
        <label for='email'>E-mail</label><br>
        <input type='email' name='email' id='email' required="required"  style='width: 35%;'>
         <select name='email_dns'' id='email_dns'>
          <option value='none'>직접 입력</option>
          <option value="daum" selected="selected">daum.net</option>
          <option value='gmail.com'>gmail.com</option>
          <option value="naver.com" >naver.com</option>
          <option value='nate.com'>nate.com</option>
          <option value="google.com" >google.com</option>
          <option value="nate.com" >nate.com</option>
        </select>
      </li>
    <li>
        <label for='tel'>Tel</label><br>
        <input type='text' name='tel' id='tel' size='15' value='' required="required">
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
</body>
<!-- -------------------------------------------- -->
</html> 
=======
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

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>


<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
   
    // content: textarea name
    if (CKEDITOR.instances['content'].getData() == '') {
      window.alert('내용을 입력해 주세요.');
      CKEDITOR.instances['content'].focus();
      return false;
    }
    
    find('email_dns').addEventListener('change', email_dns_p);  
  
  };

  function email_dns_p(){
    // alert('변경됨 ' + find('email_dns').value);
    // find('email').value = find('email_dns').value;
    var email = find('email').value;
    var position = email.indexOf('@'); // test1@mail.com
    if (position >= 0){
      email = email.substring(0, position); // test1
    }
    var email_dns = find('email_dns').value;
    if (email_dns == 'none'){ // 직접 입력
      find('email').value = email + '@';
      find('email').focus(); // 입력 커서 이동
    }else{
      find('email').value = email + '@' + email_dns;
    }
  }

</script>


</head> 

<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
     

  <div class='content_menu' style='width: 90%;'>
    <A href='../usedcar/list.do'>게시판 목록</A> > 
<%--     <A href='./list.do?u_no=${usedcarVO.u_no }'>${usedcarVO.title }</A>｜
    <A href='./create.do?u_no=${usedcarVO.u_no }'>중고차 등록</A>｜ --%>
    <A href="javascript:location.reload();">새로고침</A>
  </div>
  
  <DIV class='content' style='width: 90%;'>
    <FORM name='frm' method='POST' action='./create.do'
              enctype="multipart/form-data">
     <%--  <input type='hidden' name='u_no' id='u_no' value='${usedcarVO.u_no }'> --%>

   <div class="form-group">   
      <ul>
       <li>
        <label class='label' for='userid'>아이디</label>
        <input type='text' name='userid' id='userid' value='' required="required">
         <label class='label' for='passwd'>비밀번호</label>
        <input type='password' name='passwd' id='passwd' value='1234' required="required"><br><br>
         <label class='label' for='nickname'>닉네임</label>
        <input type='text' name='nickname' id='nickname' value='' required="required">
       </li>
      
       <li>
        <label class='label' for='category'>카테고리 코드</label>
        <select name='category' id='category'>
          <option value="hyundai" selected="selected">HYUNDAI</option>
          <option value="kia" >KIA</option>
          <option value="chevrolet" >쉐보레</option>
          <option value="benz" >BENZ</option>
        </select>
        <label class='label' for='deal_code'>거래구분 코드</label>
        <select name='deal_code' id='deal_code'>
          <option value="sale" selected="selected">팝니다</option>
          <option value="buy">삽니다</option>
        </select>
      </li>
      
      <li>
        <label class='label' for='region'>거래 지역</label>
        <select name='region' id='region' >
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
          <label class='label' for='deal_way'>거래방식</label>
        <select name='deal_way' id='deal_way'>
          <option value="field" selected="selected">현장거래</option>
          <option value="delivery">택배</option>
        </select>
      </li>
      <li>
        <label class='label' for='h_price'>희망가격</label>
        <input type='text' name='h_price' id='h_price' value='0' >
      </li>
      
      <li>
        <label class='label' for='purc_date'>구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value='' >
       
       <label class='label' for='product_code'>거래방식</label>
        <select name='product_code' id='product_code'>
          <option value="old" selected="selected">중고</option>
          <option value="new">신상</option>
        </select>
      </li>
         
      <li>
        <label class='label' for='title'>제목</label>
        <input type='text' name='title' id='title' size='50' value='' required="required">
      </li>
      
       <li>
        <label for='content'>내용</label><br>
        <div>
          <textarea class="form-control" name='content' id='content'  rows='10' cols='70'>내용을 입력해주세요</textarea>
        </div>
       </li>

       <li>
      <label for="file2MF" class="col-xs-2 col-lg-2 control-label">업로드 파일</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file2MF' id='file2MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>

      <li>
        <label for='email'>E-mail</label><br>
        <input type='email' name='email' id='email' required="required"  style='width: 35%;'>
         <select name='email_dns'' id='email_dns'>
          <option value='none' selected="selected">직접 입력</option>
          <option value="daum.net" >daum.net</option>
          <option value='gmail.com'>gmail.com</option>
          <option value="naver.com" >naver.com</option>
          <option value='nate.com'>nate.com</option>
          <option value="google.com" >google.com</option>
          <option value="nate.com" >nate.com</option>
        </select>
      </li>
    <li>
        <label for='tel'>Tel</label><br>
        <input type='text' name='tel' id='tel' size='15' value='' required="required">
      </li>
      
      <li class='right'>
        <button type="submit">등록</button>
        <button type="button" onclick="location.href='./list.do'">목록</button>
      </li>     
      
      </ul>
       
      </div>   

    </FORM>
  </DIV>


     <jsp:include page="/menu/bottom.jsp" flush='false' />     

</body>

</html> 

>>>>>>> branch 'master' of https://github.com/kjh891219/junggo_v1s4m3c.git
