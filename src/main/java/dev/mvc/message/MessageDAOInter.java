package dev.mvc.message;

import java.util.List;

public interface MessageDAOInter {
  

  /**
   * 메시지 전송(쪽지함 삽입) 
    <insert id="create" parameterType="MessageVO">
   * @param messageVO
   */
/*  public void create(MessageVO messageVO);*/
  
  /**
   * 받은 메시지 조회
   * @param messageVO
   * @return
   */
  public List<MessageVO> receive_list(String receiveid);
}
