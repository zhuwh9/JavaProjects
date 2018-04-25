import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import imagereader.IImageIO;

public class ImplementImageIO implements IImageIO {
    Image img;
    public Image myRead(String path) {
        File file = new File(path);
        try {
            FileInputStream ifs = new FileInputStream(file);
            // create byte array contains info and files' header data
            byte bmpFileHeader[] = new byte[14];
            byte bmpInfoHeader[] = new byte[40];
            // read from file
            ifs.read(bmpFileHeader,0,14);
            ifs.read(bmpInfoHeader,0,40);
            // size of bmp file header
            int size_file = (int)((bmpFileHeader[5] & 0xff) << 24 | (bmpFileHeader[4] & 0xff) << 16 | (bmpFileHeader[5] & 0xff) << 8 | (bmpFileHeader[5] & 0xff));
            // size of bmp info header
            int size_info = (int)((bmpInfoHeader[23] & 0xff) << 24 | (bmpInfoHeader[22] & 0xff) << 16 | (bmpInfoHeader[21] & 0xff) << 8 | (bmpInfoHeader[20] & 0xff));
            // height of bmp
            int height = (int)((bmpInfoHeader[11] & 0xff) << 24 | (bmpInfoHeader[10] & 0xff) << 16 | (bmpInfoHeader[9] & 0xff) << 8 | (bmpInfoHeader[8] & 0xff));
            // width of bmp
            int width = (int)((bmpInfoHeader[7] & 0xff) << 24 | (bmpInfoHeader[6] & 0xff) << 16 | (bmpInfoHeader[5] & 0xff) << 8 | (bmpInfoHeader[4] & 0xff));
            // total bytes that are used
            int bytes = (int)((bmpInfoHeader[15] & 0xff) << 8 | (bmpInfoHeader[14] & 0xff));
            // if total bytes are 24, then calculate the number of empty bytes
            if (bytes == 24) {
                int emptyByte = size_info / height - 3 * width;
                if (emptyByte == 4) {
                    emptyByte = 0;
                }
                int temp = 0;
                int pixels[] = new int[width * height];
                byte arrBmp[] = new byte[size_info];
                ifs.read(arrBmp,0,size_info);
                for (int i = height - 1; i >= 0; i--) {
                    for (int j = 0; j < width; j++) {
                        pixels[width*i+j] = 0xff << 24 | (arrBmp[temp+2] & 0xff) << 16 |(arrBmp[temp+1] & 0xff) << 8 | (arrBmp[temp] & 0xff);
                        temp = temp + 3;
                    }
                    temp = temp + emptyByte;
                }
                // API
                img = Toolkit.getDefaultToolkit().createImage((ImageProducer) new MemoryImageSource(
                    width,height,pixels,0,width));
            }
            ifs.close();
            return img;     
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (Image)null;
    }
    public Image myWrite(Image img, String path) {
        try {
            File file = new File(path+".bmp");
            BufferedImage buffer = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D graph = buffer.createGraphics();
            graph.drawImage(img,0,0,null);
            graph.dispose();
            ImageIO.write(buffer,"bmp",file);
            return img;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return img;
    }
}