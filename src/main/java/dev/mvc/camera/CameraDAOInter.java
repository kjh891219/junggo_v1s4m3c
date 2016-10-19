package dev.mvc.camera;

import java.util.List;

import dev.mvc.tmember.MemberVO;




public interface CameraDAOInter {
  
  /**
   * 레코드를 등록합니다.
   * <insert id="create" parameterType="CameraVO">
   * @param vo 등록할 데이터 객체
   * @return 등록된 레코드 수
   */
  public int create(CameraVO vo);
  
  
  
  /**
   * 전체 목록
   * <select id="list" resultType="CameraVO">
   * @return 게시글 목록
   */
  public List<CameraVO> list();
  
  /**
   * 카메라글 정보 조회
   * <select id="read" resultType="CameraVO" parameterType="int">
   * @param ctno
   * @return
   */
  public CameraVO read(int ctno); 
 
  /**
   * 회원정보(닉네임, 이메일) 가져오기
   * @param userid
   * @return
   */
  public MemberVO test(String userid);
  
  /**
   * 카메라글 업데이트
   *  <update id='update' parameterType="CameraVO">
   * @param cameraVO
   * @return
   */
  public int update(CameraVO cameraVO);
  
  /**
   * 카메라 글삭제
   * <delete id="delete" parameterType="int">
   * @param ctno
   * @return
   */
  public int delete(int ctno);
}
