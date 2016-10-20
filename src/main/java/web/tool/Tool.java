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

<<<<<<< HEAD
public class Tool {
  /**
   * ¹®ÀÚ¿­ÀÇ ±æÀÌ°¡ length º¸´Ù Å©¸é "..." À» Ç¥½ÃÇÏ´Â ¸Þ¼Òµå
   * 
   * @param str ¹®ÀÚ¿­
   * @param length ¼±º°ÇÒ ¹®ÀÚ¿­ ±æÀÌ
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
   * ¸¶½ºÅÍ °èÁ¤ÀÎÁö °Ë»çÇÕ´Ï´Ù.
   * @param request
   * @return true: ¸¶½ºÅÍ °èÁ¤
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
   * ÀÏ¹Ý °ü¸®ÀÚ °èÁ¤ÀÎÁö °Ë»çÇÕ´Ï´Ù.
   * @param request
   * @return true: ÀÏ¹Ý °ü¸®ÀÚ
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

  
  /**
   * Å°¸¦ Á¶ÇÕÇÕ´Ï´Ù. ¿¹) ABC + ÇöÀç½Ã°£: ABC1234567890123
   * @return
   */
  public static synchronized String key(){
    String code = "";
    
    //  ASCII: 65 ~ 90, (A ~ Z: 26ÀÚ)
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

    code = code + new Date().getTime(); // 1000 = 1ÃÊ
    // System.out.println("CODE: " + code); // CODE: XGL1449819012230 
    
    return code;
  }
 
  // ¹®ÀÚ¿­À» nullÀÎÁö °Ë»çÇÏ¿© null ÀÌ¸é ""À¸·Î º¯È¯ÇÏ¿© ¸®ÅÏ  
  public static synchronized String checkNull(String str){
    if (str == null){
      str = "";
    } else if (str.equals("null")){
      str = "";
    }
      
    return str;
  }
  
  /**
   * ÀÌ¹ÌÁö »çÀÌÁî¸¦ º¯°æÇÏ¿© »õ·Î¿î ÀÌ¹ÌÁö¸¦ »ý¼ºÇÕ´Ï´Ù.
   <pre>
   »ç¿ë¿¹): Tool.imgResize(srcFile °´Ã¼, destFile °´Ã¼, 200, 150)
   </pre>
   * @param src ¿øº» ÀÌ¹ÌÁö ÆÄÀÏ
   * @param dest »ý¼ºµÇ´Â ÀÌ¹ÌÁö ÆÄÀÏ
   * @param width »ý¼ºµÉ ÀÌ¹ÌÁö ³Êºñ
   * @param height »ý¼ºµÉ ÀÌ¹ÌÁö ³ôÀÌ, ImageUtil.RATIO´Â ÀÚµ¿ ºñ·Ê ºñÀ²
   * @return »ý¼ºµÈ ÀÌ¹ÌÁö ÆÄÀÏ¸í ¸®ÅÏ, src.jpg -> dest.jpg
   */
  public static synchronized String imgResize(File src, File dest, int width,
      int height) {
    int RATIO = 0;
    int SAME = -1;

    Image srcImg = null;
    // ÆÄÀÏÀÇ È®ÀåÀÚ ÃßÃâ
    String name = src.getName().toLowerCase(); // ÆÄÀÏ¸í ÃßÃâ
    // ÀÌ¹ÌÁö ÆÄÀÏÀÎÁö °Ë»ç
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png")
        || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // ¸Þ¸ð¸®¿¡ ¿øº» ÀÌ¹ÌÁö »ý¼º
        int srcWidth = srcImg.getWidth(null); // ¿øº» ÀÌ¹ÌÁö ³Êºñ ÃßÃâ
        int srcHeight = srcImg.getHeight(null); // ¿øº» ÀÌ¹ÌÁö ³ôÀÌ ÃßÃâ
        int destWidth = -1, destHeight = -1; // ´ë»ó ÀÌ¹ÌÁö Å©±â ÃÊ±âÈ­

        if (width == SAME) { // width°¡ °°Àº °æ¿ì
          destWidth = srcWidth;
        } else if (width > 0) {
          destWidth = width; // »õ·Î¿î width¸¦ ÇÒ´ç
        }

        if (height == SAME) { // ³ôÀÌ°¡ °°Àº °æ¿ì
          destHeight = srcHeight;
        } else if (height > 0) {
          destHeight = height; // »õ·Î¿î ³ôÀÌ·Î ÇÒ´ç
        }

        // ºñÀ²¿¡ µû¸¥ Å©±â °è»ê
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

        // ¸Þ¸ð¸®¿¡ ´ë»ó ÀÌ¹ÌÁö »ý¼º
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight,
            Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);

