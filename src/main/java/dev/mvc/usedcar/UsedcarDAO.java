package dev.mvc.usedcar;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

@Repository("dev.mvc.usedcar.UsedcarDAO")
public class UsedcarDAO implements UsedcarDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis; 
  
  public UsedcarDAO(){ 
     System.out.println("--> UsedcarDAO created.");
  }

  @Override
  public int create(UsedcarVO vo) {
    /*      
    <mapper namespace = "code">
    <insert id="create" parameterType="CodeVO">
    */      
    return mybatis.insert("usedcar.create", vo);
  }
  
  @Override
  public List<UsedcarVO> list() {
    return mybatis.selectList("usedcar.list");
  }
  
  @Override
  public int update(UsedcarVO usedcarVO) {
    return mybatis.update("usedcar.update", usedcarVO);
  }
 
}