package dev.mvc.camera;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import dev.mvc.tmember.MemberDAOInter;
import dev.mvc.tmember.MemberVO;



import org.springframework.beans.factory.annotation.Qualifier;

@Controller
public class CameraCont {
  @Autowired
  @Qualifier("dev.mvc.camera.CameraDAO")
  private CameraDAOInter cameraDAO;
  

  public CameraCont() {
    System.out.println("--> CameraCont created."); 
  }
  
  
  /**
   * 전체 목록을 출력합니다.
   * 
   * @return
   */
  @RequestMapping(value = "/camera/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/list"); // /webapp/member/list.jsp
    mav.addObject("list", cameraDAO.list());

    return mav;
  }

  @RequestMapping(value = "/camera/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/create"); // /webapp/member/create.jsp
  
    String userid = session.getAttribute("userid").toString();
    MemberVO memberVO = cameraDAO.test(userid);
    
    mav.addObject("memberVO", memberVO);
    mav.addObject("userid", userid);
    System.out.println(memberVO);
    return mav;
  }

  @RequestMapping(value = "/camera/create.do", method = RequestMethod.POST)
  public ModelAndView create(CameraVO cameraVO) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/message"); // webapp/member/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    if (cameraDAO.create(cameraVO) == 1) {
      msgs.add("등록 처리 완료했습니다.");
      msgs.add("감사합니다.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">로그인</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    } else {
      msgs.add("등록에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    }

    links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }
  
  @RequestMapping(value = "/camera/read.do", method = RequestMethod.GET)
  public ModelAndView read(int ctno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/read");
    mav.addObject("cameraVO", cameraDAO.read(ctno));
    
    return mav;
  }
  
  /**
   * 수정폼
   * @param ctno
   * @return
   */
  @RequestMapping(value="/camera/update.do", 
      method=RequestMethod.GET)
  public ModelAndView update(int ctno){  
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/update"); 
    mav.addObject("cameraVO", cameraDAO.read(ctno)); 
    return mav;
 
  }
  
  /**
   * 글과 파일을 수정 처리
   * 
   * @param cameraVO
   * @return
   */
  @RequestMapping(value = "/camera/update.do", method = RequestMethod.POST)
  public ModelAndView update(CameraVO cameraVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
 
    if (cameraDAO.update(cameraVO) == 1) {
      // 수정후 조회로 자동 이동
      mav.setViewName("redirect:/camera/read.do?ctno=" + cameraVO.getCtno());
    } else {
      msgs.add("게시판 수정에 실패 하셨습니다.");
      links
          .add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
      links
          .add("<button type='button' onclick=\"location.href='./list.do'>목록</button>");
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
 
    return mav;
  }
  
  /**
   * 삭제폼
   * @param ctno
   * @return
   */
  @RequestMapping(value = "/camera/delete.do", 
                              method = RequestMethod.GET)
  public ModelAndView delete(int ctno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/delete"); // /webapp/blog/delete.jsp
    mav.addObject("ctno", ctno);
    return mav;
  }
  
  /**
   * 삭제 처리
   * @param blogVO
   * @return
   */
  @RequestMapping(value = "/camera/delete.do", 
                             method = RequestMethod.POST)
  public ModelAndView delete(CameraVO cameraVO) {
    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (cameraDAO.delete(cameraVO.getCtno()) == 1) {
      
      mav.setViewName("redirect:/camera/list.do");//확장자 명시

    } else {
      msgs.add("글 삭제에 실패했습니다.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'>목록</button>");
    }
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
}