        pg.grabPixels();

        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);

        // ÆÄÀÏ¿¡ ±â·Ï
        ImageIO.write(destImg, "jpg", dest);

        System.out.println(dest.getName() + " ÀÌ¹ÌÁö¸¦ »ý¼ºÇß½À´Ï´Ù.");
      } catch (Exception e) {
        e.printStackTrace();
      }

      name = dest.getName(); // »õ·Î »ý¼ºµÈ ÆÄÀÏ¸í
    }

    return name;
  }

  /**
   * ÀÌ¹ÌÁö »çÀÌÁî¸¦ º¯°æÇÏ¿© »õ·Î¿î Preview ÀÌ¹ÌÁö¸¦ »ý¼ºÇÕ´Ï´Ù.
   <pre>
   »ç¿ë¿¹): Too.preview(folder ¸í, ¿øº» ÆÄÀÏ¸í, 200, 150)
   </pre>
   * @param upDir ¿øº» ÀÌ¹ÌÁö Æú´õ
   * @param _src ¿øº» ÆÄÀÏ¸í
   * @param width »ý¼ºµÉ ÀÌ¹ÌÁö ³Êºñ
   * @param height  »ý¼ºµÉ ÀÌ¹ÌÁö ³ôÀÌ, ImageUtil.RATIO´Â ÀÚµ¿ ºñ·Ê ºñÀ²
   * @return src.jpg ÆÄÀÏÀ» ÀÌ¿ëÇÏ¿© src_t.jpg ÆÄÀÏÀ» »ý¼ºÇÏ¿© ÆÄÀÏ¸í ¸®ÅÏ
   */
  public static synchronized String preview(String upDir, String _src, int width,
      int height) {
    int RATIO = 0;
    int SAME = -1;

    File src = new File(upDir + "/" + _src); // ¿øº» ÆÄÀÏ °´Ã¼ »ý¼º
    String srcname = src.getName(); // ¿øº» ÆÄÀÏ¸í ÃßÃâ

    // ¼ø¼ö ÆÄÀÏ¸í ÃßÃâ, mt.jpg -> mt ¸¸ ÃßÃâ
    String _dest = srcname.substring(0, srcname.indexOf("."));

    // Ãà¼Ò ÀÌ¹ÌÁö Á¶ÇÕ /upDir/mt_t.jpg
    File dest = new File(upDir + "/" + _dest + "_t.jpg");

    Image srcImg = null;
    // ÆÄÀÏÀÇ È®ÀåÀÚ ÃßÃâ
    String name = src.getName().toLowerCase(); // ÆÄÀÏ¸í ÃßÃâ
    // ÀÌ¹ÌÁö ÆÄÀÏÀÎÁö °Ë»ç
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png")
        || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // ¸Þ¸ð¸®¿¡ ¿øº» ÀÌ¹ÌÁö »ý¼º
        int srcWidth = srcImg.getWidth(null); // ¿øº» ÀÌ¹ÌÁö ³Êºñ ÃßÃâ
        int srcHeight = srcImg.getHeight(null); // ¿øº» ÀÌ¹ÌÁö ³ôÀÌ ÃßÃâ
        int destWidth = -1, destHeight = -1; // ´ë»ó ÀÌ¹ÌÁö Å©±â ÃÊ±âÈ­

        if (width == SAME) { // width°¡ °°Àº °æ¿ì
          destWidth = srcWidth;
        } else if (width > 0) {
          destWidth = width; // »õ·Î¿î width¸¦ ÇÒ´ç
        }

        if (height == SAME) { // ³ôÀÌ°¡ °°Àº °æ¿ì
          destHeight = srcHeight;
        } else if (height > 0) {
          destHeight = height; // »õ·Î¿î ³ôÀÌ·Î ÇÒ´ç
        }

        // ºñÀ²¿¡ µû¸¥ Å©±â °è»ê
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

        // ¸Þ¸ð¸®¿¡ ´ë»ó ÀÌ¹ÌÁö »ý¼º
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight,
            Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);

        pg.grabPixels();

        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);

        // ÆÄÀÏ¿¡ ±â·Ï
        ImageIO.write(destImg, "jpg", dest);

        System.out.println(dest.getName() + " ÀÌ¹ÌÁö¸¦ »ý¼ºÇß½À´Ï´Ù.");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return dest.getName();
  }

  /**
   * Youtube ÀÌ¹ÌÁöÀÇ Ãà¼Ò Ãâ·Â
   * @param str ÁÖ¼Ò
   * @param width º¯°æÇÒ ³Êºñ
   * @param height º¯°æÇÒ ³ôÀÌ
   * @return
   */
  public static synchronized String youtube(String str, int width, int height) {
    int index1 = str.indexOf("\""); // Ã¹¹øÂ° ¹®ÀÚ °Ë»ö
    int index2 = str.indexOf("\"", index1 + 1); // Ã¹¹øÂ° ÀÌÈÄºÎÅÍ °Ë»ö
    int index3 = str.indexOf("\"", index2 + 1); // µÎ¹øÂ° ÀÌÈÄºÎÅÍ °Ë»ö
    int index4 = str.indexOf("\"", index3 + 1); // ¼¼¹øÂ° ÀÌÈÄºÎÅÍ °Ë»ö
    // System.out.println("--> index1: " + index1);
    // System.out.println("--> index2: " + index2);
    // System.out.println("--> index3: " + index3);
    // System.out.println("--> index4: " + index4);

    String str1 = str.substring(0, index1 + 1); // <iframe width='
    String str2 = str.substring(index2, index3 + 1); // ' height='
    String str3 = str.substring(index4); // ' src=ÀÌÈÄÀÇ ¸ðµç ¹®ÀÚ¿­
    String url = str1 + width + "px" + str2 + height + "px" + str3;

    // System.out.println("--> str1: " + str1);
    // System.out.println("--> str1: " + str2);
    // System.out.println("--> str1: " + str3);
    return url;
  }

  /**
   * ÆÄÀÏÀ» »èÁ¦ÇÕ´Ï´Ù.
   */
  public static synchronized boolean deleteFile(String folder, String fileName) {
    boolean ret = false;

    try {
      if (fileName != null) { // ±âÁ¸¿¡ ÆÄÀÏÀÌ Á¸ÀçÇÏ´Â °æ¿ì »èÁ¦
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
   * ÆÄÀÏ »èÁ¦
   * @param fname
   * @return
   */
  public static synchronized boolean deleteFile(String fname) {
    File file = new File(fname);
    boolean ret = false;
    
      if (file.exists()){
        ret = file.delete();
      }
    
    return ret;
  }
  
  /**
   * ÆÄÀÏÀÌ ÀÌ¹ÌÁöÀÎÁö °Ë»çÇÕ´Ï´Ù.
   * @param file ÆÄÀÏ¸í
   * @return true: ÀÌ¹ÌÁö, false: ÀÏ¹Ý ÆÄÀÏ
   */
  public static synchronized boolean isImage(String file) {
    boolean sw = false;
 
    if (file != null) {
      file = file.toLowerCase(); // ¼Ò¹®ÀÚ
      if (file.endsWith(".jpg") || file.endsWith(".jpeg")
          || file.endsWith(".png") || file.endsWith(".gif")) {
        sw = true;
      }
    }
    return sw;
  }

  /**
   * ÆÄÀÏ ¿ë·®ÀÇ ´ÜÀ§¸¦ Ãß°¡ÇÕ´Ï´Ù.
   * @param size ÆÄÀÏ Å©±â
   * @return KB, MB, GB, TB
   */
  public static synchronized String unit(long size) {
    String str = "";
 
    if (size < (1024 * 1024)) { // 0 ~ 1048575, 1MB º¸´Ù ÀÛÀº °æ¿ì
      str = (int) (Math.ceil(size / 1024.0)) + " KB";
    } else if (size < (1024 * 1024 * 1024)) { // 1 GB º¸´Ù ÀÛÀº °æ¿ì
      str = (int) (Math.ceil(size / 1024.0 / 1024.0)) + " MB";
    } else if (size < (1024 * 1024 * 1024 * 1024)) { // 1 TB º¸´Ù ÀÛÀº °æ¿ì
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0)) + " GB";
    } else {
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0)) + " TB";
    }
 
    return str;
  }
  
  /**
   * ¹®ÀÚ¿­À» ÁöÁ¤µÈ ±æÀÌ·Î Â©¶ó³À´Ï´Ù.
   * 
   * @param str
   * @param length
   * @return
   */
  public static synchronized String trim(String str, int length) {
    String _str = "";

    if (str != null) { // ¹®ÀÚ¿­ÀÌ ÀÖ´Ù¸é
      if (str.length() > length) { // ¹®ÀÚ¿­ÀÇ ±æÀÌ¸¦ °Ë»ç
        _str = str.substring(0, length) + "..."; // ÁöÁ¤µÈ ±æÀÌÀÇ ¹®ÀÚ¸¸ ÃßÃâ
      } else {
        _str = str; // ¿øÆÇ ±×´ë·Î »ç¿ë
      }
    } else {
      _str = "";
    }

    return _str;
  }

  /**
   * Æ¯¼ö¹®ÀÚ¸¦ ÄÚµå·Î º¯È¯ÇÕ´Ï´Ù. static: °´Ã¼¸¦ ¸¸µéÁö¾Ê°í È£Ãâ°¡´ÉÇÕ´Ï´Ù. synchronized: µ¿½Ã Á¢¼ÓÀ» ¼øÂ÷Á¢¼ÓÀ¸·Î
   * º¯È¯ÇÕ´Ï´Ù.
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
   * Æú´õ¸¦ ÀÔ·Â¹Þ¾Æ Àý´ë °æ·Î¸¦ »êÃâÇÕ´Ï´Ù. 
   * ¿¹) getRealPath(request, "/media/storage")
   * 
   * @param request
   * @param dir Àý´ë °æ·Î¸¦ ±¸ÇÒ Æú´õ¸í
   * @return Àý´ë °æ·Î ¸®ÅÏ
   * @throws IOException
   */
  public static synchronized String getRealPath(HttpServletRequest request, String dir) {
    String path = "";
    
    try{
      path = request.getRealPath(dir) + "/";  
      // System.out.println("Upload path: " + path);
    }catch(Exception e){
      System.out.println(e.toString());
    }

    return path;
  }
  
}


