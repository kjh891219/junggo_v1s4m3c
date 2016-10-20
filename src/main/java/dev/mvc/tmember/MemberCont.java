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
    mav.setViewName("/index.jsp"); // member¿¡ create.do°¡ µé¾î¿Ã °æ¿ì ÀÌµ¿ -> /webapp/member/create.jsp
 
    return mav;
  }
  
  @RequestMapping(value = "/member/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/create"); // member¿¡ create.do°¡ µé¾î¿Ã °æ¿ì ÀÌµ¿ -> /webapp/member/create.jsp
 
    return mav;
  }
 
  @RequestMapping(value = "/member/create.do", method = RequestMethod.POST)
  public ModelAndView create(MemberVO memberVO) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // /webapp/member/message.jsp
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
  
    
// ±ÇÇÑ, ÀÎÁõ Ãß°¡ ---------------------------------------------------------    
    memberVO.setAuth(Tool.key()); // ABC012345678901234
    memberVO.setDroupout("N");
     
    if (memberDAO.admin_search("M") == 0){ // ÄÃ·³¸í, ¸¶½ºÅÍ °èÁ¤
      msgs.add("ÃÖÃÊ µî·Ï °èÁ¤ÀÓÀ¸·Î Master °èÁ¤ÀÔ´Ï´Ù.<br><br>");
      memberVO.setAct("M");  // ÃÖ°í °ü¸®ÀÚ ÁöÁ¤
      memberVO.setConfirm("Y"); //  ÃÖ°í °ü¸®ÀÚÀÓÀ¸·Î ¸ŞÀÏ È®ÀÎ Ã³¸®
    }else{
      memberVO.setAct("H");  // °ü¸®ÀÚ°¡ ½ÂÀÎÀÌ ÇÊ¿äÇÔ, H: hold.
      memberVO.setConfirm("N"); // ¸ŞÀÏ È®ÀÎ ¾ÈµÊ, °¡ÀÔÀÚ°¡ ¸ŞÀÏ È®ÀÎ ¾ÈÇÔ. 
    }
    
// ÀÌ¸ŞÀÏ ---------------------------------------------------------     
    String subject = "Blog °ü¸®ÀÚ ¸ŞÀÏ ÀÎÁõÀÔ´Ï´Ù.";  // Á¦¸ñ
    String content = "¸ŞÀÏ ÀÎÁõ<br><br>";  // ³»¿ë
    content += "¾Æ·¡ÀÇ ¸µÅ©¸¦ Å¬¸¯ÇÏ¸é °¡ÀÔÀÌ ¿Ï·áµË´Ï´Ù.<br><br>";
    // http://172.16.12.1:9090/admin_v1jq/admin1/confirm.jsp?email=testcell2010@gmail.com&auth=ABC1234567890

    content += "http://localhost:9090/junggo/member/confirm.do?email=" + memberVO.getEmail() + "&auth=" + memberVO.getAuth();

    // mw-002.cafe24.com, Cafe24
    String host = "mw-002.cafe24.com";    // smtp mail server(¼­¹ö°ü¸®ÀÚ)     
    String from = "chanmi_blog@gmail.com";   // º¸³»´Â ÁÖ¼Ò, ºí·Î±× °ü¸®ÀÚ ÁÖ¼Ò
    String to = memberVO.getEmail();    // ¹Ş´Â »ç¶÷

    Properties props = new Properties();  // SMTP ÇÁ·ÎÅäÄİ »ç¿ë, port 25
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.auth","true");

    Authenticator authenticator = new MyAuthentication();
    Session sess = Session.getInstance(props, authenticator);   // °èÁ¤ ÀÎÁõ °Ë»ç

    try {
      Message msg = new MimeMessage(sess);   // ¸ŞÀÏ ³»¿ë °´Ã¼ »ı¼º
      msg.setFrom(new InternetAddress(from));   // º¸³»´Â »ç¶÷ ¼³Á¤
            
      // ÇÑ¸í¿¡°Ô¸¸ º¸³¿
      InternetAddress[] address = {new InternetAddress(to)}; // ¹Ş´Â »ç¶÷ ¼³Á¤
      
      msg.setRecipients(Message.RecipientType.TO, address); // ¼ö·ÉÀÎ ÁÖ¼Ò ¼³Á¤
            
      msg.setSubject(subject);                  // Á¦¸ñ ¼³Á¤ 
      msg.setSentDate(new Date());          // º¸³½ ³¯Â¥ ¼³Á¤
            
      // msg.setText(msgText); // ¸ŞÀÏ ³»¿ëÀ¸·Î ¹®ÀÚ¸¸ º¸³¾ °æ¿ì

      // º¸³»´Â ³»¿ëÀ¸·Î HTML Çü½ÄÀ¸·Î º¸³¾ °æ¿ì
      msg.setContent(content, "text/html;charset=utf-8");
            
      Transport.send(msg);  // ¸ŞÀÏ Àü¼Û

      msgs.add("<u>ÀÎÁõ ¸ŞÀÏÀÌ ¹ß¼ÛµÇ¾î½À´Ï´Ù.</u><br><br>");
      msgs.add("<u>¸ŞÀÏÀ» ¿­°í ¸µÅ©¸¦ Å¬¸¯ÇØÁÖ¼¼¿ä.</u><br>");
      
    } catch (MessagingException mex) {
      System.out.println(mex.getMessage());
      // out.println(mex.getMessage()+"<br><br>");
      // out.println(to + "´Ô¿¡°Ô ¸ŞÀÏ ¹ß¼ÛÀ» ½ÇÆĞ Çß½À´Ï´Ù.");
    }
