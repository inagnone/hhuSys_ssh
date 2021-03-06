import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import org.junit.Test;
import zxing.client.j2se.MatrixToImageWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Administrator on 2017/2/20.
 */
public class ImageEditorTest {

    public void test1(){
        int width = 100;
        int height = 100;
        String s = "你好";

        File file = new File("d:/image.jpg");

        Font font = new Font("Serif", Font.BOLD, 10);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D)bi.getGraphics();
        g2.setBackground(Color.WHITE);
        g2.clearRect(0, 0, width, height);
        g2.setPaint(Color.RED);

        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(s, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;

        g2.drawString(s, (int)x, (int)baseY);

        try {
            ImageIO.write(bi, "jpg", file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void exportImg2(){
        try {

            URL url = ClassLoader.getSystemClassLoader().getResource("./");
            String path = url.getPath();
            String p = path + "../cjcx/WEB-INF/resources/img/diplomaTemplate.jpg";
            System.out.println(p);
            File file = new File(p);
            boolean exists = file.exists();
            //1.jpg是你的 主图片的路径
            InputStream is = new FileInputStream(p);

            //通过JPEG图象流创建JPEG数据流解码器
            JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
            //解码当前JPEG数据流，返回BufferedImage对象
            BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
            //得到画笔对象
            Graphics g = buffImg.getGraphics();
            //创建你要附加的图象。
            //小图片的路径
            Image image = createBarCode("http://www.baidu.com");
            ImageIcon imgIcon = new ImageIcon(image);

            //得到Image对象。
            Image img = imgIcon.getImage();

            //将小图片绘到大图片上。
            //5,300 .表示你的小图片在大图片上的位置。
            g.drawImage(image,561,2736,null);

            //设置颜色。
            g.setColor(Color.BLACK);


            //最后一个参数用来设置字体的大小
            Font f = new Font("宋体",Font.PLAIN,100);
            Color mycolor = Color.black;//new Color(0, 0, 255);
            g.setColor(mycolor);
            g.setFont(f);
            //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
            g.drawString("demo",1280,1118);

            g.dispose();
            OutputStream os;
            //os = new FileOutputStream("d:/union.jpg");
            String shareFileName = "demo.jpg";
            os = new FileOutputStream(shareFileName);
            //创键编码器，用于编码内存中的图象数据。
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
            en.encode(buffImg);

            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ImageFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Image createBarCode(String url){
        String qrcodeFilePath = "";
        try {
            int qrcodeWidth = 300;
            int qrcodeHeight = 300;
            String qrcodeFormat = "png";
            HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, qrcodeWidth, qrcodeHeight, hints);
            BufferedImage image = new BufferedImage(qrcodeWidth, qrcodeHeight, BufferedImage.TYPE_INT_RGB);
            Random random = new Random();
            File QrcodeFile = new File("test.jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(QrcodeFile);
            ImageIO.write(image, qrcodeFormat, QrcodeFile);
            MatrixToImageWriter.writeToStream(bitMatrix, qrcodeFormat, fileOutputStream);
            BufferedImage image1 = ImageIO.read(QrcodeFile);
            return image1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
