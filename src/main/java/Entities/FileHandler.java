package Entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class FileHandler {
    public BufferedImage[] getImagesForAnimation(String animation, int frames) {
        BufferedImage[] output = new BufferedImage[frames];
        try {
            for (int i = 0; i<frames;i++) {
                String temp = "/player/" + animation + "/frame0" + i + ".png";
                output[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(temp)));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