// ---------------------------------------------------------     
    
    if (memberDAO.create(memberVO) == 1) {
      msgs.add("È¸¿ø°¡ÀÔÀÌ Ã³¸® µÇ¾ú½À´Ï´Ù.");
      msgs.add("°¡ÀÔÇØÁÖ¼Å¼­ °¨»çÇÕ´Ï´Ù.");
      msgs.add("ÀÌ¸ŞÀÏ ÀÎÁõ ½Ã ·Î±×ÀÎÀÌ °¡´ÉÇÕ´Ï´Ù.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">·Î±×ÀÎ</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">È¨ÆäÀÌÁö</button>");
    } else {
      msgs.add("È¸¿ø °¡ÀÔ¿¡ ½ÇÆĞÇß½À´Ï´Ù.");
      msgs.add("ÁË¼ÛÇÏÁö¸¸ ´Ù½ÃÇÑ¹ø ½ÃµµÇØÁÖ¼¼¿ä.");
      links.add("<button type='button' onclick=\"history.back()\">´Ù½Ã½Ãµµ</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">È¨ÆäÀÌÁö</button>");
    }
 
    links.add("<button type='button' onclick=\"location.href='./list.do'\">¸ñ·Ï</button>");
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
 
  /**
   * Áßº¹ ¾ÆÀÌµğ¸¦ °Ë»çÇÕ´Ï´Ù.
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
   * Áßº¹ ´Ğ³×ÀÓ¸¦ °Ë»çÇÕ´Ï´Ù.
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
   * Áßº¹ ÀÌ¸ŞÀÏÀ» °Ë»çÇÕ´Ï´Ù.
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
   * ÀüÃ¼ ¸ñ·ÏÀ» Ãâ·ÂÇÕ´Ï´Ù.
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
      msgs.add("È¸¿øÁ¤º¸°¡ ¼öÁ¤µÇ¾ú½À´Ï´Ù.");
      links.add("<button type='button' onclick=\"location.href='./read.do?mno="+memberVO.getMno()+"'\">º¯°æµÈ È¸¿øÁ¤º¸ È®ÀÎ</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">È¨ÆäÀÌÁö</button>");
    } else {
      msgs.add("È¸¿ø Á¤º¸ º¯°æ¿¡ ½ÇÆĞÇß½À´Ï´Ù.");
      msgs.add("ÁË¼ÛÇÏÁö¸¸ ´Ù½ÃÇÑ¹ø ½ÃµµÇØÁÖ¼¼¿ä.");
      links.add("<button type='button' onclick=\"history.back()\">´Ù½Ã½Ãµµ</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">È¨ÆäÀÌÁö</button>");
    }
 
    links.add("<button type='button' onclick=\"location.href='./list.do'\">¸ñ·Ï</button>");
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
 
  /**
   * ÀÌ¸ŞÀÏ ÀÎÁõ ÈÄ Ã³¸®
   * confirm(ÀÎÁõ ¿©ºÎ), act(±ÇÇÑ) Y·Î º¯°æ
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
      msgs.add("ÀÌ¸ŞÀÏ ÀÎÁõÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">·Î±×ÀÎ</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">È¨ÆäÀÌÁö</button>");
    } else {
      msgs.add("ÀÌ¸ŞÀÏ ÀÎÁõÀÌ ½ÇÆĞÇß½À´Ï´Ù.");
      msgs.add("ÁË¼ÛÇÏÁö¸¸ ´Ù½ÃÇÑ¹ø ½ÃµµÇØÁÖ¼¼¿ä.");
      links.add("<button type='button' onclick=\"history.back()\">´Ù½Ã½Ãµµ</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">È¨ÆäÀÌÁö</button>");
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
      if ("MY".indexOf(act) >= 0){ // ·Î±×ÀÎ ±ÇÇÑ ÀÖÀ½. M: Master, Y:ÀÏ¹İ È¸¿ø
        System.out.println("act:"+ ("MY".indexOf(act)));
        System.out.println("act:"+ ("MY".indexOf("M")));
        System.out.println("act:"+ ("MY".indexOf("Y")));
        
        session.setAttribute("userid", memberVO.getUserid());
        session.setAttribute("pwd", memberVO.getPwd());
        session.setAttribute("act", act);
        session.setAttribute("mno", mno);
        // ------------------------------------------------------------------
        // userid ÀúÀå °ü·Ã ÄíÅ° ÀúÀå
        // ------------------------------------------------------------------
        String userid_save = Tool.checkNull(memberVO.getUserid_save());
        if (userid_save.equals("Y")){ // id ÀúÀå ÇÒ °æ¿ì
          Cookie ck_userid = new Cookie("ck_userid", memberVO.getUserid()); // id ÀúÀå
          ck_userid.setMaxAge(600); // ÃÊ ´ÜÀ§, 10ºĞ
          response.addCookie(ck_userid);
        }else{ // id¸¦ ÀúÀåÇÏÁö ¾ÊÀ» °æ¿ì
          Cookie ck_userid = new Cookie("ck_userid", ""); 
          ck_userid.setMaxAge(0); // ÄíÅ° »èÁ¦
          response.addCookie(ck_userid);
        }
        // id ÀúÀå ¿©ºÎ¸¦ °áÁ¤ÇÏ´Â Äí±â ±â·Ï, Y or "" ÀúÀå
        Cookie ck_userid_save = new Cookie("ck_userid_save", memberVO.getUserid_save());
        ck_userid_save.setMaxAge(600); // ÃÊ
        response.addCookie(ck_userid_save);
        // ------------------------------------------------------------------
      
        // ------------------------------------------------------------------
        // pwd ÀúÀå °ü·Ã ÄíÅ° ÀúÀå
        // ------------------------------------------------------------------
        String pwd_save = Tool.checkNull(memberVO.getPwd_save());
        if (pwd_save.equals("Y")){ 
          Cookie ck_pwd = new Cookie("ck_pwd", memberVO.getPwd()); 
          ck_pwd.setMaxAge(600); // ÃÊ
          response.addCookie(ck_pwd);
      
        }else{ // passwd¸¦ ÀúÀåÇÏÁö ¾ÊÀ» °æ¿ì
          Cookie ck_pwd = new Cookie("ck_pwd", "");
          ck_pwd.setMaxAge(0); // ÃÊ
          response.addCookie(ck_pwd);
      
        }
        // pwd ÀúÀå ¿©ºÎ¸¦ °áÁ¤ÇÏ´Â ÄíÅ° ±â·Ï, Y or "" ÀúÀå
        Cookie ck_pwd_save = new Cookie("ck_pwd_save", memberVO.getPwd_save());
        ck_pwd_save.setMaxAge(600); // ÃÊ
        response.addCookie(ck_pwd_save);
        // ------------------------------------------------------------------
      
        String url_address = Tool.checkNull(memberVO.getUrl_address());
        if (url_address.length() > 0){
          mav.setViewName("redirect:" + memberVO.getUrl_address());
        }else{
          System.out.println("--> index.jsp ÆäÀÌÁö·Î ÀÌµ¿ÇÕ´Ï´Ù.");
          mav.setViewName("redirect:/index.jsp"); // È®ÀåÀÚ ¸í½Ã
        }
    
        } else {
          mav.setViewName("/member/message");
          msgs.add("ÇöÀç °èÁ¤ÀÌ »ç¿ë ºÒ°¡ÇÕ´Ï´Ù.");
          msgs.add("°ü¸®ÀÚ¿¡°Ô ¹®ÀÇÇØÁÖ¼¼¿ä.");
          links.add("<button type='button' onclick=\"history.back()\">´Ù½Ã½Ãµµ</button>");
          links.add("<button type='button' onclick=\"location.href='./home.do'\">È¨ÆäÀÌÁö</button>");
        }
      
      } else {
        mav.setViewName("/member/message");
        msgs.add("·Î±×ÀÎ¿¡ ½ÇÆĞÇß½À´Ï´Ù.");
        msgs.add("ÁË¼ÛÇÏÁö¸¸ ´Ù½ÃÇÑ¹ø ½ÃµµÇØÁÖ¼¼¿ä.");
        links.add("<button type='button' onclick=\"history.back()\">´Ù½Ã½Ãµµ</button>");
        links.add("<button type='button' onclick=\"location.href='./home.do'\">È¨ÆäÀÌÁö</button>");
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
  
    msgs.add("ÀÌ¿ëÇØÁÖ¼Å¼­ °¨»çÇÕ´Ï´Ù.");
    links.add("<button type='button' onclick=\"location.href='../index.do'\">È¨ÆäÀÌÁö</button>");
  
    session.invalidate(); // ¸ğµç session º¯¼ö »èÁ¦
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }  

}





//javamail lib ÀÌ ÇÊ¿äÇÕ´Ï´Ù.
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
    mav.setViewName("/index"); // memberì— create.doê°€ ë“¤ì–´ì˜¬ ê²½ìš° ì´ë™ -> /webapp/member/create.jsp
 
    return mav;
  }
  
  @RequestMapping(value = "/member/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/create"); // memberì— create.doê°€ ë“¤ì–´ì˜¬ ê²½ìš° ì´ë™ -> /webapp/member/create.jsp
 
    return mav;
  }
 
  @RequestMapping(value = "/member/create.do", method = RequestMethod.POST)
  public ModelAndView create(MemberVO memberVO) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // /webapp/member/message.jsp
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
  
    
// ê¶Œí•œ, ì¸ì¦ ì¶”ê°€ ---------------------------------------------------------    
    memberVO.setAuth(Tool.key()); // ABC012345678901234
    memberVO.setDropout("N");
     
    if (memberDAO.admin_search("M") == 0){ // ì»¬ëŸ¼ëª…, ë§ˆìŠ¤í„° ê³„ì •
      msgs.add("ìµœì´ˆ ë“±ë¡ ê³„ì •ì„ìœ¼ë¡œ Master ê³„ì •ì…ë‹ˆë‹¤.<br><br>");
      memberVO.setAct("M");  // ìµœê³  ê´€ë¦¬ì ì§€ì •
      memberVO.setConfirm("Y"); //  ìµœê³  ê´€ë¦¬ìì„ìœ¼ë¡œ ë©”ì¼ í™•ì¸ ì²˜ë¦¬
    }else{
      memberVO.setAct("H");  // ê´€ë¦¬ìê°€ ìŠ¹ì¸ì´ í•„ìš”í•¨, H: hold.
      memberVO.setConfirm("N"); // ë©”ì¼ í™•ì¸ ì•ˆë¨, ê°€ì…ìê°€ ë©”ì¼ í™•ì¸ ì•ˆí•¨. 
    }
    
// ì´ë©”ì¼ ---------------------------------------------------------     
    String subject = "Blog ê´€ë¦¬ì ë©”ì¼ ì¸ì¦ì…ë‹ˆë‹¤.";  // ì œëª©
    String content = "ë©”ì¼ ì¸ì¦<br><br>";  // ë‚´ìš©
    content += "ì•„ë˜ì˜ ë§í¬ë¥¼ í´ë¦­í•˜ë©´ ê°€ì…ì´ ì™„ë£Œë©ë‹ˆë‹¤.<br><br>";
    // http://172.16.12.1:9090/admin_v1jq/admin1/confirm.jsp?email=testcell2010@gmail.com&auth=ABC1234567890

    content += "http://localhost:9090/tmember/member/confirm.do?email=" + memberVO.getEmail() + "&auth=" + memberVO.getAuth();

    // mw-002.cafe24.com, Cafe24
    String host = "mw-002.cafe24.com";    // smtp mail server(ì„œë²„ê´€ë¦¬ì)     
    String from = "chanmi_blog@gmail.com";   // ë³´ë‚´ëŠ” ì£¼ì†Œ, ë¸”ë¡œê·¸ ê´€ë¦¬ì ì£¼ì†Œ
    String to = memberVO.getEmail();    // ë°›ëŠ” ì‚¬ëŒ

    Properties props = new Properties();  // SMTP í”„ë¡œí† ì½œ ì‚¬ìš©, port 25
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.auth","true");

    Authenticator authenticator = new MyAuthentication();
    Session sess = Session.getInstance(props, authenticator);   // ê³„ì • ì¸ì¦ ê²€ì‚¬

    try {
      Message msg = new MimeMessage(sess);   // ë©”ì¼ ë‚´ìš© ê°ì²´ ìƒì„±
      msg.setFrom(new InternetAddress(from));   // ë³´ë‚´ëŠ” ì‚¬ëŒ ì„¤ì •
            
      // í•œëª…ì—ê²Œë§Œ ë³´ëƒ„
      InternetAddress[] address = {new InternetAddress(to)}; // ë°›ëŠ” ì‚¬ëŒ ì„¤ì •
      
      msg.setRecipients(Message.RecipientType.TO, address); // ìˆ˜ë ¹ì¸ ì£¼ì†Œ ì„¤ì •
            
      msg.setSubject(subject);                  // ì œëª© ì„¤ì • 
      msg.setSentDate(new Date());          // ë³´ë‚¸ ë‚ ì§œ ì„¤ì •
            
      // msg.setText(msgText); // ë©”ì¼ ë‚´ìš©ìœ¼ë¡œ ë¬¸ìë§Œ ë³´ë‚¼ ê²½ìš°

      // ë³´ë‚´ëŠ” ë‚´ìš©ìœ¼ë¡œ HTML í˜•ì‹ìœ¼ë¡œ ë³´ë‚¼ ê²½ìš°
      msg.setContent(content, "text/html;charset=utf-8");
            
      Transport.send(msg);  // ë©”ì¼ ì „ì†¡

      msgs.add("<u>ì¸ì¦ ë©”ì¼ì´ ë°œì†¡ë˜ì–´ìŠµë‹ˆë‹¤.</u><br><br>");
      msgs.add("<u>ë©”ì¼ì„ ì—´ê³  ë§í¬ë¥¼ í´ë¦­í•´ì£¼ì„¸ìš”.</u><br>");
      
    } catch (MessagingException mex) {
      System.out.println(mex.getMessage());
      // out.println(mex.getMessage()+"<br><br>");
      // out.println(to + "ë‹˜ì—ê²Œ ë©”ì¼ ë°œì†¡ì„ ì‹¤íŒ¨ í–ˆìŠµë‹ˆë‹¤.");
    }
// ---------------------------------------------------------     
    
    if (memberDAO.create(memberVO) == 1) {
      msgs.add("íšŒì›ê°€ì…ì´ ì²˜ë¦¬ ë˜ì—ˆìŠµë‹ˆë‹¤.");
      msgs.add("ê°€ì…í•´ì£¼ì…”ì„œ ê°ì‚¬í•©ë‹ˆë‹¤.");
      msgs.add("ì´ë©”ì¼ ì¸ì¦ ì‹œ ë¡œê·¸ì¸ì´ ê°€ëŠ¥í•©ë‹ˆë‹¤.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">ë¡œê·¸ì¸</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">í™ˆí˜ì´ì§€</button>");
    } else {
      msgs.add("íšŒì› ê°€ì…ì— ì‹¤íŒ¨í–ˆìŠµë‹ˆë‹¤.");
      msgs.add("ì£„ì†¡í•˜ì§€ë§Œ ë‹¤ì‹œí•œë²ˆ ì‹œë„í•´ì£¼ì„¸ìš”.");
      links.add("<button type='button' onclick=\"history.back()\">ë‹¤ì‹œì‹œë„</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">í™ˆí˜ì´ì§€</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
 
  /**
   * ì¤‘ë³µ ì•„ì´ë””ë¥¼ ê²€ì‚¬í•©ë‹ˆë‹¤.
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
   * ì¤‘ë³µ ë‹‰ë„¤ì„ë¥¼ ê²€ì‚¬í•©ë‹ˆë‹¤.
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
   * ì¤‘ë³µ ì´ë©”ì¼ì„ ê²€ì‚¬í•©ë‹ˆë‹¤.
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
   * ìˆ˜ì • ì‹œ ì¤‘ë³µ ì´ë©”ì¼ì„ ê²€ì‚¬í•©ë‹ˆë‹¤.
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
   * ìˆ˜ì • ì‹œ ì¤‘ë³µ ì´ë©”ì¼ì„ ê²€ì‚¬í•©ë‹ˆë‹¤.
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
   * ì „ì²´ ëª©ë¡ì„ ì¶œë ¥í•©ë‹ˆë‹¤.
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
   * ì´ë©”ì¼ ì¸ì¦ í›„ ì²˜ë¦¬
   * confirm(ì¸ì¦ ì—¬ë¶€), act(ê¶Œí•œ) Yë¡œ ë³€ê²½
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
      msgs.add("ì´ë©”ì¼ ì¸ì¦ì´ ì™„ë£Œë˜ì—ˆìŠµë‹ˆë‹¤.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">ë¡œê·¸ì¸</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">í™ˆí˜ì´ì§€</button>");
    } else {
      msgs.add("ì´ë©”ì¼ ì¸ì¦ì´ ì‹¤íŒ¨í–ˆìŠµë‹ˆë‹¤.");
      msgs.add("ì£„ì†¡í•˜ì§€ë§Œ ë‹¤ì‹œí•œë²ˆ ì‹œë„í•´ì£¼ì„¸ìš”.");
      links.add("<button type='button' onclick=\"history.back()\">ë‹¤ì‹œì‹œë„</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">í™ˆí˜ì´ì§€</button>");
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
      if ("MY".indexOf(act) >= 0){ // ë¡œê·¸ì¸ ê¶Œí•œ ìˆìŒ. M: Master, Y:ì¼ë°˜ íšŒì›
        session.setAttribute("userid", memberVO.getUserid());
        session.setAttribute("pwd", memberVO.getPwd());
        session.setAttribute("act", act);
        session.setAttribute("mno", mno);
        // ------------------------------------------------------------------
        // userid ì €ì¥ ê´€ë ¨ ì¿ í‚¤ ì €ì¥
        // ------------------------------------------------------------------
        String userid_save = Tool.checkNull(memberVO.getUserid_save());
        if (userid_save.equals("Y")){ // id ì €ì¥ í•  ê²½ìš°
          Cookie ck_userid = new Cookie("ck_userid", memberVO.getUserid()); // id ì €ì¥
          ck_userid.setMaxAge(600); // ì´ˆ ë‹¨ìœ„, 10ë¶„
          response.addCookie(ck_userid);
        }else{ // idë¥¼ ì €ì¥í•˜ì§€ ì•Šì„ ê²½ìš°
          Cookie ck_userid = new Cookie("ck_userid", ""); 
          ck_userid.setMaxAge(0); // ì¿ í‚¤ ì‚­ì œ
          response.addCookie(ck_userid);
        }
        // id ì €ì¥ ì—¬ë¶€ë¥¼ ê²°ì •í•˜ëŠ” ì¿ ê¸° ê¸°ë¡, Y or "" ì €ì¥
        Cookie ck_userid_save = new Cookie("ck_userid_save", memberVO.getUserid_save());
        ck_userid_save.setMaxAge(600); // ì´ˆ
        response.addCookie(ck_userid_save);
        // ------------------------------------------------------------------
      
        // ------------------------------------------------------------------
        // pwd ì €ì¥ ê´€ë ¨ ì¿ í‚¤ ì €ì¥
        // ------------------------------------------------------------------
        String pwd_save = Tool.checkNull(memberVO.getPwd_save());
        if (pwd_save.equals("Y")){ 
          Cookie ck_pwd = new Cookie("ck_pwd", memberVO.getPwd()); 
          ck_pwd.setMaxAge(600); // ì´ˆ
          response.addCookie(ck_pwd);
      
        }else{ // passwdë¥¼ ì €ì¥í•˜ì§€ ì•Šì„ ê²½ìš°
          Cookie ck_pwd = new Cookie("ck_pwd", "");
          ck_pwd.setMaxAge(0); // ì´ˆ
          response.addCookie(ck_pwd);
      
        }
        // pwd ì €ì¥ ì—¬ë¶€ë¥¼ ê²°ì •í•˜ëŠ” ì¿ í‚¤ ê¸°ë¡, Y or "" ì €ì¥
        Cookie ck_pwd_save = new Cookie("ck_pwd_save", memberVO.getPwd_save());
        ck_pwd_save.setMaxAge(600); // ì´ˆ
        response.addCookie(ck_pwd_save);
        // ------------------------------------------------------------------
      
        String url_address = Tool.checkNull(memberVO.getUrl_address());
        if (url_address.length() > 0){
          mav.setViewName("redirect:" + memberVO.getUrl_address());
        }else{
          System.out.println("--> index.jsp í˜ì´ì§€ë¡œ ì´ë™í•©ë‹ˆë‹¤.");
          mav.setViewName("redirect:/index.jsp"); // í™•ì¥ì ëª…ì‹œ
        }
    
        } else {
          mav.setViewName("/member/message");
          msgs.add("í˜„ì¬ ê³„ì •ì´ ì‚¬ìš© ë¶ˆê°€í•©ë‹ˆë‹¤.");
          msgs.add("ê´€ë¦¬ìì—ê²Œ ë¬¸ì˜í•´ì£¼ì„¸ìš”.");
          links.add("<button type='button' onclick=\"history.back()\">ë‹¤ì‹œì‹œë„</button>");
          links.add("<button type='button' onclick=\"location.href='./home.do'\">í™ˆí˜ì´ì§€</button>");
        }
      
      } else {
        mav.setViewName("/member/message");
        msgs.add("ë¡œê·¸ì¸ì— ì‹¤íŒ¨í–ˆìŠµë‹ˆë‹¤.");
        msgs.add("ì£„ì†¡í•˜ì§€ë§Œ ë‹¤ì‹œí•œë²ˆ ì‹œë„í•´ì£¼ì„¸ìš”.");
        links.add("<button type='button' onclick=\"history.back()\">ë‹¤ì‹œì‹œë„</button>");
        links.add("<button type='button' onclick=\"location.href='./home.do'\">í™ˆí˜ì´ì§€</button>");
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
  
    msgs.add("ì´ìš©í•´ì£¼ì…”ì„œ ê°ì‚¬í•©ë‹ˆë‹¤.");
    links.add("<button type='button' onclick=\"location.href='../index.do'\">í™ˆí˜ì´ì§€</button>");
  
    session.invalidate(); // ëª¨ë“  session ë³€ìˆ˜ ì‚­ì œ
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  } 
  
  /**
   * íƒˆí‡´ ì‹ ì²­
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
      msgs.add("íƒˆí‡´ê°€ ì •ìƒì ìœ¼ë¡œ ì²˜ë¦¬ë˜ì—ˆìŠµë‹ˆë‹¤.");
      msgs.add("ì´ìš©í•´ ì£¼ì…”ì„œ ê°ì‚¬í•©ë‹ˆë‹¤.");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">í™ˆí˜ì´ì§€</button>");
      links.add("<button type='button' onclick=\"location.href='./create.do'\">íšŒì›ê°€ì…</button>");
    } else {
      msgs.add("ì£„ì†¡í•˜ì§€ë§Œ ë‹¤ì‹œí•œë²ˆ ì‹œë„í•´ì£¼ì„¸ìš”.");
      links.add("<button type='button' onclick=\"history.back()\">ë‹¤ì‹œì‹œë„</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">í™ˆí˜ì´ì§€</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
  
  /**
   * íŒ¨ìŠ¤ì›Œë“œ ë³€ê²½ í¼ ì¶œë ¥
   * @param mno íšŒì› ë²ˆí˜¸
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
    // í˜„ì¬ íŒ¨ìŠ¤ì›Œë“œ ì¼ì¹˜ ì—¬ë¶€ ê²€ì‚¬
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
   * íŒ¨ìŠ¤ì›Œë“œ ì‚­ì œ í¼ ì¶œë ¥
   * @param mno ì‚­ì œí•  ê¸€ ë²ˆí˜¸
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
  * íšŒì› ì •ë³´ ìˆ˜ì •
  * ì´ë©”ì¼ ë³€ê²½ ì‹œ ì¬ì¸ì¦ í•„ìš”
  * @param memberVO
  * @param updateFlag
  * @return
  */
  @RequestMapping(value = "/member/update.do", method = RequestMethod.POST)
  public ModelAndView emailConfirm(MemberVO memberVO, String updateFlag, HttpSession session) {
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if(updateFlag.equals("1")) { // ì´ë©”ì¼ì´ ë³€ê²½ ë˜ì—ˆë‹¤ë©´ ì‹¤í–‰
      // ë¡œê·¸ì¸ ë¶ˆê°€ëŠ¥ ìƒíƒœ
      memberVO.setConfirm("N");
      memberVO.setAct("H");
      
        
  // ì´ë©”ì¼ ---------------------------------------------------------     
      String subject = "Blog ê´€ë¦¬ì ë©”ì¼ ì¸ì¦ì…ë‹ˆë‹¤.";  // ì œëª©
      String content = "ë©”ì¼ ì¸ì¦<br><br>";  // ë‚´ìš©
      content += "ì•„ë˜ì˜ ë§í¬ë¥¼ í´ë¦­í•˜ë©´ ê°€ì…ì´ ì™„ë£Œë©ë‹ˆë‹¤.<br><br>";
      // http://172.16.12.1:9090/admin_v1jq/admin1/confirm.jsp?email=testcell2010@gmail.com&auth=ABC1234567890
  
      content += "http://localhost:9090/tmember/member/confirm.do?email=" + memberVO.getEmail() + "&auth=" + memberVO.getAuth();
  
      // mw-002.cafe24.com, Cafe24
      String host = "mw-002.cafe24.com";    // smtp mail server(ì„œë²„ê´€ë¦¬ì)     
      String from = "chanmi_blog@gmail.com";   // ë³´ë‚´ëŠ” ì£¼ì†Œ, ë¸”ë¡œê·¸ ê´€ë¦¬ì ì£¼ì†Œ
      String to = memberVO.getEmail();    // ë°›ëŠ” ì‚¬ëŒ
  
      Properties props = new Properties();  // SMTP í”„ë¡œí† ì½œ ì‚¬ìš©, port 25
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.auth","true");
  
      Authenticator authenticator = new MyAuthentication();
      Session sess = Session.getInstance(props, authenticator);   // ê³„ì • ì¸ì¦ ê²€ì‚¬
  
      try {
        Message msg = new MimeMessage(sess);   // ë©”ì¼ ë‚´ìš© ê°ì²´ ìƒì„±
        msg.setFrom(new InternetAddress(from));   // ë³´ë‚´ëŠ” ì‚¬ëŒ ì„¤ì •
              
        // í•œëª…ì—ê²Œë§Œ ë³´ëƒ„
        InternetAddress[] address = {new InternetAddress(to)}; // ë°›ëŠ” ì‚¬ëŒ ì„¤ì •
        
        msg.setRecipients(Message.RecipientType.TO, address); // ìˆ˜ë ¹ì¸ ì£¼ì†Œ ì„¤ì •
              
        msg.setSubject(subject);                  // ì œëª© ì„¤ì • 
        msg.setSentDate(new Date());          // ë³´ë‚¸ ë‚ ì§œ ì„¤ì •
              
        // msg.setText(msgText); // ë©”ì¼ ë‚´ìš©ìœ¼ë¡œ ë¬¸ìë§Œ ë³´ë‚¼ ê²½ìš°
  
        // ë³´ë‚´ëŠ” ë‚´ìš©ìœ¼ë¡œ HTML í˜•ì‹ìœ¼ë¡œ ë³´ë‚¼ ê²½ìš°
        msg.setContent(content, "text/html;charset=utf-8");
              
        Transport.send(msg);  // ë©”ì¼ ì „ì†¡
  
        msgs.add("<u>ì´ë©”ì¼ ì¬ì¸ì¦ì´ í•„ìš”í•©ë‹ˆë‹¤.</u><br><br>");
        msgs.add("<u>ë©”ì¼ì„ ì—´ê³  ë§í¬ë¥¼ í´ë¦­í•´ì£¼ì„¸ìš”.</u><br>");
        
      } catch (MessagingException mex) {
        System.out.println(mex.getMessage());
        // out.println(mex.getMessage()+"<br><br>");
        // out.println(to + "ë‹˜ì—ê²Œ ë©”ì¼ ë°œì†¡ì„ ì‹¤íŒ¨ í–ˆìŠµë‹ˆë‹¤.");
      }
      
      } // ifë¬¸
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
 
    if (memberDAO.update(memberVO) == 1) {
      msgs.add("íšŒì›ì •ë³´ê°€ ìˆ˜ì •ë˜ì—ˆìŠµë‹ˆë‹¤.");
      msgs.add("ë‹¤ì‹œ ë¡œê·¸ì¸ í•´ì£¼ì„¸ìš”.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">ë¡œê·¸ì¸</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">í™ˆí˜ì´ì§€</button>");
    } else {
      msgs.add("íšŒì› ì •ë³´ ë³€ê²½ì— ì‹¤íŒ¨í–ˆìŠµë‹ˆë‹¤.");
      msgs.add("ì£„ì†¡í•˜ì§€ë§Œ ë‹¤ì‹œí•œë²ˆ ì‹œë„í•´ì£¼ì„¸ìš”.");
      links.add("<button type='button' onclick=\"history.back()\">ë‹¤ì‹œì‹œë„</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">í™ˆí˜ì´ì§€</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    session.invalidate();
    
    return mav;
    
  }

}





//javamail lib ì´ í•„ìš”í•©ë‹ˆë‹¤.
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