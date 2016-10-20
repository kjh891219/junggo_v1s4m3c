package dev.mvc.tmember;

<<<<<<< HEAD
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
   * 중복 아이디를 검사합니다.
   * <select id='checkNickname' resultType='int' parameterType='String'>
   * @param checkNickname 닉네임
   * @return 0: 중복 아님, 1: 중복
   */
  public int checkNickname(String nickname);
  
  /**
   * 중복 아이디를 검사합니다.
   * <select id='checkEmail' resultType='int' parameterType='String'>
   * @param email 이메일
   * @return 0: 중복 아님, 1: 중복
   */
  public int checkEmail(String email);
 
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
  
=======
import java.util.HashMap;
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
   * 중복 아이디를 검사합니다.
   * <select id='checkNickname' resultType='int' parameterType='String'>
   * @param checkNickname 닉네임
   * @return 0: 중복 아님, 1: 중복
   */
  public int checkNickname(String nickname);
  
  /**
   * 중복 아이디를 검사합니다.
   * <select id='checkEmail' resultType='int' parameterType='String'>
   * @param email 이메일
   * @return 0: 중복 아님, 1: 중복
   */
  public int checkEmail(String email);
 
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
  
  /**
   * 탈퇴 신청
   * <update id="dropout" parameterType="MemberVO">
   * @param memberVO
   * @return 
   */
  public int dropout(MemberVO memberVO);
  
  /**
   * 본인 인증 - 비밀번호 확인
    <select id="checkPwd" resultType="int" parameterType="Map">
   * @param userid
   * @param pwd
   * @return
   */
  public int checkPwd(String userid, String pwd);

  /** 수정 시 닉네임 중복 환인
   * <select id='checkNickname_update' resultType='int' parameterType='map'>
   * @param userid 자신을 제외
   * @param pwd
   * @return
   */
  public int checkNickname_update(HashMap map);
  
  /** 수정 시 이메일 중복 확인
   * <select id='checkEmail_update' resultType='int' parameterType='Map'>
   * @param userid
   * @param pwd
   * @return
   */
  public int checkEmail_update(HashMap map);
>>>>>>> branch 'master' of https://github.com/kjh891219/junggo_v1s4m3c.git
}