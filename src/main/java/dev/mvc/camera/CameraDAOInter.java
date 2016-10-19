package dev.mvc.camera;

import java.util.List;

import dev.mvc.tmember.MemberVO;




public interface CameraDAOInter {
  
  /**
   * ���ڵ带 ����մϴ�.
   * <insert id="create" parameterType="CameraVO">
   * @param vo ����� ������ ��ü
   * @return ��ϵ� ���ڵ� ��
   */
  public int create(CameraVO vo);
  
  
  
  /**
   * ��ü ���
   * <select id="list" resultType="CameraVO">
   * @return �Խñ� ���
   */
  public List<CameraVO> list();
  
  /**
   * ī�޶�� ���� ��ȸ
   * <select id="read" resultType="CameraVO" parameterType="int">
   * @param ctno
   * @return
   */
  public CameraVO read(int ctno); 
 
  /**
   * ȸ������(�г���, �̸���) ��������
   * @param userid
   * @return
   */
  public MemberVO test(String userid);
  
  /**
   * ī�޶�� ������Ʈ
   *  <update id='update' parameterType="CameraVO">
   * @param cameraVO
   * @return
   */
  public int update(CameraVO cameraVO);
  
  /**
   * ī�޶� �ۻ���
   * <delete id="delete" parameterType="int">
   * @param ctno
   * @return
   */
  public int delete(int ctno);
}
