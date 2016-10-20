package dev.mvc.message;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.tmember.MessageDAO")
public class MessageDAO implements MessageDAOInter{
    
  @Autowired
  private SqlSessionTemplate mybatis; //MyBatis 
   
  public MessageDAO(){
     System.out.println("--> MessageDAO created.");
  }

  @Override
  public List<MessageVO> receive_list(String receiveid) {
    System.out.println("다오:"+ receiveid);
    return mybatis.selectList("message.receive_list", receiveid);
  }
}
