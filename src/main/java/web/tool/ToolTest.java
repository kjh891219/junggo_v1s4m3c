package web.tool;
 
public class ToolTest {
 
  public static void main(String[] args) {
    String col = "rname";
    // 3항 연산자: (조건)? 참일 경우 : 거짓일 경우
    System.out.println((col.equals("rname"))?"selected='selected'":"");
 
  }
 
}