=======


public class Tool {
  
  /**
   * ï¿½ï¿½ï¿½Ú¿ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ì°ï¿½ length ï¿½ï¿½ï¿½ï¿½ Å©ï¿½ï¿½ "..." ï¿½ï¿½ Ç¥ï¿½ï¿½ï¿½Ï´ï¿½ ï¿½Þ¼Òµï¿½
   * 
   * @param str ï¿½ï¿½ï¿½Ú¿ï¿½
   * @param length ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ú¿ï¿½ ï¿½ï¿½ï¿½ï¿½
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
   * Å°ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Õ´Ï´ï¿½. ï¿½ï¿½) ABC + ï¿½ï¿½ï¿½ï¿½Ã°ï¿½: ABC1234567890123
   * @return
   */
  public static synchronized String key(){
    String code = "";
    
    //  ASCII: 65 ~ 90, (A ~ Z: 26ï¿½ï¿½)
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

  
  // ï¿½ï¿½ï¿½Ú¿ï¿½ï¿½ï¿½ nullï¿½ï¿½ï¿½ï¿½ ï¿½Ë»ï¿½ï¿½Ï¿ï¿½ null ï¿½Ì¸ï¿½ ""ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½È¯ï¿½Ï¿ï¿½ ï¿½ï¿½ï¿½ï¿½  
  public static synchronized String checkNull(String str){
    if (str == null){
      str = "";
    } else if(str.equals("null")){
      str = "";
    }
      
    return str;
  }
  
  /**
   * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ë»ï¿½ï¿½Õ´Ï´ï¿½.
   * @param file ï¿½ï¿½ï¿½Ï¸ï¿½
   * @return true: ï¿½Ì¹ï¿½ï¿½ï¿½, false: ï¿½Ï¹ï¿½ ï¿½ï¿½ï¿½ï¿½
   */
  public static synchronized boolean isImage(String file) {
    boolean sw = false;
 
    if (file != null) {
      file = file.toLowerCase(); // ï¿½Ò¹ï¿½ï¿½ï¿½
      if (file.endsWith(".jpg") || file.endsWith(".jpeg")
          || file.endsWith(".png") || file.endsWith(".gif")) {
        sw = true;
      }
    }
    return sw;
  }

  /**
   * ï¿½ï¿½ï¿½ï¿½ ï¿½ë·®ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ß°ï¿½ï¿½Õ´Ï´ï¿½.
   * @param size ï¿½ï¿½ï¿½ï¿½ Å©ï¿½ï¿½
   * @return KB, MB, GB, TB
   */
  public static synchronized String unit(long size) {
    String str = "";
 
    if (size < (1024 * 1024)) { // 0 ~ 1048575, 1MB ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½
      str = (int) (Math.ceil(size / 1024.0)) + " KB";
    } else if (size < (1024 * 1024 * 1024)) { // 1 GB ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½
      str = (int) (Math.ceil(size / 1024.0 / 1024.0)) + " MB";
    } else if (size < (1024 * 1024 * 1024 * 1024)) { // 1 TB ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0)) + " GB";
    } else {
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0)) + " TB";
    }
 
