package dev.mvc.camera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import dev.mvc.tmember.MemberVO;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

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
  public ModelAndView list(SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/list"); // /webapp/member/list.jsp
    
    // HashMap hashMap = new HashMap();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());
    
    
    
    System.out.println(searchDTO.getCol());
    System.out.println(searchDTO.getWord());
    
    
    
    List<CameraVO> list = cameraDAO.list2(hashMap);
    Iterator<CameraVO> iter = list.iterator();
    while (iter.hasNext() == true) { // 다음 요소 검사
      CameraVO vo = iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
      // vo.setFile1(Tool.textLength(10, vo.getFile1()));
      // vo.setFile2(Tool.textLength(10, vo.getFile2()));
      vo.setSize2Label(Tool.unit(vo.getSize2())); // MB 단위 변환
      vo.setSize4Label(Tool.unit(vo.getSize4()));
      
    }
    
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());
    mav.addObject("totalRecord", cameraDAO.count(hashMap));
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
  public ModelAndView create(CameraVO cameraVO, HttpServletRequest request) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera/message"); // webapp/member/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    
 // -------------------------------------------------------------------
    // 파일 전송
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";
    String upDir = Tool.getRealPath(request, "/camera/storage");
    MultipartFile file2MF = cameraVO.getFile2MF();
   
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file2MF.getSize() > 0) {
      file2 = Upload.saveFileSpring(file2MF, upDir);
      cameraVO.setFile2(file2); // 전송된 파일명 저장
      cameraVO.setSize2(file2MF.getSize());

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) {
        file1 = Tool.preview(upDir, file2, 120, 80);
      } else {
        file1 = "";
      }
      // -------------------------------------------------------------------
    }
    cameraVO.setFile1(file1); // Thumb 이미지
    cameraVO.setFile2(file2); // 원본 이미지
    // -------------------------------------------------------------------
    
 
    // Integer itg = (Integer)(session.getAttribute("mno"));
    // blogVO.setMno(itg.intValue());
    
    
    
 // -------------------------------------------------------------------
    // 파일 전송2
    // -------------------------------------------------------------------
    String file3 = "";
    String file4 = "";
    String upDir2 = Tool.getRealPath(request, "/camera/storage");
    MultipartFile file4MF = cameraVO.getFile4MF();
   
    // System.out.println("file2MF.getSize(): " + file2MF.getSize());
    if (file4MF.getSize() > 0) {
      file4 = Upload.saveFileSpring(file4MF, upDir2);
      cameraVO.setFile4(file4); // 전송된 파일명 저장
      cameraVO.setSize4(file4MF.getSize());

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file4)) {
        file3 = Tool.preview(upDir2, file4, 120, 80);
      } else {
        file3 = "";
      }
      // -------------------------------------------------------------------
    }
    cameraVO.setFile3(file3); // Thumb 이미지
    cameraVO.setFile4(file4); // 원본 이미지
    // -------------------------------------------------------------------
    
    
    
    
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
  public ModelAndView read(int ctno, SearchDTO searchDTO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    cameraDAO.increaseCnt(ctno); //조회수 증가
    mav.setViewName("/camera/read");
    CameraVO cameraVO = cameraDAO.read(ctno);
    cameraVO.setSize2Label(Tool.unit(cameraVO.getSize2()));
    cameraVO.setSize4Label(Tool.unit(cameraVO.getSize4()));
    mav.addObject("cameraVO", cameraVO);
    mav.addObject("searchDTO", searchDTO);
    mav.addObject("root", request.getContextPath());
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
    
 // -------------------------------------------------------------------
    // 파일 전송 관련
    // -------------------------------------------------------------------
    String file1 = "";
    String file2 = "";

    String upDir = Tool.getRealPath(request, "/camera/storage");
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file2MF = cameraVO.getFile2MF();
    CameraVO oldVO = cameraDAO.read(cameraVO.getCtno());

    if (file2MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir, oldVO.getFile2()); // 기존 등록된 파일 삭제
      file2 = Upload.saveFileSpring(file2MF, upDir); // 새로운 파일 저장
      cameraVO.setFile2(file2); // 새로운 파일명 저장
      cameraVO.setSize2(file2MF.getSize()); // 새로운 크기 저장

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file2)) { // 이미지인지 검사
        Tool.deleteFile(upDir, oldVO.getFile1()); // 파일 삭제
        file1 = Tool.preview(upDir, file2, 120, 80); // thumb 이미지 생성
      } else {
        file1 = "";
      }
      // -------------------------------------------------------------------

    } else {
      file1 = oldVO.getFile1(); // 파일 업로드를하지 않는 경우
      file2 = oldVO.getFile2();
    }
    cameraVO.setFile1(file1); 
    cameraVO.setFile2(file2);
    
    
    
    
    
    
 // -------------------------------------------------------------------
    // 파일 전송 관련2
    // -------------------------------------------------------------------
    String file3 = "";
    String file4 = "";

    String upDir2 = Tool.getRealPath(request, "/camera/storage");
    // <input type="file" name='file2MF' id='file2MF' size='40' >
    MultipartFile file4MF = cameraVO.getFile4MF();
    CameraVO oldVO2 = cameraDAO.read(cameraVO.getCtno());

    if (file4MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
      Tool.deleteFile(upDir2, oldVO2.getFile4()); // 기존 등록된 파일 삭제
      file4 = Upload.saveFileSpring(file4MF, upDir2); // 새로운 파일 저장
      cameraVO.setFile4(file4); // 새로운 파일명 저장
      cameraVO.setSize4(file4MF.getSize()); // 새로운 크기 저장

      // -------------------------------------------------------------------
      // Thumb 파일 생성
      // -------------------------------------------------------------------
      if (Tool.isImage(file4)) { // 이미지인지 검사
        Tool.deleteFile(upDir2, oldVO2.getFile3()); // 파일 삭제
        file3 = Tool.preview(upDir2, file4, 120, 80); // thumb 이미지 생성
      } else {
        file3 = "";
      }
      // -------------------------------------------------------------------

    } else {
      file3 = oldVO2.getFile3(); // 파일 업로드를하지 않는 경우
      file4 = oldVO2.getFile4();
    }
    cameraVO.setFile3(file3); 
    cameraVO.setFile4(file4);
    
    
    
    
    
    
 
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
