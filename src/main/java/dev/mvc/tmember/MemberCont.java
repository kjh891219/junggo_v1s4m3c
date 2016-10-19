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
class MyAuthentication extends Authenticator {
  PasswordAuthentication pa;
  public MyAuthentication(){
   pa = new PasswordAuthentication("test@nulunggi.pe.kr", "test2016");
  }
  
  public PasswordAuthentication getPasswordAuthentication() {
   return pa;
  }
}