package dev.mvc.usedcar;

import java.util.Date;

import oracle.sql.DATE;

public class UsedcarVO {
  

  private int     u_no;
  /** 중고차번호 */
  private String nickname;
  /** 닉네임 */
  private String passwd;
  /** 비밀번호 */
  private String userid;
  /** 아이디 */
  private String category;
  /** 자동차카테고리코드 */
  private String deal_way;
  /** 거래방식 */
  private String deal_code;
  /** 거래구분코드 */
  private String product_code;
  /** 상품구분 */
  private int     h_price;
  /** 희망가격 */
  private String region;
  /** 거래지역 */
  private String tel;
  /** 전화번호 */
  private String email;
  /** 이메일 */
  private int quantity;
  /** 수량 */
  private String title;
  /** 제목 */
  private String content;
  /** 내용 */
  private String purc_date;
  /** 구입시기 */
  private DATE wdate;
  /** 글등록일 */
  private int     u_cnt;
  /** 조회수 */
  
  public int getU_no() {
    return u_no;
  }
  public void setU_no(int u_no) {
    this.u_no = u_no;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public String getUserid() {
    return userid;
  }
  public void setUserid(String userid) {
    this.userid = userid;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public String getDeal_way() {
    return deal_way;
  }
  public void setDeal_way(String deal_way) {
    this.deal_way = deal_way;
  }
  public String getDeal_code() {
    return deal_code;
  }
  public void setDeal_code(String deal_code) {
    this.deal_code = deal_code;
  }
  public String getProduct_code() {
    return product_code;
  }
  public void setProduct_code(String product_code) {
    this.product_code = product_code;
  }
  public int getH_price() {
    return h_price;
  }
  public void setH_price(int h_price) {
    this.h_price = h_price;
  }
  public String getRegion() {
    return region;
  }
  public void setRegion(String region) {
    this.region = region;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getPurc_date() {
    return purc_date;
  }
  public void setPurc_date(String purc_date) {
    this.purc_date = purc_date;
  }
  public DATE getWdate() {
    return wdate;
  }
  public void setWdate(DATE wdate) {
    this.wdate = wdate;
  }
  public int getU_cnt() {
    return u_cnt;
  }
  public void setU_cnt(int u_cnt) {
    this.u_cnt = u_cnt;
  }
  


  
}
