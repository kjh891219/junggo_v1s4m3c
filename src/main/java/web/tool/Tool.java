package web.tool;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class Tool {
  
  /**
   * ���ڿ��� ���̰� length ���� ũ�� "..." �� ǥ���ϴ� �޼ҵ�
   * 
   * @param str ���ڿ�
   * @param length ������ ���ڿ� ����
   * @return
   */
  public static synchronized String textLength(String str, int length) {

    if (str != null) {
      if (str.length() > length) {
        str = str.substring(0, length) + "...";
      }
    } else {
      str = "";
    }

    return str;
  }
  
  /**
   * Ű�� �����մϴ�. ��) ABC + ����ð�: ABC1234567890123
   * @return
   */
  public static synchronized String key(){
    String code = "";
    
    //  ASCII: 65 ~ 90, (A ~ Z: 26��)
    Random rnd = new Random();
    int su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
    
    su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
    
    su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
 
    code = code + new Date().getTime();
    // System.out.println("CODE: " + code); // CODE: XGL1449819012230 
    
    return code;
  }

  
  // ���ڿ��� null���� �˻��Ͽ� null �̸� ""���� ��ȯ�Ͽ� ����  
  public static synchronized String checkNull(String str){
    if (str == null){
      str = "";
    } else if(str.equals("null")){
      str = "";
    }
      
    return str;
  }
  
  /**
   * ������ �̹������� �˻��մϴ�.
   * @param file ���ϸ�
   * @return true: �̹���, false: �Ϲ� ����
   */
  public static synchronized boolean isImage(String file) {
    boolean sw = false;
 
    if (file != null) {
      file = file.toLowerCase(); // �ҹ���
      if (file.endsWith(".jpg") || file.endsWith(".jpeg")
          || file.endsWith(".png") || file.endsWith(".gif")) {
        sw = true;
      }
    }
    return sw;
  }

  /**
   * ���� �뷮�� ������ �߰��մϴ�.
   * @param size ���� ũ��
   * @return KB, MB, GB, TB
   */
  public static synchronized String unit(long size) {
    String str = "";
 
    if (size < (1024 * 1024)) { // 0 ~ 1048575, 1MB ���� ���� ���
      str = (int) (Math.ceil(size / 1024.0)) + " KB";
    } else if (size < (1024 * 1024 * 1024)) { // 1 GB ���� ���� ���
      str = (int) (Math.ceil(size / 1024.0 / 1024.0)) + " MB";
    } else if (size < (1024 * 1024 * 1024 * 1024)) { // 1 TB ���� ���� ���
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0)) + " GB";
    } else {
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0)) + " TB";
    }
 
    return str;
  }
  
  /**
   * ���ڿ��� ������ ���̷� ©����ϴ�.
   * 
   * @param str
   * @param length
   * @return
   */
  public static synchronized String trim(String str, int length) {
    String _str = "";
 
    if (str != null) { // ���ڿ��� �ִٸ�
      if (str.length() > length) { // ���ڿ��� ���̸� �˻�
        _str = str.substring(0, length) + "..."; // ������ ������ ���ڸ� ����
      } else {
        _str = str; // ���� �״�� ���
      }
    } else {
      _str = "";
    }
 
    return _str;
  }
  
  /**
   * Ư�����ڸ� �ڵ�� ��ȯ�մϴ�. static: ��ü�� �������ʰ� ȣ�Ⱑ���մϴ�. synchronized: ���� ������ ������������
   * ��ȯ�մϴ�.
   */
  public static synchronized String convertChar(String str) {
    str = str.replaceAll("<", "&lt;");
    str = str.replaceAll(">", "&gt;");
    str = str.replaceAll("'", "&#39;");
    str = str.replaceAll("\"", "&quot;");
    str = str.replaceAll("\r\n", "<BR>");
 
    return str;
  }
  
  /**
   * �̹��� ����� �����Ͽ� ���ο� �̹����� �����մϴ�.
   <pre>
   ��뿹): Too.imgResize(srcFile ��ü, destFile ��ü, 200, 150)
   </pre>
   * @param src ���� �̹��� ����
   * @param dest �����Ǵ� �̹��� ����
   * @param width ������ �̹��� �ʺ�
   * @param height ������ �̹��� ����, ImageUtil.RATIO�� �ڵ� ��� ����
   * @return ������ �̹��� ���ϸ� ����, src.jpg -> dest.jpg
   */
  public static synchronized String imgResize(File src, File dest, int width,
      int height) {
    int RATIO = 0;
    int SAME = -1;
 
    Image srcImg = null;
    // ������ Ȯ���� ����
    String name = src.getName().toLowerCase(); // ���ϸ� ����
    // �̹��� �������� �˻�
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png")
        || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // �޸𸮿� ���� �̹��� ����
        int srcWidth = srcImg.getWidth(null); // ���� �̹��� �ʺ� ����
        int srcHeight = srcImg.getHeight(null); // ���� �̹��� ���� ����
        int destWidth = -1, destHeight = -1; // ��� �̹��� ũ�� �ʱ�ȭ
 
        if (width == SAME) { // width�� ���� ���
          destWidth = srcWidth;
        } else if (width > 0) {
          destWidth = width; // ���ο� width�� �Ҵ�
        }
 
        if (height == SAME) { // ���̰� ���� ���
          destHeight = srcHeight;
        } else if (height > 0) {
          destHeight = height; // ���ο� ���̷� �Ҵ�
        }
 
        // ������ ���� ũ�� ���
        if (width == RATIO && height == RATIO) {
          destWidth = srcWidth;
          destHeight = srcHeight;
        } else if (width == RATIO) {
          double ratio = ((double) destHeight) / ((double) srcHeight);
          destWidth = (int) ((double) srcWidth * ratio);
        } else if (height == RATIO) {
          double ratio = ((double) destWidth) / ((double) srcWidth);
          destHeight = (int) ((double) srcHeight * ratio);
        }
 
        // �޸𸮿� ��� �̹��� ����
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight,
            Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);
 
        pg.grabPixels();
 
        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);
 
        // ���Ͽ� ���
        ImageIO.write(destImg, "jpg", dest);
 
        System.out.println(dest.getName() + " �̹����� �����߽��ϴ�.");
      } catch (Exception e) {
        e.printStackTrace();
      }
 
      name = dest.getName(); // ���� ������ ���ϸ�
    }
 
    return name;
  }
  
  /**
   * �̹��� ����� �����Ͽ� ���ο� Preview �̹����� �����մϴ�.
   <pre>
   ��뿹): Too.preview(folder ��, ���� ���ϸ�, 200, 150)
   </pre>
   * @param upDir ���� �̹��� ����
   * @param _src ���� ���ϸ�
   * @param width ������ �̹��� �ʺ�
   * @param height  ������ �̹��� ����, ImageUtil.RATIO�� �ڵ� ��� ����
   * @return src.jpg ������ �̿��Ͽ� src_t.jpg ������ �����Ͽ� ���ϸ� ����
   */
  public static synchronized String preview(String upDir, String _src, int width,
      int height) {
    int RATIO = 0;
    int SAME = -1;
 
    File src = new File(upDir + "/" + _src); // ���� ���� ��ü ����
    String srcname = src.getName(); // ���� ���ϸ� ����
 
    // ���� ���ϸ� ����, mt.jpg -> mt �� ����
    String _dest = srcname.substring(0, srcname.indexOf("."));
 
    // ��� �̹��� ���� /upDir/mt_t.jpg
    File dest = new File(upDir + "/" + _dest + "_t.jpg");
 
    Image srcImg = null;
    // ������ Ȯ���� ����
    String name = src.getName().toLowerCase(); // ���ϸ� ����
    // �̹��� �������� �˻�
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png")
        || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // �޸𸮿� ���� �̹��� ����
        int srcWidth = srcImg.getWidth(null); // ���� �̹��� �ʺ� ����
        int srcHeight = srcImg.getHeight(null); // ���� �̹��� ���� ����
        int destWidth = -1, destHeight = -1; // ��� �̹��� ũ�� �ʱ�ȭ
 
        if (width == SAME) { // width�� ���� ���
          destWidth = srcWidth;
        } else if (width > 0) {
          destWidth = width; // ���ο� width�� �Ҵ�
        }
 
        if (height == SAME) { // ���̰� ���� ���
          destHeight = srcHeight;
        } else if (height > 0) {
          destHeight = height; // ���ο� ���̷� �Ҵ�
        }
 
        // ������ ���� ũ�� ���
        if (width == RATIO && height == RATIO) {
          destWidth = srcWidth;
          destHeight = srcHeight;
        } else if (width == RATIO) {
          double ratio = ((double) destHeight) / ((double) srcHeight);
          destWidth = (int) ((double) srcWidth * ratio);
        } else if (height == RATIO) {
          double ratio = ((double) destWidth) / ((double) srcWidth);
          destHeight = (int) ((double) srcHeight * ratio);
        }
 
        // �޸𸮿� ��� �̹��� ����
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight,
            Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);
 
        pg.grabPixels();
 
        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);
 
        // ���Ͽ� ���
        ImageIO.write(destImg, "jpg", dest);
 
        System.out.println(dest.getName() + " �̹����� �����߽��ϴ�.");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
 
    return dest.getName();
  }
  
  /**
   * Youtube �̹����� ��� ���
   * @param str �ּ�
   * @param width ������ �ʺ�
   * @param height ������ ����
   * @return
   */
  public static synchronized String youtube(String str, int width, int height) {
    int index1 = str.indexOf("\""); // ù��° ���� �˻�
    int index2 = str.indexOf("\"", index1 + 1); // ù��° ���ĺ��� �˻�
    int index3 = str.indexOf("\"", index2 + 1); // �ι�° ���ĺ��� �˻�
    int index4 = str.indexOf("\"", index3 + 1); // ����° ���ĺ��� �˻�
    // System.out.println("--> index1: " + index1);
    // System.out.println("--> index2: " + index2);
    // System.out.println("--> index3: " + index3);
    // System.out.println("--> index4: " + index4);
 
    String str1 = str.substring(0, index1 + 1); // <iframe width='
    String str2 = str.substring(index2, index3 + 1); // ' height='
    String str3 = str.substring(index4); // ' src=������ ��� ���ڿ�
    String url = str1 + width + "px" + str2 + height + "px" + str3;
 
    // System.out.println("--> str1: " + str1);
    // System.out.println("--> str1: " + str2);
    // System.out.println("--> str1: " + str3);
    return url;
  }
 
  /**
   * ������ �����մϴ�.
   */
  public static synchronized boolean deleteFile(String folder, String fileName) {
    boolean ret = false;
 
    try {
      if (fileName != null) { // ������ ������ �����ϴ� ��� ����
        File file = new File(folder + "/" + fileName);
        if (file.exists()) {
          ret = file.delete();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
 
    return ret;
  }
 
  
  /**
   * ������ �������� �˻��մϴ�.
   * @param request
   * @return true: ������ ����
   */
  public static synchronized boolean isMaster(HttpServletRequest request){
    boolean sw = false;
    
    HttpSession session = request.getSession();
    String act = (String)session.getAttribute("act");
    // System.out.println("--> Tool.java act: " + act);
    
    if (act != null){
      if (act.equals("M")){
        sw = true;
      }
    }
    return sw;
  }
 
  /**
   * �Ϲ� ������ �������� �˻��մϴ�.
   * @param request
   * @return true: �Ϲ� ������
   */
  public static synchronized boolean isAdmin(HttpServletRequest request){
    boolean sw = false;
    
    HttpSession session = request.getSession();
    String act = (String)session.getAttribute("act");
    
    if (act != null){
      if (act.equals("Y")){
        sw = true;
      }
    }
    return sw;
  }
 
  

}
