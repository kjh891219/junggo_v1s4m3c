package dev.mvc.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import web.tool.Tool;


@Controller
public class MessageCont {
  @Autowired
  @Qualifier("dev.mvc.tmember.MessageDAO")
  private MessageDAOInter messageDAO;
  
  public MessageCont(){
    System.out.println("--> MessageCont created.");
  }
  
  @RequestMapping(value = "/message/receive_list.do", method = RequestMethod.GET)
  public ModelAndView receive_list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/message/list_message"); //  /webapp/member/list.jsp
    
    String userid = (String) session.getAttribute("userid");
    
    List<MessageVO> list =  messageDAO.receive_list(userid);
    Iterator<MessageVO> iter = list.iterator();

    int cnt = 0;
    
    while(iter.hasNext() == true){  // 다음 요소 검사
      MessageVO vo = iter.next();  // 요소 추출
      vo.setMsg_no(vo.getMsg_no()); 
      vo.setSendid(vo.getSendid()); 
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));  
      vo.setContent(Tool.textLength(vo.getContent(), 10));
      vo.setMsg_date(vo.getMsg_date().substring(0, 10));  // 년월일
      cnt ++;
    }
    mav.addObject("list", list);
    mav.addObject("cnt", cnt);
    
    return mav;
  }
  
  
  
}
