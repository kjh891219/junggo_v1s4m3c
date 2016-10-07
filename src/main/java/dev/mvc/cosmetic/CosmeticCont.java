package dev.mvc.cosmetic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.cosmetic.CosmeticDAOInter;
import dev.mvc.cosmetic.CosmeticVO;

@Controller
public class CosmeticCont {
  @Autowired
  @Qualifier("dev.mvc.cosmetic.CosmeticDAO")
  private CosmeticDAOInter cosmeticDAO;
  
  public CosmeticCont(){
    System.out.println("--> CosmeticCont created.");
  }
  
  @RequestMapping(value = "/cosmetic/create.do", 
      method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called."); 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cosmetic/create"); // /webapp/code/create.jsp

    return mav;
  }
  
  @RequestMapping(value = "/cosmetic/create.do", 
      method = RequestMethod.POST)
  public ModelAndView create(CosmeticVO cosmeticVO) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cosmetic/message"); // /webapp/code/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    if (cosmeticDAO.create(cosmeticVO) == 1) {
      msgs.add("등록되었습니다.");
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
  
  /**
   * 전체 목록을 출력합니다.
   * 
   * @return
   */
  @RequestMapping(value = "/cosmetic/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cosmetic/list");  //webapp/code/list.jsp
    mav.addObject("list", cosmeticDAO.list());
 
    return mav;
  }
  
}
