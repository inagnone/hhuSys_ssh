import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.junit.Test;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import zxing.client.j2se.MatrixToImageWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Administrator on 2017/2/20.
 */
public class BarCodeTest {

//    @Test
//    public void test(){
//        String qrcodeFilePath = "";
//        try {
//            int qrcodeWidth = 300;
//            int qrcodeHeight = 300;
//            String qrcodeFormat = "png";
//            HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
//            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//            BitMatrix bitMatrix = new MultiFormatWriter().encode("http://www.baidu.com", BarcodeFormat.QR_CODE, qrcodeWidth, qrcodeHeight, hints);
//            BufferedImage image = new BufferedImage(qrcodeWidth, qrcodeHeight, BufferedImage.TYPE_INT_RGB);
//            Random random = new Random();
//            File QrcodeFile = new File("test.jpg");
//            FileOutputStream fileOutputStream = new FileOutputStream(QrcodeFile);
//            ImageIO.write(image, qrcodeFormat, QrcodeFile);
//            MatrixToImageWriter.writeToStream(bitMatrix, qrcodeFormat, fileOutputStream);
//            byte[] bytes = toByteArray(QrcodeFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static byte[] toByteArray(File f) throws IOException {

        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

}
