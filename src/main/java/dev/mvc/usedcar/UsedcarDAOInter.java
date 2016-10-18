package dev.mvc.usedcar;

import java.util.List;

import dev.mvc.usedcar.UsedcarVO;


public interface UsedcarDAOInter {
  
  /**
   * 레코드를 등록합니다.
   * <insert id="create" parameterType="CodeVO">
   * @param vo 등록할 데이터 객체
   * @return 등록된 레코드 수
   */
  public int create(UsedcarVO vo);
  
  /**
   * 코드 전체 목록
   * <select id="list" resultType="CodeVO">
   * @return 코드 목록
   */
  public List<UsedcarVO> list();
  
  public int update(UsedcarVO usedcarVO);
}