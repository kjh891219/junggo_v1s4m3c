package web.tool;
<<<<<<< HEAD

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBClose {
  public void close(Connection con, PreparedStatement pstmt, ResultSet rs){
    try{
      if (rs != null){ rs.close(); }
    }catch(Exception e){ }
    
    try{
      if (pstmt != null){ pstmt.close(); }
    }catch(Exception e){ }

    try{
      if (con != null){ con.close(); }
    }catch(Exception e){ }
  }
  
  public void close(Connection con, PreparedStatement pstmt){
    try{
      if (pstmt != null){ pstmt.close(); }
    }catch(Exception e){ }

    try{
      if (con != null){ con.close(); }
    }catch(Exception e){ }
  }  
}
=======
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
public class DBClose {
 public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
   try{
     if (rs != null){ rs.close(); } /* 마지막에 만든 것을 가장 먼저 지운다! */
   }catch(Exception e){ }
   
   try{
     if (pstmt != null){ pstmt.close(); }
   }catch(Exception e){ }
  
   try{
     if (con != null){ con.close(); }
   }catch(Exception e){ }
 }
 
 public void close(Connection con, PreparedStatement pstmt) {
   try{
     if (pstmt != null){ pstmt.close(); }
   }catch(Exception e){ }
   
   try{
     if (con != null){ con.close(); }
   }catch(Exception e){ }
 }
 
 
}
>>>>>>> branch 'master' of https://github.com/kjh891219/junggo_v1s4m3c.git