    return str;
  }
  
  /**
   * ï¿½ï¿½ï¿½Ú¿ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ì·ï¿½ Â©ï¿½ï¿½ï¿½ï¿½Ï´ï¿½.
   * 
   * @param str
   * @param length
   * @return
   */
  public static synchronized String trim(String str, int length) {
    String _str = "";
 
    if (str != null) { // ï¿½ï¿½ï¿½Ú¿ï¿½ï¿½ï¿½ ï¿½Ö´Ù¸ï¿½
      if (str.length() > length) { // ï¿½ï¿½ï¿½Ú¿ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ì¸ï¿½ ï¿½Ë»ï¿½
        _str = str.substring(0, length) + "..."; // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ú¸ï¿½ ï¿½ï¿½ï¿½ï¿½
      } else {
        _str = str; // ï¿½ï¿½ï¿½ï¿½ ï¿½×´ï¿½ï¿½ ï¿½ï¿½ï¿½
      }
    } else {
      _str = "";
    }
 
    return _str;
  }
  
  /**
   * Æ¯ï¿½ï¿½ï¿½ï¿½ï¿½Ú¸ï¿½ ï¿½Úµï¿½ï¿½ ï¿½ï¿½È¯ï¿½Õ´Ï´ï¿½. static: ï¿½ï¿½Ã¼ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ê°ï¿½ È£ï¿½â°¡ï¿½ï¿½ï¿½Õ´Ï´ï¿½. synchronized: ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
   * ï¿½ï¿½È¯ï¿½Õ´Ï´ï¿½.
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
   * ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½î¸¦ ï¿½ï¿½ï¿½ï¿½ï¿½Ï¿ï¿½ ï¿½ï¿½ï¿½Î¿ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Õ´Ï´ï¿½.
   <pre>
   ï¿½ï¿½ë¿¹): Too.imgResize(srcFile ï¿½ï¿½Ã¼, destFile ï¿½ï¿½Ã¼, 200, 150)
   </pre>
   * @param src ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
   * @param dest ï¿½ï¿½ï¿½ï¿½ï¿½Ç´ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
   * @param width ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½Êºï¿½
   * @param height ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½, ImageUtil.RATIOï¿½ï¿½ ï¿½Úµï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
   * @return ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ï¸ï¿½ ï¿½ï¿½ï¿½ï¿½, src.jpg -> dest.jpg
   */
  public static synchronized String imgResize(File src, File dest, int width,
      int height) {
    int RATIO = 0;
    int SAME = -1;
 
    Image srcImg = null;
    // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ È®ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
    String name = src.getName().toLowerCase(); // ï¿½ï¿½ï¿½Ï¸ï¿½ ï¿½ï¿½ï¿½ï¿½
    // ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ë»ï¿½
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png")
        || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // ï¿½Þ¸ð¸®¿ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
        int srcWidth = srcImg.getWidth(null); // ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½Êºï¿½ ï¿½ï¿½ï¿½ï¿½
        int srcHeight = srcImg.getHeight(null); // ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
        int destWidth = -1, destHeight = -1; // ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ Å©ï¿½ï¿½ ï¿½Ê±ï¿½È­
 
        if (width == SAME) { // widthï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½
          destWidth = srcWidth;
        } else if (width > 0) {
          destWidth = width; // ï¿½ï¿½ï¿½Î¿ï¿½ widthï¿½ï¿½ ï¿½Ò´ï¿½
        }
 
        if (height == SAME) { // ï¿½ï¿½ï¿½Ì°ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½
          destHeight = srcHeight;
        } else if (height > 0) {
          destHeight = height; // ï¿½ï¿½ï¿½Î¿ï¿½ ï¿½ï¿½ï¿½Ì·ï¿½ ï¿½Ò´ï¿½
        }
 
        // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ Å©ï¿½ï¿½ ï¿½ï¿½ï¿½
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
 
        // ï¿½Þ¸ð¸®¿ï¿½ ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight,
            Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);
 
        pg.grabPixels();
 
        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);
 
        // ï¿½ï¿½ï¿½Ï¿ï¿½ ï¿½ï¿½ï¿½
        ImageIO.write(destImg, "jpg", dest);
 
        System.out.println(dest.getName() + " ï¿½Ì¹ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ß½ï¿½ï¿½Ï´ï¿½.");
      } catch (Exception e) {
        e.printStackTrace();
      }
 
      name = dest.getName(); // ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ï¸ï¿½
    }
 
    return name;
  }
  
  /**
   * ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½î¸¦ ï¿½ï¿½ï¿½ï¿½ï¿½Ï¿ï¿½ ï¿½ï¿½ï¿½Î¿ï¿½ Preview ï¿½Ì¹ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Õ´Ï´ï¿½.
   <pre>
   ï¿½ï¿½ë¿¹): Too.preview(folder ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ï¸ï¿½, 200, 150)
   </pre>
   * @param upDir ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
   * @param _src ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ï¸ï¿½
   * @param width ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½Êºï¿½
   * @param height  ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½, ImageUtil.RATIOï¿½ï¿½ ï¿½Úµï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
   * @return src.jpg ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¿ï¿½ï¿½Ï¿ï¿½ src_t.jpg ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ï¿ï¿½ ï¿½ï¿½ï¿½Ï¸ï¿½ ï¿½ï¿½ï¿½ï¿½
   */
  public static synchronized String preview(String upDir, String _src, int width,
      int height) {
    int RATIO = 0;
    int SAME = -1;
 
    File src = new File(upDir + "/" + _src); // ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Ã¼ ï¿½ï¿½ï¿½ï¿½
    String srcname = src.getName(); // ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ï¸ï¿½ ï¿½ï¿½ï¿½ï¿½
 
    // ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ï¸ï¿½ ï¿½ï¿½ï¿½ï¿½, mt.jpg -> mt ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
    String _dest = srcname.substring(0, srcname.indexOf("."));
 
    // ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ /upDir/mt_t.jpg
    File dest = new File(upDir + "/" + _dest + "_t.jpg");
 
    Image srcImg = null;
    // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ È®ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
    String name = src.getName().toLowerCase(); // ï¿½ï¿½ï¿½Ï¸ï¿½ ï¿½ï¿½ï¿½ï¿½
    // ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ë»ï¿½
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png")
        || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // ï¿½Þ¸ð¸®¿ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
        int srcWidth = srcImg.getWidth(null); // ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½Êºï¿½ ï¿½ï¿½ï¿½ï¿½
        int srcHeight = srcImg.getHeight(null); // ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
        int destWidth = -1, destHeight = -1; // ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ Å©ï¿½ï¿½ ï¿½Ê±ï¿½È­
 
        if (width == SAME) { // widthï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½
          destWidth = srcWidth;
        } else if (width > 0) {
          destWidth = width; // ï¿½ï¿½ï¿½Î¿ï¿½ widthï¿½ï¿½ ï¿½Ò´ï¿½
        }
 
        if (height == SAME) { // ï¿½ï¿½ï¿½Ì°ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½
          destHeight = srcHeight;
        } else if (height > 0) {
          destHeight = height; // ï¿½ï¿½ï¿½Î¿ï¿½ ï¿½ï¿½ï¿½Ì·ï¿½ ï¿½Ò´ï¿½
        }
 
        // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ Å©ï¿½ï¿½ ï¿½ï¿½ï¿½
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
 
        // ï¿½Þ¸ð¸®¿ï¿½ ï¿½ï¿½ï¿½ ï¿½Ì¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight,
            Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);
 
        pg.grabPixels();
 
        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);
 
        // ï¿½ï¿½ï¿½Ï¿ï¿½ ï¿½ï¿½ï¿½
        ImageIO.write(destImg, "jpg", dest);
 
        System.out.println(dest.getName() + " ï¿½Ì¹ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ß½ï¿½ï¿½Ï´ï¿½.");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
 
    return dest.getName();
  }
  
  /**
   * Youtube ï¿½Ì¹ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½
   * @param str ï¿½Ö¼ï¿½
   * @param width ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Êºï¿½
   * @param height ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
   * @return
   */
  public static synchronized String youtube(String str, int width, int height) {
    int index1 = str.indexOf("\""); // Ã¹ï¿½ï¿½Â° ï¿½ï¿½ï¿½ï¿½ ï¿½Ë»ï¿½
    int index2 = str.indexOf("\"", index1 + 1); // Ã¹ï¿½ï¿½Â° ï¿½ï¿½ï¿½Äºï¿½ï¿½ï¿½ ï¿½Ë»ï¿½
    int index3 = str.indexOf("\"", index2 + 1); // ï¿½Î¹ï¿½Â° ï¿½ï¿½ï¿½Äºï¿½ï¿½ï¿½ ï¿½Ë»ï¿½
    int index4 = str.indexOf("\"", index3 + 1); // ï¿½ï¿½ï¿½ï¿½Â° ï¿½ï¿½ï¿½Äºï¿½ï¿½ï¿½ ï¿½Ë»ï¿½
    // System.out.println("--> index1: " + index1);
    // System.out.println("--> index2: " + index2);
    // System.out.println("--> index3: " + index3);
    // System.out.println("--> index4: " + index4);
 
    String str1 = str.substring(0, index1 + 1); // <iframe width='
    String str2 = str.substring(index2, index3 + 1); // ' height='
    String str3 = str.substring(index4); // ' src=ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Ú¿ï¿½
    String url = str1 + width + "px" + str2 + height + "px" + str3;
 
    // System.out.println("--> str1: " + str1);
    // System.out.println("--> str1: " + str2);
    // System.out.println("--> str1: " + str3);
    return url;
  }
 
  /**
   * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Õ´Ï´ï¿½.
   */
  public static synchronized boolean deleteFile(String folder, String fileName) {
    boolean ret = false;
 
    try {
      if (fileName != null) { // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ï´ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
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
   * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ë»ï¿½ï¿½Õ´Ï´ï¿½.
   * @param request
   * @return true: ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
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
   * ï¿½Ï¹ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ë»ï¿½ï¿½Õ´Ï´ï¿½.
   * @param request
   * @return true: ï¿½Ï¹ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
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
>>>>>>> branch 'master' of https://github.com/kjh891219/junggo_v1s4m3c.git
