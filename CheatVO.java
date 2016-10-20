package dev.mvc.cheat;

public class CheatVO {
  private int ctno;
  private String gubun;
  private String region;
  private String occurday;
  private int buyprice;
  private String cheatid;
  private String cheattel;
  private String cheatemail;
  private int hit;
  private String content;
  private String email;
  private String tel;
  private String rname;
  private String passwd;
  private String rdate;
  private String title;
  public int getCtno() {
    return ctno;
  }
  public void setCtno(int ctno) {
    this.ctno = ctno;
  }
  public String getGubun() {
    return gubun;
  }
  public void setGubun(String gubun) {
    this.gubun = gubun;
  }
  public String getRegion() {
    return region;
  }
  public void setRegion(String region) {
    this.region = region;
  }
  public String getOccurday() {
    return occurday;
  }
  public void setOccurday(String occurday) {
    this.occurday = occurday;
  }
  public int getBuyprice() {
    return buyprice;
  }
  public void setBuyprice(int buyprice) {
    this.buyprice = buyprice;
  }
  public String getCheatid() {
    return cheatid;
  }
  public void setCheatid(String cheatid) {
    this.cheatid = cheatid;
  }
  public String getCheattel() {
    return cheattel;
  }
  public void setCheattel(String cheattel) {
    this.cheattel = cheattel;
  }
  public String getCheatemail() {
    return cheatemail;
  }
  public void setCheatemail(String cheatemail) {
    this.cheatemail = cheatemail;
  }
  public int getHit() {
    return hit;
  }
  public void setHit(int hit) {
    this.hit = hit;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getRname() {
    return rname;
  }
  public void setRname(String rname) {
    this.rname = rname;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public CheatVO(){}
  
  public CheatVO(int ctno, String gubun, String region, String occurday, int buyprice, String cheatid, String cheattel,
      String cheatemail, int hit, String content, String email, String tel, String rname, String passwd, String rdate, String title) {
    this.ctno = ctno;
    this.gubun = gubun;
    this.region = region;
    this.occurday = occurday;
    this.buyprice = buyprice;
    this.cheatid = cheatid;
    this.cheattel = cheattel;
    this.cheatemail = cheatemail;
    this.hit = hit;
    this.content = content;
    this.email = email;
    this.tel = tel;
    this.rname = rname;
    this.passwd = passwd;
    this.rdate = rdate;
    this.title = title;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  
  
}
