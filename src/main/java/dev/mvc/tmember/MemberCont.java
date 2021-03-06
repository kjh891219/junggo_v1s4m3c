package dev.mvc.tmember;
 
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import web.tool.Tool;
<<<<<<< HEAD

@Controller
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.tmember.MemberDAO")
  private MemberDAOInter memberDAO;
  
  public MemberCont(){
    System.out.println("--> MemberCont created.");
  }
  
  @RequestMapping(value = "/member/home.do", method = RequestMethod.GET)
  public ModelAndView home() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/index.jsp"); // member에 create.do가 들어올 경우 이동 -> /webapp/member/create.jsp
 
    return mav;
  }
  
  @RequestMapping(value = "/member/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/create"); // member에 create.do가 들어올 경우 이동 -> /webapp/member/create.jsp
 
    return mav;
  }
 
  @RequestMapping(value = "/member/create.do", method = RequestMethod.POST)
  public ModelAndView create(MemberVO memberVO) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // /webapp/member/message.jsp
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
  
    
// 권한, 인증 추가 ---------------------------------------------------------    
    memberVO.setAuth(Tool.key()); // ABC012345678901234
    memberVO.setDroupout("N");
     
    if (memberDAO.admin_search("M") == 0){ // 컬럼명, 마스터 계정
      msgs.add("최초 등록 계정임으로 Master 계정입니다.<br><br>");
      memberVO.setAct("M");  // 최고 관리자 지정
      memberVO.setConfirm("Y"); //  최고 관리자임으로 메일 확인 처리
    }else{
      memberVO.setAct("H");  // 관리자가 승인이 필요함, H: hold.
      memberVO.setConfirm("N"); // 메일 확인 안됨, 가입자가 메일 확인 안함. 
    }
    
// 이메일 ---------------------------------------------------------     
    String subject = "Blog 관리자 메일 인증입니다.";  // 제목
    String content = "메일 인증<br><br>";  // 내용
    content += "아래의 링크를 클릭하면 가입이 완료됩니다.<br><br>";
    // http://172.16.12.1:9090/admin_v1jq/admin1/confirm.jsp?email=testcell2010@gmail.com&auth=ABC1234567890

    content += "http://localhost:9090/junggo/member/confirm.do?email=" + memberVO.getEmail() + "&auth=" + memberVO.getAuth();

    // mw-002.cafe24.com, Cafe24
    String host = "mw-002.cafe24.com";    // smtp mail server(서버관리자)     
    String from = "chanmi_blog@gmail.com";   // 보내는 주소, 블로그 관리자 주소
    String to = memberVO.getEmail();    // 받는 사람

    Properties props = new Properties();  // SMTP 프로토콜 사용, port 25
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.auth","true");

    Authenticator authenticator = new MyAuthentication();
    Session sess = Session.getInstance(props, authenticator);   // 계정 인증 검사

    try {
      Message msg = new MimeMessage(sess);   // 메일 내용 객체 생성
      msg.setFrom(new InternetAddress(from));   // 보내는 사람 설정
            
      // 한명에게만 보냄
      InternetAddress[] address = {new InternetAddress(to)}; // 받는 사람 설정
      
      msg.setRecipients(Message.RecipientType.TO, address); // 수령인 주소 설정
            
      msg.setSubject(subject);                  // 제목 설정 
      msg.setSentDate(new Date());          // 보낸 날짜 설정
            
      // msg.setText(msgText); // 메일 내용으로 문자만 보낼 경우

      // 보내는 내용으로 HTML 형식으로 보낼 경우
      msg.setContent(content, "text/html;charset=utf-8");
            
      Transport.send(msg);  // 메일 전송

      msgs.add("<u>인증 메일이 발송되어습니다.</u><br><br>");
      msgs.add("<u>메일을 열고 링크를 클릭해주세요.</u><br>");
      
    } catch (MessagingException mex) {
      System.out.println(mex.getMessage());
      // out.println(mex.getMessage()+"<br><br>");
      // out.println(to + "님에게 메일 발송을 실패 했습니다.");
    }
