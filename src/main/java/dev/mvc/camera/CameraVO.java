package dev.mvc.camera;

import org.springframework.web.multipart.MultipartFile;

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
  
  /**글번호*/
  private int ctno;
  /**카테고리*/
  private String category;
  /**닉네임*/
  private String nickname;
  /**비밀번호*/
  private String passwd;
  /**거래방법*/
  private String deal_way;
  /**거래구분*/
  private String deal_code;
  /**상품구분*/
  private String product_code;
  /**희망가격*/
  private int hprice;
  /**지역*/
  private String region;
  /**전화번호*/
  private String tel;
  /**이메일*/
  private String email;
  /**수량*/
  private int quantity;
  /**제목*/
  private String title;
  /**상세내용*/
  private String content;
  /**구입시기*/
  private String purc_date;
  /**글등록일*/
  private String wdate;
  /**조회수*/
  private int cnt;
  /**아이디*/
  private String userid;
  
  /** Preview 소형 이미지 200 X 150, 자동 생성됨 */
  private String file1 = "";
  /** 업로드 파일 */
  private String file2 = "";
  /** 업로드된 파일 크기 */
  private long size2 = 0;
  
  
  /** Preview 소형 이미지2 200 X 150, 자동 생성됨 */
  private String file3 = "";
  
  /** 업로드 파일2 */
  private String file4 = "";
  /** 업로드된 파일 크기2 */
  private long size4 = 0;
  
  
  /** Preview 소형 이미지3 200 X 150, 자동 생성됨 */
  private String file5 = "";
  
  /** 업로드 파일3 */
  private String file6 = "";
  /** 업로드된 파일 크기3 */
  private long size6 = 0;
  
  
  /** Preview 소형 이미지4 200 X 150, 자동 생성됨 */
  private String file7 = "";
  
  /** 업로드 파일4 */
  private String file8 = "";
  /** 업로드된 파일 크기4 */
  private long size8 = 0;
  
  
  /** Preview 소형 이미지5 200 X 150, 자동 생성됨 */
  private String file9 = "";
  
  /** 업로드 파일5 */
  private String file10 = "";
  /** 업로드된 파일 크기5 */
  private long size10 = 0;
  
  
  
  /** Spring Framework에서 자동 주입되는 업로드 파일 객체,
  실제 컬럼은 존재하지 않음.
   */  
  private MultipartFile file2MF;

  /** size2의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String size2Label; 
  
  
  /** Spring Framework에서 자동 주입되는 업로드 파일 객체2,
  실제 컬럼은 존재하지 않음.
   */  
  private MultipartFile file4MF;

  /** size4의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String size4Label; 
  
  
  /** Spring Framework에서 자동 주입되는 업로드 파일 객체3,
  실제 컬럼은 존재하지 않음.
   */  
  private MultipartFile file6MF;

  /** size6의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String size6Label; 
  
  /** Spring Framework에서 자동 주입되는 업로드 파일 객체4,
  실제 컬럼은 존재하지 않음.
   */  
  private MultipartFile file8MF;

  /** size8의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String size8Label; 
  
  /** Spring Framework에서 자동 주입되는 업로드 파일 객체5,
  실제 컬럼은 존재하지 않음.
   */  
  private MultipartFile file10MF;

  /** size10의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String size10Label; 
  
  
  
  
  
  
  public String getFile5() {
    return file5;
  }
  public void setFile5(String file5) {
    this.file5 = file5;
  }
  public String getFile6() {
    return file6;
  }
  public void setFile6(String file6) {
    this.file6 = file6;
  }
  public long getSize6() {
    return size6;
  }
  public void setSize6(long size6) {
    this.size6 = size6;
  }
  public String getFile7() {
    return file7;
  }
  public void setFile7(String file7) {
    this.file7 = file7;
  }
  public String getFile8() {
    return file8;
  }
  public void setFile8(String file8) {
    this.file8 = file8;
  }
  public long getSize8() {
    return size8;
  }
  public void setSize8(long size8) {
    this.size8 = size8;
  }
  public String getFile9() {
    return file9;
  }
  public void setFile9(String file9) {
    this.file9 = file9;
  }
  public String getFile10() {
    return file10;
  }
  public void setFile10(String file10) {
    this.file10 = file10;
  }
  public long getSize10() {
    return size10;
  }
  public void setSize10(long size10) {
    this.size10 = size10;
  }
  public MultipartFile getFile6MF() {
    return file6MF;
  }
  public void setFile6MF(MultipartFile file6mf) {
    file6MF = file6mf;
  }
  public String getSize6Label() {
    return size6Label;
  }
  public void setSize6Label(String size6Label) {
    this.size6Label = size6Label;
  }
  public MultipartFile getFile8MF() {
    return file8MF;
  }
  public void setFile8MF(MultipartFile file8mf) {
    file8MF = file8mf;
  }
  public String getSize8Label() {
    return size8Label;
  }
  public void setSize8Label(String size8Label) {
    this.size8Label = size8Label;
  }
  public MultipartFile getFile10MF() {
    return file10MF;
  }
  public void setFile10MF(MultipartFile file10mf) {
    file10MF = file10mf;
  }
  public String getSize10Label() {
    return size10Label;
  }
  public void setSize10Label(String size10Label) {
    this.size10Label = size10Label;
  }
 
  
  
  
  
  
  
  
  public String getFile3() {
    return file3;
  }
  public void setFile3(String file3) {
    this.file3 = file3;
  }
  public String getFile4() {
    return file4;
  }
  public void setFile4(String file4) {
    this.file4 = file4;
  }
  public long getSize4() {
    return size4;
  }
  public void setSize4(long size4) {
    this.size4 = size4;
  }
  public MultipartFile getFile4MF() {
    return file4MF;
  }
  public void setFile4MF(MultipartFile file4mf) {
    file4MF = file4mf;
  }
  public String getSize4Label() {
    return size4Label;
  }
  public void setSize4Label(String size4Label) {
    this.size4Label = size4Label;
  }
 
  
  
  
  
  
  public String getFile1() {
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }
  public String getFile2() {
    return file2;
  }
  public void setFile2(String file2) {
    this.file2 = file2;
  }
  public long getSize2() {
    return size2;
  }
  public void setSize2(long size2) {
    this.size2 = size2;
  }
  public MultipartFile getFile2MF() {
    return file2MF;
  }
  public void setFile2MF(MultipartFile file2mf) {
    file2MF = file2mf;
  }
  public String getSize2Label() {
    return size2Label;
  }
  public void setSize2Label(String size2Label) {
    this.size2Label = size2Label;
  }

  
  
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
