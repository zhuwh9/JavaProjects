import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
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
public class ImageProcessTest {
    private ImplementImageProcessor processor;
    private ImplementImageIO imageioer;
    private String str;
    @Before
    public void setUp() throws Exception {
        processor = new ImplementImageProcessor();
        imageioer = new ImplementImageIO();
        str = "./bmptest/bmptest/1.bmp";
    }
    @Test
    public void testShowChanelR() {
        Image test = processor.showChanelR(imageioer.myRead(str));
        Image goal = imageioer.myRead("./bmptest/bmptest/goal/1_red_goal.bmp");
        if (test != null && goal != null) {
            int width1 = test.getWidth(null);
            int height1 = test.getHeight(null);
            int width2 = goal.getWidth(null);
            int height2 = goal.getHeight(null);
            assertEquals(width1, width2);
            assertEquals(height1, height2);
            int type = BufferedImage.TYPE_INT_RGB;
            BufferedImage test1 = new BufferedImage(width1, height1, type);
            BufferedImage goal1 = new BufferedImage(width2, height2, type);
            for (int i = 0; i < width1; i++) {
                for (int j = 0; j < height1; j++) {
                    assertEquals(test1.getRGB(i, j), goal1.getRGB(i, j));
                }
            }       
        }
    }
    @Test
    public void testShowChanelG() {
        Image test = processor.showChanelG(imageioer.myRead(str));
        Image goal = imageioer.myRead("./bmptest/bmptest/goal/1_green_goal.bmp");
        if (test != null && goal != null) {
            int width1 = test.getWidth(null);
            int height1 = test.getHeight(null);
            int width2 = goal.getWidth(null);
            int height2 = goal.getHeight(null);
            assertEquals(width1, width2);
            assertEquals(height1, height2);
            int type = BufferedImage.TYPE_INT_RGB;
            BufferedImage test1 = new BufferedImage(width1, height1, type);
            BufferedImage goal1 = new BufferedImage(width2, height2, type);
            for (int i = 0; i < width1; i++) {
                for (int j = 0; j < height1; j++) {
                    assertEquals(test1.getRGB(i, j), goal1.getRGB(i, j));
                }
            }       
        }
    }
    @Test
    public void testShowChanelB() {
        Image test = processor.showChanelB(imageioer.myRead(str));
        Image goal = imageioer.myRead("./bmptest/bmptest/goal/1_blue_goal.bmp");
        if (test != null && goal != null) {
            int width1 = test.getWidth(null);
            int height1 = test.getHeight(null);
            int width2 = goal.getWidth(null);
            int height2 = goal.getHeight(null);
            assertEquals(width1, width2);
            assertEquals(height1, height2);
            int type = BufferedImage.TYPE_INT_RGB;
            BufferedImage test1 = new BufferedImage(width1, height1, type);
            BufferedImage goal1 = new BufferedImage(width2, height2, type);
            for (int i = 0; i < width1; i++) {
                for (int j = 0; j < height1; j++) {
                    assertEquals(test1.getRGB(i, j), goal1.getRGB(i, j));
                }
            }       
        }
    }
    @Test
    public void testShowGray() {
        Image test = processor.showGray(imageioer.myRead(str));
        Image goal = imageioer.myRead("./bmptest/bmptest/goal/1_gray_goal.bmp");
        if (test != null && goal != null) {
            int width1 = test.getWidth(null);
            int height1 = test.getHeight(null);
            int width2 = goal.getWidth(null);
            int height2 = goal.getHeight(null);
            assertEquals(width1, width2);
            assertEquals(height1, height2);
            int type = BufferedImage.TYPE_INT_RGB;
            BufferedImage test1 = new BufferedImage(width1, height1, type);
            BufferedImage goal1 = new BufferedImage(width2, height2, type);
            for (int i = 0; i < width1; i++) {
                for (int j = 0; j < height1; j++) {
                    assertEquals(test1.getRGB(i, j), goal1.getRGB(i, j));
                }
            }       
        }
    }
}