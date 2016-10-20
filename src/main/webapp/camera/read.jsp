<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<!-- 합쳐지고 최소화된 최신 CSS -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link href="./css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript">
 
</script>

<script type="text/javascript">
  $(function(){
    $('#file2').load(function(){ // 태그 메모리 상주후 작동
      // var width = $('#file2').width();
      //alert('file2: ' + width); 
      if ($('#file2').width() > screen.width * 0.7){
        $('#file2').width('70%');      
      }
    });
    $('#file4').load(function(){ // 태그 메모리 상주후 작동
      // var width = $('#file2').width();
      //alert('file2: ' + width); 
      if ($('#file4').width() > screen.width * 0.7){
        $('#file4').width('70%');      
      }
    });
  });
  
</script>


</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<!-- ----------------------------------------- -->
<h1 style="text-align: center;">${cameraVO.title}</h1>
<div style="float: left;">
  내용 : ${cameraVO.content}
</div>
<div style="float: right;">
<h2><상품정보></h2>
<table border="1">
  <tr>
  <td style="text-align: center;">거래구분</td>
  <td style="text-align: center;">${cameraVO.deal_code}</td>
  <tr>
  <td style="text-align: center;">분류</td>
  <td style="text-align: center;">${cameraVO.category}</td>
  </tr>
  <tr>
  <td style="text-align: center;">상품구분</td>
  <td style="text-align: center;">${cameraVO.product_code}</td>
  </tr>
  <tr>
  <td style="text-align: center;">거래지역</td>
  <td style="text-align: center;">${cameraVO.region}</td>
  </tr>
  <tr>
  <td style="text-align: center;">거래방식</td>
  <td style="text-align: center;">${cameraVO.deal_way}</td>
  </tr>
  <tr>
  <td style="text-align: center;">구입시기</td>
  <td style="text-align: center;">${cameraVO.purc_date}</td>
  </tr>
  <tr>
  <td style="text-align: center;">수량</td>
  <td style="text-align: center;">${cameraVO.quantity}</td>
  </tr>
  <tr>
  <td style="text-align: center;">희망가격</td>
  <td style="text-align: center;">${cameraVO.hprice}</td>
  </tr>       
</table>  


<h2><판매자 정보></h2>
 <table border="1">
  <tr>
  <td style="text-align: center;">판매자</td>
  <td style="text-align: center;">${cameraVO.nickname}</td>
  <tr>
  <tr>
  <td style="text-align: center;">이메일</td>
  <td style="text-align: center;">${cameraVO.email}</td>
  <tr>
  <tr>
  <td style="text-align: center;">전화번호</td>
  <td style="text-align: center;">${cameraVO.tel}</td>
  <tr>
 </table>
</div>
<div  style="clear: both;">
            <li>
            <label for="file1" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${cameraVO.size2 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/camera/storage&filename=${cameraVO.file2}'>${cameraVO.file2}</A> (${cameraVO.size2Label})
              </c:if>
            </span>    
            <div id='file2Panel'>
              <c:set var='file2' value="${fn:toLowerCase(cameraVO.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <IMG id='file2' src='./storage/${cameraVO.file2}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file2'  src='./storage/${cameraVO.file2}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file2'  src='./storage/${cameraVO.file2}'' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
          <li>
            <label for="file3" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${cameraVO.size4 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/camera/storage&filename=${cameraVO.file4}'>${cameraVO.file4}</A> (${cameraVO.size4Label})
              </c:if>
            </span>    
            <div id='file4Panel'>
              <c:set var='file4' value="${fn:toLowerCase(cameraVO.file4)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file4, '.jpg')}">
                  <IMG id='file4' src='./storage/${cameraVO.file4}' >
                </c:when>
                <c:when test="${fn:endsWith(file4, '.gif')}">
                  <IMG id='file4'  src='./storage/${cameraVO.file4}' >
                </c:when>
                <c:when test="${fn:endsWith(file4, '.png')}">
                  <IMG id='file4'  src='./storage/${cameraVO.file4}'' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
          
          <li>
            <label for="file5" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${cameraVO.size6 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/camera/storage&filename=${cameraVO.file6}'>${cameraVO.file6}</A> (${cameraVO.size6Label})
              </c:if>
            </span>    
            <div id='file6Panel'>
              <c:set var='file6' value="${fn:toLowerCase(cameraVO.file6)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file6, '.jpg')}">
                  <IMG id='file6' src='./storage/${cameraVO.file6}' >
                </c:when>
                <c:when test="${fn:endsWith(file6, '.gif')}">
                  <IMG id='file6'  src='./storage/${cameraVO.file6}' >
                </c:when>
                <c:when test="${fn:endsWith(file6, '.png')}">
                  <IMG id='file6'  src='./storage/${cameraVO.file6}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
          
           <li>
            <label for="file7" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${cameraVO.size8 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/camera/storage&filename=${cameraVO.file8}'>${cameraVO.file8}</A> (${cameraVO.size8Label})
              </c:if>
            </span>    
            <div id='file8Panel'>
              <c:set var='file8' value="${fn:toLowerCase(cameraVO.file8)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file8, '.jpg')}">
                  <IMG id='file8' src='./storage/${cameraVO.file8}' >
                </c:when>
                <c:when test="${fn:endsWith(file8, '.gif')}">
                  <IMG id='file8'  src='./storage/${cameraVO.file8}' >
                </c:when>
                <c:when test="${fn:endsWith(file8, '.png')}">
                  <IMG id='file8'  src='./storage/${cameraVO.file8}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
          
           <li>
            <label for="file9" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${cameraVO.size10 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/camera/storage&filename=${cameraVO.file10}'>${cameraVO.file10}</A> (${cameraVO.size10Label})
              </c:if>
            </span>    
            <div id='file10Panel'>
              <c:set var='file10' value="${fn:toLowerCase(cameraVO.file10)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file10, '.jpg')}">
                  <IMG id='file10' src='./storage/${cameraVO.file10}' >
                </c:when>
                <c:when test="${fn:endsWith(file10, '.gif')}">
                  <IMG id='file10'  src='./storage/${cameraVO.file10}' >
                </c:when>
                <c:when test="${fn:endsWith(file10, '.png')}">
                  <IMG id='file10'  src='./storage/${cameraVO.file10}' >
                </c:when>
              </c:choose>
            </div>
          </li>
                    
</div>
<DIV style="text-align: right;">
  <button type='button' onclick="location.href='./update.do?ctno=${cameraVO.ctno}&col=${searchDTO.col}&word=${searchDTO.word}'">수정</button>
  <button type='button' onclick="location.href='./delete.do?ctno=${cameraVO.ctno}&col=${searchDTO.col}&word=${searchDTO.word}'">삭제</button>
  <button type='button' onclick="location.href='./list.do'">목록</button>
  
</DIV>
<!-- -------------------------------------------- -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</body>
<!-- -------------------------------------------- -->
</html> 