// ---------------------------------------------------------     
    
    if (memberDAO.create(memberVO) == 1) {
      msgs.add("회원가입이 처리 되었습니다.");
      msgs.add("가입해주셔서 감사합니다.");
      msgs.add("이메일 인증 시 로그인이 가능합니다.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">로그인</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    } else {
      msgs.add("회원 가입에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    }
 
    links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
 
  /**
   * 중복 아이디를 검사합니다.
   * @param id
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkId.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String checkId(String id) {
 
    JSONObject obj = new JSONObject();
 
    int cnt = memberDAO.checkId(id);
    obj.put("cnt", cnt);
 
    return obj.toJSONString();
  }
  
  /**
   * 중복 닉네임를 검사합니다.
   * @param nickname
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkNickname.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String checkNickname(String nickname) {
 
    JSONObject obj = new JSONObject();
 
    int cnt = memberDAO.checkNickname(nickname);
    obj.put("cnt", cnt);
 
    return obj.toJSONString();
  }
  
  /**
   * 중복 이메일을 검사합니다.
   * @param email
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkEmail.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String checkEmail(String email) {
    JSONObject obj = new JSONObject();
    
    int cnt = memberDAO.checkEmail(email);
    obj.put("cnt", cnt);
    
    return obj.toJSONString();
  }

  /**
   * 전체 목록을 출력합니다.
   * 
   * @return
   */
  @RequestMapping(value = "/member/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/list"); //  /webapp/member/list.jsp
    mav.addObject("list", memberDAO.list());
 
    return mav;
  }
  
  @RequestMapping(value = "/member/read.do", method = RequestMethod.GET)
  public ModelAndView read(int mno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/read");
    mav.addObject("memberVO", memberDAO.read(mno));
 
    return mav;
  }
  
  @RequestMapping(value = "/member/update.do", method = RequestMethod.POST)
  public ModelAndView update(MemberVO memberVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    if (memberDAO.update(memberVO) == 1) {
      msgs.add("회원정보가 수정되었습니다.");
      links.add("<button type='button' onclick=\"location.href='./read.do?mno="+memberVO.getMno()+"'\">변경된 회원정보 확인</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    } else {
      msgs.add("회원 정보 변경에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    }
 
    links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
 
  /**
   * 이메일 인증 후 처리
   * confirm(인증 여부), act(권한) Y로 변경
   * @param memberVO
   * @return
   */
  @RequestMapping(value = "/member/confirm.do", method = RequestMethod.GET)
  public ModelAndView confirm(MemberVO memberVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    if (memberDAO.confirm(memberVO) == 1) {
      msgs.add("이메일 인증이 완료되었습니다.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">로그인</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    } else {
      msgs.add("이메일 인증이 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
  
  @RequestMapping(value = "/member/login.do", 
                              method = RequestMethod.GET)
  public ModelAndView login() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/login_ck_form"); // /webapp/member/login_ck_form.jsp

    return mav;
  }
  
  @RequestMapping(value = "/member/login.do", 
                              method = RequestMethod.POST)
  public ModelAndView login(MemberVO memberVO, 
                                         HttpSession session, 
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
    // System.out.println("--> login() POST called.");
  
    ModelAndView mav = new ModelAndView();
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
  
    if (memberDAO.login(memberVO) == 1) {
      String act = memberDAO.read_userid(memberVO.getUserid()).getAct();
      int mno = memberDAO.read_userid(memberVO.getUserid()).getMno();
      if ("MY".indexOf(act) >= 0){ // 로그인 권한 있음. M: Master, Y:일반 회원
        System.out.println("act:"+ ("MY".indexOf(act)));
        System.out.println("act:"+ ("MY".indexOf("M")));
        System.out.println("act:"+ ("MY".indexOf("Y")));
        
        session.setAttribute("userid", memberVO.getUserid());
        session.setAttribute("pwd", memberVO.getPwd());
        session.setAttribute("act", act);
        session.setAttribute("mno", mno);
        // ------------------------------------------------------------------
        // userid 저장 관련 쿠키 저장
        // ------------------------------------------------------------------
        String userid_save = Tool.checkNull(memberVO.getUserid_save());
        if (userid_save.equals("Y")){ // id 저장 할 경우
          Cookie ck_userid = new Cookie("ck_userid", memberVO.getUserid()); // id 저장
          ck_userid.setMaxAge(600); // 초 단위, 10분
          response.addCookie(ck_userid);
        }else{ // id를 저장하지 않을 경우
          Cookie ck_userid = new Cookie("ck_userid", ""); 
          ck_userid.setMaxAge(0); // 쿠키 삭제
          response.addCookie(ck_userid);
        }
        // id 저장 여부를 결정하는 쿠기 기록, Y or "" 저장
        Cookie ck_userid_save = new Cookie("ck_userid_save", memberVO.getUserid_save());
        ck_userid_save.setMaxAge(600); // 초
        response.addCookie(ck_userid_save);
        // ------------------------------------------------------------------
      
        // ------------------------------------------------------------------
        // pwd 저장 관련 쿠키 저장
        // ------------------------------------------------------------------
        String pwd_save = Tool.checkNull(memberVO.getPwd_save());
        if (pwd_save.equals("Y")){ 
          Cookie ck_pwd = new Cookie("ck_pwd", memberVO.getPwd()); 
          ck_pwd.setMaxAge(600); // 초
          response.addCookie(ck_pwd);
      
        }else{ // passwd를 저장하지 않을 경우
          Cookie ck_pwd = new Cookie("ck_pwd", "");
          ck_pwd.setMaxAge(0); // 초
          response.addCookie(ck_pwd);
      
        }
        // pwd 저장 여부를 결정하는 쿠키 기록, Y or "" 저장
        Cookie ck_pwd_save = new Cookie("ck_pwd_save", memberVO.getPwd_save());
        ck_pwd_save.setMaxAge(600); // 초
        response.addCookie(ck_pwd_save);
        // ------------------------------------------------------------------
      
        String url_address = Tool.checkNull(memberVO.getUrl_address());
        if (url_address.length() > 0){
          mav.setViewName("redirect:" + memberVO.getUrl_address());
        }else{
          System.out.println("--> index.jsp 페이지로 이동합니다.");
          mav.setViewName("redirect:/index.jsp"); // 확장자 명시
        }
    
        } else {
          mav.setViewName("/member/message");
          msgs.add("현재 계정이 사용 불가합니다.");
          msgs.add("관리자에게 문의해주세요.");
          links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
          links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
        }
      
      } else {
        mav.setViewName("/member/message");
        msgs.add("로그인에 실패했습니다.");
        msgs.add("죄송하지만 다시한번 시도해주세요.");
        links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
        links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
      }
      
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
      
      return mav;
  }
  
  @RequestMapping(value = "/member/logout.do", 
                              method = RequestMethod.GET)
  public ModelAndView logout(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // /webapp/member/message.jsp
  
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
  
    msgs.add("이용해주셔서 감사합니다.");
    links.add("<button type='button' onclick=\"location.href='../index.do'\">홈페이지</button>");
  
    session.invalidate(); // 모든 session 변수 삭제
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }  

}





//javamail lib 이 필요합니다.
=======
 
@Controller
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.tmember.MemberDAO")
  private MemberDAOInter memberDAO;
  
  public MemberCont(){
    System.out.println("--> MemberCont created.");
  }
  
  @RequestMapping(value = "/member/home.do", method = RequestMethod.GET)
  public ModelAndView home() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/index"); // member�뿉 create.do媛� �뱾�뼱�삱 寃쎌슦 �씠�룞 -> /webapp/member/create.jsp
 
    return mav;
  }
  
  @RequestMapping(value = "/member/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/create"); // member�뿉 create.do媛� �뱾�뼱�삱 寃쎌슦 �씠�룞 -> /webapp/member/create.jsp
 
    return mav;
  }
 
  @RequestMapping(value = "/member/create.do", method = RequestMethod.POST)
  public ModelAndView create(MemberVO memberVO) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // /webapp/member/message.jsp
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
  
    
// 沅뚰븳, �씤利� 異붽�� ---------------------------------------------------------    
    memberVO.setAuth(Tool.key()); // ABC012345678901234
    memberVO.setDropout("N");
     
    if (memberDAO.admin_search("M") == 0){ // 而щ읆紐�, 留덉뒪�꽣 怨꾩젙
      msgs.add("理쒖큹 �벑濡� 怨꾩젙�엫�쑝濡� Master 怨꾩젙�엯�땲�떎.<br><br>");
      memberVO.setAct("M");  // 理쒓퀬 愿�由ъ옄 吏��젙
      memberVO.setConfirm("Y"); //  理쒓퀬 愿�由ъ옄�엫�쑝濡� 硫붿씪 �솗�씤 泥섎━
    }else{
      memberVO.setAct("H");  // 愿�由ъ옄媛� �듅�씤�씠 �븘�슂�븿, H: hold.
      memberVO.setConfirm("N"); // 硫붿씪 �솗�씤 �븞�맖, 媛��엯�옄媛� 硫붿씪 �솗�씤 �븞�븿. 
    }
    
// �씠硫붿씪 ---------------------------------------------------------     
    String subject = "Blog 愿�由ъ옄 硫붿씪 �씤利앹엯�땲�떎.";  // �젣紐�
    String content = "硫붿씪 �씤利�<br><br>";  // �궡�슜
    content += "�븘�옒�쓽 留곹겕瑜� �겢由��븯硫� 媛��엯�씠 �셿猷뚮맗�땲�떎.<br><br>";
    // http://172.16.12.1:9090/admin_v1jq/admin1/confirm.jsp?email=testcell2010@gmail.com&auth=ABC1234567890

    content += "http://localhost:9090/tmember/member/confirm.do?email=" + memberVO.getEmail() + "&auth=" + memberVO.getAuth();

    // mw-002.cafe24.com, Cafe24
    String host = "mw-002.cafe24.com";    // smtp mail server(�꽌踰꾧��由ъ옄)     
    String from = "chanmi_blog@gmail.com";   // 蹂대궡�뒗 二쇱냼, 釉붾줈洹� 愿�由ъ옄 二쇱냼
    String to = memberVO.getEmail();    // 諛쏅뒗 �궗�엺

    Properties props = new Properties();  // SMTP �봽濡쒗넗肄� �궗�슜, port 25
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.auth","true");

    Authenticator authenticator = new MyAuthentication();
    Session sess = Session.getInstance(props, authenticator);   // 怨꾩젙 �씤利� 寃��궗

    try {
      Message msg = new MimeMessage(sess);   // 硫붿씪 �궡�슜 媛앹껜 �깮�꽦
      msg.setFrom(new InternetAddress(from));   // 蹂대궡�뒗 �궗�엺 �꽕�젙
            
      // �븳紐낆뿉寃뚮쭔 蹂대깂
      InternetAddress[] address = {new InternetAddress(to)}; // 諛쏅뒗 �궗�엺 �꽕�젙
      
      msg.setRecipients(Message.RecipientType.TO, address); // �닔�졊�씤 二쇱냼 �꽕�젙
            
      msg.setSubject(subject);                  // �젣紐� �꽕�젙 
      msg.setSentDate(new Date());          // 蹂대궦 �궇吏� �꽕�젙
            
      // msg.setText(msgText); // 硫붿씪 �궡�슜�쑝濡� 臾몄옄留� 蹂대궪 寃쎌슦

      // 蹂대궡�뒗 �궡�슜�쑝濡� HTML �삎�떇�쑝濡� 蹂대궪 寃쎌슦
      msg.setContent(content, "text/html;charset=utf-8");
            
      Transport.send(msg);  // 硫붿씪 �쟾�넚

      msgs.add("<u>�씤利� 硫붿씪�씠 諛쒖넚�릺�뼱�뒿�땲�떎.</u><br><br>");
      msgs.add("<u>硫붿씪�쓣 �뿴怨� 留곹겕瑜� �겢由��빐二쇱꽭�슂.</u><br>");
      
    } catch (MessagingException mex) {
      System.out.println(mex.getMessage());
      // out.println(mex.getMessage()+"<br><br>");
      // out.println(to + "�떂�뿉寃� 硫붿씪 諛쒖넚�쓣 �떎�뙣 �뻽�뒿�땲�떎.");
    }
// ---------------------------------------------------------     
    
    if (memberDAO.create(memberVO) == 1) {
      msgs.add("�쉶�썝媛��엯�씠 泥섎━ �릺�뿀�뒿�땲�떎.");
      msgs.add("媛��엯�빐二쇱뀛�꽌 媛먯궗�빀�땲�떎.");
      msgs.add("�씠硫붿씪 �씤利� �떆 濡쒓렇�씤�씠 媛��뒫�빀�땲�떎.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">濡쒓렇�씤</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">�솃�럹�씠吏�</button>");
    } else {
      msgs.add("�쉶�썝 媛��엯�뿉 �떎�뙣�뻽�뒿�땲�떎.");
      msgs.add("二꾩넚�븯吏�留� �떎�떆�븳踰� �떆�룄�빐二쇱꽭�슂.");
      links.add("<button type='button' onclick=\"history.back()\">�떎�떆�떆�룄</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">�솃�럹�씠吏�</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
 
  /**
   * 以묐났 �븘�씠�뵒瑜� 寃��궗�빀�땲�떎.
   * @param id
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkId.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String checkId(String id) {
 
    JSONObject obj = new JSONObject();
 
    int cnt = memberDAO.checkId(id);
    obj.put("cnt", cnt);
 
    return obj.toJSONString();
  }
  
  /**
   * 以묐났 �땳�꽕�엫瑜� 寃��궗�빀�땲�떎.
   * @param nickname
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkNickname.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String checkNickname(String nickname) {
    System.out.print(nickname);
    JSONObject obj = new JSONObject();
    int cnt = memberDAO.checkNickname(nickname);
    obj.put("cnt", cnt);
 
    return obj.toJSONString();
  }
  
  /**
   * 以묐났 �씠硫붿씪�쓣 寃��궗�빀�땲�떎.
   * @param email
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkEmail.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String checkEmail(String email) {
    JSONObject obj = new JSONObject();
    
    int cnt = memberDAO.checkEmail(email);
    obj.put("cnt", cnt);
    
    return obj.toJSONString();
  }
  
  /**
   * �닔�젙 �떆 以묐났 �씠硫붿씪�쓣 寃��궗�빀�땲�떎.
   * @param email
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkNickname_update.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String checkNickname_update(String userid, String nickname) {
    JSONObject obj = new JSONObject();
    
    HashMap<String, String> hashMap = new HashMap<String, String>();
    hashMap.put("userid", userid);
    hashMap.put("nickname", nickname);
   
    int cnt = memberDAO.checkNickname_update(hashMap);
    obj.put("cnt", cnt);
    
    return obj.toJSONString();
  }
  
  /**
   * �닔�젙 �떆 以묐났 �씠硫붿씪�쓣 寃��궗�빀�땲�떎.
   * @param email
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkEmail_update.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String checkEmail_update(String userid, String email) {
    JSONObject obj = new JSONObject();
    
    HashMap<String, String> hashMap = new HashMap<String, String>();
    hashMap.put("userid", userid);
    hashMap.put("email", email);
    
    int cnt = memberDAO.checkEmail_update(hashMap);
    obj.put("cnt", cnt);
    
    return obj.toJSONString();
  }

  /**
   * �쟾泥� 紐⑸줉�쓣 異쒕젰�빀�땲�떎.
   * 
   * @return
   */
  @RequestMapping(value = "/member/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/list"); //  /webapp/member/list.jsp
    mav.addObject("list", memberDAO.list());
 
    return mav;
  }
  
  @RequestMapping(value = "/member/read.do", method = RequestMethod.GET)
  public ModelAndView read(int mno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/read");
    mav.addObject("memberVO", memberDAO.read(mno));
 
    return mav;
  }
  
 
  /**
   * �씠硫붿씪 �씤利� �썑 泥섎━
   * confirm(�씤利� �뿬遺�), act(沅뚰븳) Y濡� 蹂�寃�
   * @param memberVO
   * @return
   */
  @RequestMapping(value = "/member/confirm.do", method = RequestMethod.GET)
  public ModelAndView confirm(MemberVO memberVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    if (memberDAO.confirm(memberVO) == 1) {
      msgs.add("�씠硫붿씪 �씤利앹씠 �셿猷뚮릺�뿀�뒿�땲�떎.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">濡쒓렇�씤</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">�솃�럹�씠吏�</button>");
    } else {
      msgs.add("�씠硫붿씪 �씤利앹씠 �떎�뙣�뻽�뒿�땲�떎.");
      msgs.add("二꾩넚�븯吏�留� �떎�떆�븳踰� �떆�룄�빐二쇱꽭�슂.");
      links.add("<button type='button' onclick=\"history.back()\">�떎�떆�떆�룄</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">�솃�럹�씠吏�</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
  
  @RequestMapping(value = "/member/login.do", 
                              method = RequestMethod.GET)
  public ModelAndView login() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/login_ck_form"); // /webapp/member/login_ck_form.jsp

    return mav;
  }
  
  @RequestMapping(value = "/member/login.do", 
                              method = RequestMethod.POST)
  public ModelAndView login(MemberVO memberVO, 
                                         HttpSession session, 
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
    // System.out.println("--> login() POST called.");
  
    ModelAndView mav = new ModelAndView();
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
  
    if (memberDAO.login(memberVO) == 1) {
      String act = memberDAO.read_userid(memberVO.getUserid()).getAct();
      int mno = memberDAO.read_userid(memberVO.getUserid()).getMno();
      if ("MY".indexOf(act) >= 0){ // 濡쒓렇�씤 沅뚰븳 �엳�쓬. M: Master, Y:�씪諛� �쉶�썝
        session.setAttribute("userid", memberVO.getUserid());
        session.setAttribute("pwd", memberVO.getPwd());
        session.setAttribute("act", act);
        session.setAttribute("mno", mno);
        // ------------------------------------------------------------------
        // userid ����옣 愿��젴 荑좏궎 ����옣
        // ------------------------------------------------------------------
        String userid_save = Tool.checkNull(memberVO.getUserid_save());
        if (userid_save.equals("Y")){ // id ����옣 �븷 寃쎌슦
          Cookie ck_userid = new Cookie("ck_userid", memberVO.getUserid()); // id ����옣
          ck_userid.setMaxAge(600); // 珥� �떒�쐞, 10遺�
          response.addCookie(ck_userid);
        }else{ // id瑜� ����옣�븯吏� �븡�쓣 寃쎌슦
          Cookie ck_userid = new Cookie("ck_userid", ""); 
          ck_userid.setMaxAge(0); // 荑좏궎 �궘�젣
          response.addCookie(ck_userid);
        }
        // id ����옣 �뿬遺�瑜� 寃곗젙�븯�뒗 荑좉린 湲곕줉, Y or "" ����옣
        Cookie ck_userid_save = new Cookie("ck_userid_save", memberVO.getUserid_save());
        ck_userid_save.setMaxAge(600); // 珥�
        response.addCookie(ck_userid_save);
        // ------------------------------------------------------------------
      
        // ------------------------------------------------------------------
        // pwd ����옣 愿��젴 荑좏궎 ����옣
        // ------------------------------------------------------------------
        String pwd_save = Tool.checkNull(memberVO.getPwd_save());
        if (pwd_save.equals("Y")){ 
          Cookie ck_pwd = new Cookie("ck_pwd", memberVO.getPwd()); 
          ck_pwd.setMaxAge(600); // 珥�
          response.addCookie(ck_pwd);
      
        }else{ // passwd瑜� ����옣�븯吏� �븡�쓣 寃쎌슦
          Cookie ck_pwd = new Cookie("ck_pwd", "");
          ck_pwd.setMaxAge(0); // 珥�
          response.addCookie(ck_pwd);
      
        }
        // pwd ����옣 �뿬遺�瑜� 寃곗젙�븯�뒗 荑좏궎 湲곕줉, Y or "" ����옣
        Cookie ck_pwd_save = new Cookie("ck_pwd_save", memberVO.getPwd_save());
        ck_pwd_save.setMaxAge(600); // 珥�
        response.addCookie(ck_pwd_save);
        // ------------------------------------------------------------------
      
        String url_address = Tool.checkNull(memberVO.getUrl_address());
        if (url_address.length() > 0){
          mav.setViewName("redirect:" + memberVO.getUrl_address());
        }else{
          System.out.println("--> index.jsp �럹�씠吏�濡� �씠�룞�빀�땲�떎.");
          mav.setViewName("redirect:/index.jsp"); // �솗�옣�옄 紐낆떆
        }
    
        } else {
          mav.setViewName("/member/message");
          msgs.add("�쁽�옱 怨꾩젙�씠 �궗�슜 遺덇���빀�땲�떎.");
          msgs.add("愿�由ъ옄�뿉寃� 臾몄쓽�빐二쇱꽭�슂.");
          links.add("<button type='button' onclick=\"history.back()\">�떎�떆�떆�룄</button>");
          links.add("<button type='button' onclick=\"location.href='./home.do'\">�솃�럹�씠吏�</button>");
        }
      
      } else {
        mav.setViewName("/member/message");
        msgs.add("濡쒓렇�씤�뿉 �떎�뙣�뻽�뒿�땲�떎.");
        msgs.add("二꾩넚�븯吏�留� �떎�떆�븳踰� �떆�룄�빐二쇱꽭�슂.");
        links.add("<button type='button' onclick=\"history.back()\">�떎�떆�떆�룄</button>");
        links.add("<button type='button' onclick=\"location.href='./home.do'\">�솃�럹�씠吏�</button>");
      }
      
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
      
      return mav;
  }
  
  @RequestMapping(value = "/member/logout.do", 
                              method = RequestMethod.GET)
  public ModelAndView logout(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // /webapp/member/message.jsp
  
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
  
    msgs.add("�씠�슜�빐二쇱뀛�꽌 媛먯궗�빀�땲�떎.");
    links.add("<button type='button' onclick=\"location.href='../index.do'\">�솃�럹�씠吏�</button>");
  
    session.invalidate(); // 紐⑤뱺 session 蹂��닔 �궘�젣
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  } 
  
  /**
   * �깉�눜 �떊泥�
   * @param memberVO
   * @return
   */
  @RequestMapping(value = "/member/dropout.do", method = RequestMethod.POST)
  public ModelAndView dropout(MemberVO memberVO, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
    
    System.out.println(memberVO.getUserid());
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    if (memberDAO.dropout(memberVO) == 1) {
      session.invalidate();
      msgs.add("�깉�눜媛� �젙�긽�쟻�쑝濡� 泥섎━�릺�뿀�뒿�땲�떎.");
      msgs.add("�씠�슜�빐 二쇱뀛�꽌 媛먯궗�빀�땲�떎.");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">�솃�럹�씠吏�</button>");
      links.add("<button type='button' onclick=\"location.href='./create.do'\">�쉶�썝媛��엯</button>");
    } else {
      msgs.add("二꾩넚�븯吏�留� �떎�떆�븳踰� �떆�룄�빐二쇱꽭�슂.");
      links.add("<button type='button' onclick=\"history.back()\">�떎�떆�떆�룄</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">�솃�럹�씠吏�</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
  
  /**
   * �뙣�뒪�썙�뱶 蹂�寃� �뤌 異쒕젰
   * @param mno �쉶�썝 踰덊샇
   * @return
   */
  @RequestMapping(value = "/member/checkPwd.do", method = RequestMethod.GET)
  public ModelAndView checkPwd(int mno, String flag) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/checkPwd"); // /webapp/member/checkPwd.jsp
    
    mav.addObject("flag", flag);
    mav.addObject("mno", mno);
    return mav;
  }
  
  @RequestMapping(value = "/member/checkPwd.do", method = RequestMethod.POST)
  public ModelAndView checkPwd(MemberVO memberVO, String flag) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    // �쁽�옱 �뙣�뒪�썙�뱶 �씪移� �뿬遺� 寃��궗
    if (memberDAO.checkPwd(memberVO.getUserid(), memberVO.getPwd()) == 1){
      mav.addObject("memberVO", memberDAO.read(memberVO.getMno()));
        if(flag.equals("1")){
          mav.setViewName("/member/read");
        } else {
          mav.setViewName("/member/dropout");
        }
      } else { }
 
    return mav;
  }
  
  /**
   * �뙣�뒪�썙�뱶 �궘�젣 �뤌 異쒕젰
   * @param mno �궘�젣�븷 湲� 踰덊샇
   * @return
   */
  @RequestMapping(value = "/member/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int mno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/delete"); // /webapp/member/delete.jsp
    mav.addObject("mno", mno);
    
    return mav;
  }
  
 /**
  * �쉶�썝 �젙蹂� �닔�젙
  * �씠硫붿씪 蹂�寃� �떆 �옱�씤利� �븘�슂
  * @param memberVO
  * @param updateFlag
  * @return
  */
  @RequestMapping(value = "/member/update.do", method = RequestMethod.POST)
  public ModelAndView emailConfirm(MemberVO memberVO, String updateFlag, HttpSession session) {
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if(updateFlag.equals("1")) { // �씠硫붿씪�씠 蹂�寃� �릺�뿀�떎硫� �떎�뻾
      // 濡쒓렇�씤 遺덇���뒫 �긽�깭
      memberVO.setConfirm("N");
      memberVO.setAct("H");
      
        
  // �씠硫붿씪 ---------------------------------------------------------     
      String subject = "Blog 愿�由ъ옄 硫붿씪 �씤利앹엯�땲�떎.";  // �젣紐�
      String content = "硫붿씪 �씤利�<br><br>";  // �궡�슜
      content += "�븘�옒�쓽 留곹겕瑜� �겢由��븯硫� 媛��엯�씠 �셿猷뚮맗�땲�떎.<br><br>";
      // http://172.16.12.1:9090/admin_v1jq/admin1/confirm.jsp?email=testcell2010@gmail.com&auth=ABC1234567890
  
      content += "http://localhost:9090/tmember/member/confirm.do?email=" + memberVO.getEmail() + "&auth=" + memberVO.getAuth();
  
      // mw-002.cafe24.com, Cafe24
      String host = "mw-002.cafe24.com";    // smtp mail server(�꽌踰꾧��由ъ옄)     
      String from = "chanmi_blog@gmail.com";   // 蹂대궡�뒗 二쇱냼, 釉붾줈洹� 愿�由ъ옄 二쇱냼
      String to = memberVO.getEmail();    // 諛쏅뒗 �궗�엺
  
      Properties props = new Properties();  // SMTP �봽濡쒗넗肄� �궗�슜, port 25
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.auth","true");
  
      Authenticator authenticator = new MyAuthentication();
      Session sess = Session.getInstance(props, authenticator);   // 怨꾩젙 �씤利� 寃��궗
  
      try {
        Message msg = new MimeMessage(sess);   // 硫붿씪 �궡�슜 媛앹껜 �깮�꽦
        msg.setFrom(new InternetAddress(from));   // 蹂대궡�뒗 �궗�엺 �꽕�젙
              
        // �븳紐낆뿉寃뚮쭔 蹂대깂
        InternetAddress[] address = {new InternetAddress(to)}; // 諛쏅뒗 �궗�엺 �꽕�젙
        
        msg.setRecipients(Message.RecipientType.TO, address); // �닔�졊�씤 二쇱냼 �꽕�젙
              
        msg.setSubject(subject);                  // �젣紐� �꽕�젙 
        msg.setSentDate(new Date());          // 蹂대궦 �궇吏� �꽕�젙
              
        // msg.setText(msgText); // 硫붿씪 �궡�슜�쑝濡� 臾몄옄留� 蹂대궪 寃쎌슦
  
        // 蹂대궡�뒗 �궡�슜�쑝濡� HTML �삎�떇�쑝濡� 蹂대궪 寃쎌슦
        msg.setContent(content, "text/html;charset=utf-8");
              
        Transport.send(msg);  // 硫붿씪 �쟾�넚
  
        msgs.add("<u>�씠硫붿씪 �옱�씤利앹씠 �븘�슂�빀�땲�떎.</u><br><br>");
        msgs.add("<u>硫붿씪�쓣 �뿴怨� 留곹겕瑜� �겢由��빐二쇱꽭�슂.</u><br>");
        
      } catch (MessagingException mex) {
        System.out.println(mex.getMessage());
        // out.println(mex.getMessage()+"<br><br>");
        // out.println(to + "�떂�뿉寃� 硫붿씪 諛쒖넚�쓣 �떎�뙣 �뻽�뒿�땲�떎.");
      }
      
      } // if臾�
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
 
    if (memberDAO.update(memberVO) == 1) {
      msgs.add("�쉶�썝�젙蹂닿�� �닔�젙�릺�뿀�뒿�땲�떎.");
      msgs.add("�떎�떆 濡쒓렇�씤 �빐二쇱꽭�슂.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">濡쒓렇�씤</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">�솃�럹�씠吏�</button>");
    } else {
      msgs.add("�쉶�썝 �젙蹂� 蹂�寃쎌뿉 �떎�뙣�뻽�뒿�땲�떎.");
      msgs.add("二꾩넚�븯吏�留� �떎�떆�븳踰� �떆�룄�빐二쇱꽭�슂.");
      links.add("<button type='button' onclick=\"history.back()\">�떎�떆�떆�룄</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">�솃�럹�씠吏�</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    session.invalidate();
    
    return mav;
    
  }

}





//javamail lib �씠 �븘�슂�빀�땲�떎.
>>>>>>> branch 'master' of https://github.com/kjh891219/junggo_v1s4m3c.git
class MyAuthentication extends Authenticator {
  PasswordAuthentication pa;
  public MyAuthentication(){
   pa = new PasswordAuthentication("test@nulunggi.pe.kr", "test2016");
  }
  
  public PasswordAuthentication getPasswordAuthentication() {
   return pa;
  }
}