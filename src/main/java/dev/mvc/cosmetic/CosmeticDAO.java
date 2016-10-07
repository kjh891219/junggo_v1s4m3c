package dev.mvc.cosmetic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.cosmetic.CosmeticDAO")
public class CosmeticDAO implements CosmeticDAOInter {
  
  @Autowired
  private SqlSessionTemplate mybatis; // MyBATIS 3 ¿¬°á °´Ã¼
  
  public CosmeticDAO(){
    System.out.println("--> CosmeticDAO created.");  
  }
  
  @Override
  public int create(CosmeticVO vo) {
    return mybatis.insert("cosmetic.create", vo);
  }

  @Override
  public List<CosmeticVO> list() {
    return mybatis.selectList("cosmetic.list");
  }

}
