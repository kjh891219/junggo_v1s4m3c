package dev.mvc.tmember;
 
/*
 * CREATE TABLE member(
    userid                            VARCHAR2(20)     NOT NULL PRIMARY KEY,
    mno                               NUMBER(6)        NOT NULL UNIQUE,
    pwd                               VARCHAR2(30)     NOT NULL,
    name                              VARCHAR2(20)     NOT NULL,
    nickname                          VARCHAR2(20)     NOT NULL UNIQUE,
    tel                               VARCHAR2(14)     NOT NULL,
    zipcode                           VARCHAR2(5)          NULL,
    address1                          VARCHAR2(80)         NULL,
    address2                          VARCHAR2(50)         NULL,
    email                             VARCHAR2(100)    NOT NULL UNIQUE,
    mdate                             DATE             NOT NULL,
    auth                              VARCHAR2(20)     DEFAULT 'N'   NOT NULL,
    confirm                           CHAR(1)    DEFAULT 'N'     NOT NULL, -- 이메일 링크 클릭 여부, Y:클릭, N:클릭안함
    act                               CHAR(1)    NOT NULL,  -- M: 마스터, Y: 로그인 가능, N: 로그인 불가, H: 인증 대기
    droupout                          VARCHAR2(1)    DEFAULT 'N'     NOT NULL
);
 * 
 */
public class MemberVO {
 
  /** 관리자 번호 */
  private int mno;
  /** 아이디 */
  private String userid = "";
  /** 패스워드 */
  private String pwd = "";
  /** 성명 */
  private String name = "";
  /** 닉네임 */
  private String nickname = "";
  /** 전화 번호 */
  private String tel = "";
  /** 우편 번호 */
  private String zipcode = "";
  /** 주소 1 */
  private String address1 = "";
  /** 주소 2 */
  private String address2 = "";
  /** 가입일 */
  private String mdate = "";
  /** 이메일 */
  private String email = "";
  
  private String auth  = "";
  private String confirm = "";
  private String act = "";
  private String droupout = "";
  
  /** 등록된 패스워드 */
  private String old_pwd = "";
  /** id 저장 여부 */
  private String userid_save = "";
  /** passwd 저장 여부 */
  private String pwd_save = "";
  /** 이동할 주소 저장 */
  private String url_address = "";
  public int getMno() {
    return mno;
  }
  public void setMno(int mno) {
    this.mno = mno;
  }
  public String getUserid() {
    return userid;
  }
  public void setUserid(String userid) {
    this.userid = userid;
  }
  public String getPwd() {
    return pwd;
  }
  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getZipcode() {
    return zipcode;
  }
  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
  public String getAddress1() {
    return address1;
  }
  public void setAddress1(String address1) {
    this.address1 = address1;
  }
  public String getAddress2() {
    return address2;
  }
  public void setAddress2(String address2) {
    this.address2 = address2;
  }
  public String getMdate() {
    return mdate;
  }
  public void setMdate(String mdate) {
    this.mdate = mdate;
  }
  public String getOld_pwd() {
    return old_pwd;
  }
  public void setOld_pwd(String old_pwd) {
    this.old_pwd = old_pwd;
  }
  public String getUserid_save() {
    return userid_save;
  }
  public void setUserid_save(String userid_save) {
    this.userid_save = userid_save;
  }
  public String getPwd_save() {
    return pwd_save;
  }
  public void setPwd_save(String pwd_save) {
    this.pwd_save = pwd_save;
  }
  public String getUrl_address() {
    return url_address;
  }
  public void setUrl_address(String url_address) {
    this.url_address = url_address;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getAuth() {
    return auth;
  }
  public void setAuth(String auth) {
    this.auth = auth;
  }
  public String getConfirm() {
    return confirm;
  }
  public void setConfirm(String confirm) {
    this.confirm = confirm;
  }
  public String getAct() {
    return act;
  }
  public void setAct(String act) {
    this.act = act;
  }
  public String getDroupout() {
    return droupout;
  }
  public void setDroupout(String droupout) {
    this.droupout = droupout;
  }

  
 
}