package dev.mvc.game;

public class GameVO {
 /*  
  * CREATE TABLE GAME(
    gno                               NUMBER(10)     NOT NULL    PRIMARY KEY,
    rname                             VARCHAR2(20)     NOT NULL,
    email                             VARCHAR2(100)    NULL ,
    tell                              VARCHAR2(20)     NULL ,
    title                             VARCHAR2(200)    NOT NULL,
    content                           VARCHAR2(4000)     NOT NULL,
    passwd                            VARCHAR2(30)     NOT NULL,
    cnt                               NUMBER(15)     DEFAULT 0     NULL ,
    rdate                             DATE     NOT NULL,
    area                              VARCHAR2(20)     NOT NULL,
    state                             VARCHAR2(20)     NOT NULL,
    purchtime                         VARCHAR2(20)     NOT NULL,
    purchprice                        VARCHAR2(20)     NOT NULL,
    goods                             VARCHAR2(20)     NULL ,
    howdeal                           VARCHAR2(20)     NOT NULL,
    amount                            VARCHAR2(10)     DEFAULT 1     NOT NULL,
    price                             VARCHAR2(10)     NOT NULL,
    level                             VARCHAR2(15)     NOT NULL,
    genre                             VARCHAR2(10)     NULL ,
  FOREIGN KEY () REFERENCES game ()
);
  */
    private int gno;
    private String category;
    private String rname;
    private String email;
    private String tell;
    private String title;
    private String content;
    private String passwd;
    private int cnt;
    private String rdate;
    private String area;
    private String state;
    private String purchtime;
    private String purchprice;
    private String goods;
    private String howdeal;
    public int getGno() {
      return gno;
    }
    public void setGno(int gno) {
      this.gno = gno;
    }
    public String getCategory() {
      return category;
    }
    public void setCategory(String category) {
      this.category = category;
    }
    public String getRname() {
      return rname;
    }
    public void setRname(String rname) {
      this.rname = rname;
    }
    public String getEmail() {
      return email;
    }
    public void setEmail(String email) {
      this.email = email;
    }
    public String getTell() {
      return tell;
    }
    public void setTell(String tell) {
      this.tell = tell;
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
    public String getPasswd() {
      return passwd;
    }
    public void setPasswd(String passwd) {
      this.passwd = passwd;
    }
    public int getCnt() {
      return cnt;
    }
    public void setCnt(int cnt) {
      this.cnt = cnt;
    }
    public String getRdate() {
      return rdate;
    }
    public void setRdate(String rdate) {
      this.rdate = rdate;
    }
    public String getArea() {
      return area;
    }
    public void setArea(String area) {
      this.area = area;
    }
    public String getState() {
      return state;
    }
    public void setState(String state) {
      this.state = state;
    }
    public String getPurchtime() {
      return purchtime;
    }
    public void setPurchtime(String purchtime) {
      this.purchtime = purchtime;
    }
    public String getPurchprice() {
      return purchprice;
    }
    public void setPurchprice(String purchprice) {
      this.purchprice = purchprice;
    }
    public String getGoods() {
      return goods;
    }
    public void setGoods(String goods) {
      this.goods = goods;
    }
    public String getHowdeal() {
      return howdeal;
    }
    public void setHowdeal(String howdeal) {
      this.howdeal = howdeal;
    }
    public String getAmount() {
      return amount;
    }
    public void setAmount(String amount) {
      this.amount = amount;
    }
    public String getPrice() {
      return price;
    }
    public void setPrice(String price) {
      this.price = price;
    }
    public String getLev() {
      return lev;
    }
    public void setLev(String lev) {
      this.lev = lev;
    }
    public String getGenre() {
      return genre;
    }
    public void setGenre(String genre) {
      this.genre = genre;
    }
    private String amount;
    private String price;
    private String lev;
    private String genre;
  
    
    
}
