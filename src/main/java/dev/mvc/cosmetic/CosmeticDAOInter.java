package dev.mvc.cosmetic;

import java.util.List;

import dev.mvc.cosmetic.CosmeticVO;

public interface CosmeticDAOInter {
  /**
   * 레코드를 등록합니다.
   * <insert id="create" parameterType="CosmeticVO">
   * @param vo 등록할 데이터 객체
   * @return 등록된 레코드 수
   */
  public int create(CosmeticVO vo);
  
  /**
   * 회원 전체 목록
   * <select id="list" resultType="CosmeticVO">
   * @return 회원 목록
   */ 
  public List<CosmeticVO> list(); 
}
