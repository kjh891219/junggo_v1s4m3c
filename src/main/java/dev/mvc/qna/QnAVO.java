package dev.mvc.qna;
 
public class QnAVO {
   private  int      qnano;
   private  String   title="";
   private  String   content="";
   private  String   qfile="";
   private  String   writer="";
   private  String   passwd="";
   private  String   qdate="";
   private  int      categoryno;
   
   public QnAVO(){
      
   }
    
   public QnAVO(int qnano, String title, String content, String qfile, String writer, String passwd,
         int categoryno) {
      this.qnano = qnano;
      this.title = title;
      this.content = content;
      this.qfile = qfile;
      this.writer = writer;
      this.passwd = passwd;
      this.categoryno = categoryno;
   }
   
   
   public String getQdate() {
      return qdate;
   }

   public void setQdate(String qdate) {
      this.qdate = qdate;
   }

   public int getQnano() {
      return qnano;
   }
   public void setQnano(int qnano) {
      this.qnano = qnano;
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
   public String getQfile() {
      return qfile;
   }
   public void setQfile(String qfile) {
      this.qfile = qfile;
   }
   public String getWriter() {
      return writer;
   }
   public void setWriter(String writer) {
      this.writer = writer;
   }
   public String getPasswd() {
      return passwd;
   }
   public void setPasswd(String passwd) {
      this.passwd = passwd;
   }
   public int getCategoryno() {
      return categoryno;
   }
   public void setCategoryno(int categoryno) {
      this.categoryno = categoryno;
   }
   
   
   
}