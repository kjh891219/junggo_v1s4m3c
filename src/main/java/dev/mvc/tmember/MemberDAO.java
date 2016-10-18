package dev.mvc.tmember;
 
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

 
@Repository("dev.mvc.tmember.MemberDAO")
public  class MemberDAO implements MemberDAOInter{
   

  @Autowired
  private SqlSessionTemplate mybatis; //MyBatis 3 ¿¬°á °´Ã¼
  
  public MemberDAO(){
     System.out.println("--> MemberDAO created.");
  }
  
  @Override
  public int checkId(String id) {
/*
     <Mapper namespace = "member">
     <select id='checkId' resultType='int' parameterType='String'>
*/      
     return mybatis.selectOne("member.checkId", id); 
  }
  
  @Override
  public int checkNickname(String nickname) {
    /*
     <Mapper namespace = "member">
     <select id='checkNickname' resultType='int' parameterType='String'>
     */      
    return mybatis.selectOne("member.checkNickname", nickname); 
  }
  
  @Override
  public int checkEmail(String email) {
    /*
     <Mapper namespace = "member">
     <select id='checkEmail' resultType='int' parameterType='String'>
     */      
    return mybatis.selectOne("member.checkEmail", email); 
  }

  @Override
  public int create(MemberVO vo) {
/*      
     <Mapper namespace = "member">
     <insert id="create" parameterType="MemberVO">
*/      
     return mybatis.insert("member.create", vo);
  }

  @Override
  public List<MemberVO> list() {
    return mybatis.selectList("member.list");
  }

  @Override
  public MemberVO read(int mno) {
    return mybatis.selectOne("member.read", mno);
  }
  
  @Override
  public MemberVO read_userid(String userid) {
    return mybatis.selectOne("member.read_userid", userid);
  }
  
  @Override
  public int update(MemberVO memberVO) {
    return mybatis.update("member.update", memberVO);
  }

  @Override
  public int admin_search(String act) {
    return mybatis.selectOne("member.admin_search", act);
  }

  @Override
  public int confirm(MemberVO memberVO) {
    return mybatis.update("member.confirm", memberVO);
  }

  @Override
  public int login(MemberVO memberVO) {
    return mybatis.selectOne("member.login", memberVO);
  }
   
  
}