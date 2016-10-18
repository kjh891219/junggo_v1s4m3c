package dev.mvc.tmember;

import java.util.List;



public interface MemberDAOInter {
  /**
   * 중복 아이디를 검사합니다.
   * <select id='checkId' resultType='int' parameterType='String'>
   * @param id 아이디
   * @return 0: 중복 아님, 1: 중복
   */
  public int checkId(String id);
 
  /**
   * 레코드를 등록합니다.
   * <insert id="create" parameterType="MemberVO">
   * @param vo 등록할 데이터 객체
   * @return 등록된 레코드 수
   */
  public int create(MemberVO vo);
   
  /**
   * 회원 전체 목록
   * <select id="list" resultType="MemberVO">
   * @return 회원 목록
   */
  public List<MemberVO> list();
  
  /**
   * 회원 정보 조회
   * <select id="read" resultType="MemberVO" parameterType="int">
   * @param mno
   * @return
   */
  public MemberVO read(int mno); 
  
  /**
   * 회원 정보 조회
   * <select id="read" resultType="MemberVO" parameterType="String">
   * @param userid
   * @return
   */
  public MemberVO read_userid(String userid); 
  
  /**
   * 회원을 수정합니다.
   * <update id="update" parameterType="MemberVO"> 
   * @param memberVO
   * @return 수정된 레코드 갯수
   */
  public int update(MemberVO memberVO);
  
  /** 최초 가입시 관리자 조회
   *  <select id="admin_search" resultType="int" parameterType="String">
   * @param act
   * @return
   */
  public int admin_search(String act);
  
  /** 이메일 인증 처리
   * <update id="confirm" parameterType="MemberVO">
   * @param memberVO
   * @return
   */
  public int confirm(MemberVO memberVO);
  
  /** 로그인
   * <select id="login" resultType="int" parameterType="MemberVO">
   * @param memberVO
   * @return
   */
  public int login(MemberVO memberVO);
  
}
