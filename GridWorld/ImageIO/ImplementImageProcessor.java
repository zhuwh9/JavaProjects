import imagereader.IImageProcessor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class ImplementImageProcessor implements IImageProcessor {
    public Image showChanelR(Image source) {
        ColorFilter filter_red = new ColorFilter(1);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(new FilteredImageSource(source.getSource(), filter_red));
        return img;
    }
    public Image showChanelG(Image source) {
        ColorFilter filter_green = new ColorFilter(2);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(new FilteredImageSource(source.getSource(), filter_green));
        return img;
    }
    public Image showChanelB(Image source) {
        ColorFilter filter_blue = new ColorFilter(3);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(new FilteredImageSource(source.getSource(), filter_blue));
        return img;
    }
    public Image showGray(Image source) {
        ColorFilter filter_gray = new ColorFilter(4);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(new FilteredImageSource(source.getSource(), filter_gray));
        return img;
    }
}

class ColorFilter extends RGBImageFilter {
    private int which;
    public ColorFilter(int num) {
        which = num;
        canFilterIndexColorModel = true;
    }
    public int filterRGB(int x, int y, int color) {
        if (which == 1) {
            return (color & 0xffff0000);
        } else if (which == 2) {
            return (color & 0xff00ff00);
        } else if (which == 3) {
            return (color & 0xff0000ff);
        } else {
            int gray = (int)(((color & 0x00ff0000) >> 16) * 0.299 + ((color & 0x0000ff00) >> 8) * 0.587 + (color & 0x000000ff)*0.114);
            return (color & 0xff000000) + (gray << 16) + (gray << 8) + gray;
        }
    }
}