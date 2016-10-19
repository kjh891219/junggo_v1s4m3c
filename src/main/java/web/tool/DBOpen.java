package web.tool;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DBOpen {
  public Connection getConnection(){
    Connection con = null;         // DBMS ����
/*    String jdbc = "org.gjt.mm.mysql.Driver"; // MySQL ���� Drvier 
    String url = "jdbc:mysql://localhost:3306/chanmi910?useUnicode=true&characterEncoding=euckr"; 
    String user = "chanmi910"; 
    String pass = "cksal910!";*/
    
    String jdbc = "oracle.jdbc.driver.OracleDriver"; // MySQL ���� Drvier 
    String url = "jdbc:oracle:thin:@localhost:1521:XE"; 
    String user = "chanmi"; 
    String pass = "1234";
    
    try {
      Class.forName(jdbc); // memory�� ����̹� Ŭ������ �ε���.
      con = DriverManager.getConnection(url, user, pass); // MySQL ����
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } 
    
    return con;
  }
}
 
/*--------------------------------------------------------------------


6. �����ͺ��̽� ���� ����
   - �޼ҵ�� ��ü�� ���� ���� �� �ֽ��ϴ�.(Call by Reference)

�� web.tool.DBClose.java
--------------------------------------------------------------------
*/