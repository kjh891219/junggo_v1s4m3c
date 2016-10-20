package web.tool;
<<<<<<< HEAD

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBOpen {
  public Connection getConnection(){
    Connection con = null;         // DBMS ¿¬°á
    String jdbc = "org.gjt.mm.mysql.Driver"; // MySQL ¿¬°á Drvier 
    String url = "jdbc:mysql://localhost:3306/web?useUnicode=true&characterEncoding=euckr"; 
    String user = "root"; 
    String pass = "1234";
    
    try {
      Class.forName(jdbc); // memory·Î µå¶óÀÌ¹ö Å¬·¡½º¸¦ ·ÎµùÇÔ.
      con = DriverManager.getConnection(url, user, pass); // MySQL ¿¬°á
      
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } 
    
    return con;
  }
}




=======
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DBOpen {
  public Connection getConnection(){
    Connection con = null;         // DBMS ï¿½ï¿½ï¿½ï¿½
/*    String jdbc = "org.gjt.mm.mysql.Driver"; // MySQL ï¿½ï¿½ï¿½ï¿½ Drvier 
    String url = "jdbc:mysql://localhost:3306/chanmi910?useUnicode=true&characterEncoding=euckr"; 
    String user = "chanmi910"; 
    String pass = "cksal910!";*/
    
    String jdbc = "oracle.jdbc.driver.OracleDriver"; // MySQL ï¿½ï¿½ï¿½ï¿½ Drvier 
    String url = "jdbc:oracle:thin:@localhost:1521:XE"; 
    String user = "chanmi"; 
    String pass = "1234";
    
    try {
      Class.forName(jdbc); // memoryï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½Ì¹ï¿½ Å¬ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Îµï¿½ï¿½ï¿½.
      con = DriverManager.getConnection(url, user, pass); // MySQL ï¿½ï¿½ï¿½ï¿½
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } 
    
    return con;
  }
}
 
/*--------------------------------------------------------------------


6. ï¿½ï¿½ï¿½ï¿½ï¿½Íºï¿½ï¿½Ì½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
   - ï¿½Þ¼Òµï¿½ï¿½ ï¿½ï¿½Ã¼ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½Ö½ï¿½ï¿½Ï´ï¿½.(Call by Reference)

ï¿½ï¿½ web.tool.DBClose.java
--------------------------------------------------------------------
*/
>>>>>>> branch 'master' of https://github.com/kjh891219/junggo_v1s4m3c.git
