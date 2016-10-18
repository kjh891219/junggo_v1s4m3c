package dev.mvc.camera;


public class CameraVO {

  /*
   * CREATE TABLE CAMERA(
      ctno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
      category                          VARCHAR2(20)     NOT NULL,
      nickname                          VARCHAR2(20)     NOT NULL,
      passwd                            VARCHAR2(10)     NOT NULL,
      deal_way                          VARCHAR2(20)     NOT NULL,
      deal_code                         VARCHAR2(20)     NOT NULL,
      product_code                      VARCHAR2(20)     NOT NULL,
      hprice                            NUMBER(15)     DEFAULT 0     NOT NULL,
      region                            VARCHAR2(20)     DEFAULT ''    NOT NULL,
      tel                               VARCHAR2(14)     DEFAULT ''    NOT NULL,
      email                             VARCHAR2(100)    DEFAULT ''    NOT NULL,
      quantity                          NUMBER(6)    DEFAULT 0     NOT NULL,
      title                             VARCHAR2(200)    DEFAULT ''    NOT NULL,
      content                           VARCHAR2(4000)     NOT NULL,
      purc_date                         VARCHAR2(20)     DEFAULT ''    NOT NULL,
      wdate                             DATE     DEFAULT sysdate     NOT NULL,
      cnt                               NUMBER(6)    DEFAULT 0     NOT NULL
  );
  */
  
  /**�۹�ȣ*/
  private int ctno;
  /**ī�װ�*/
  private String category;
  /**�г���*/
  private String nickname;
  /**��й�ȣ*/
  private String passwd;
  /**�ŷ����*/
  private String deal_way;
  /**�ŷ�����*/
  private String deal_code;
  /**��ǰ����*/
  private String product_code;
  /**�������*/
  private int hprice;
  /**����*/
  private String region;
  /**��ȭ��ȣ*/
  private String tel;
  /**�̸���*/
  private String email;
  /**����*/
  private int quantity;
  /**����*/
  private String title;
  /**�󼼳���*/
  private String content;
  /**���Խñ�*/
  private String purc_date;
  /**�۵����*/
  private String wdate;
  /**��ȸ��*/
  private int cnt;
  /**���̵�*/
  private String userid;
  public String getUserid() {
    return userid;
  }
  public void setUserid(String userid) {
    this.userid = userid;
  }
  public int getCtno() {
    return ctno;
  }
  public void setCtno(int ctno) {
    this.ctno = ctno;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
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
  public int getHprice() {
    return hprice;
  }
  public void setHprice(int hprice) {
    this.hprice = hprice;
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
  public String getWdate() {
    return wdate;
  }
  public void setWdate(String wdate) {
    this.wdate = wdate;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
 
 
}
