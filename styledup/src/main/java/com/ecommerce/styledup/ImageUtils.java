package com.ecommerce.styledup;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageUtils {

    /**************************************************NOTE:************************************************************
     * 
     * Iniatially existed to remove background from images uploaded by sellers but will be added in further implementation
     * Replace bright background with a given color
     ************************************************************************************************************************/
    
    public static void replaceBackground(File inputFile, File outputFile, Color bgColor) throws Exception {
        BufferedImage img = ImageIO.read(inputFile);

        int width = img.getWidth();
        int height = img.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);
                Color c = new Color(rgb, true);

                // Simple bright background detection
                if (c.getRed() > 200 && c.getGreen() > 200 && c.getBlue() < 100) { // example for yellowish background
                    img.setRGB(x, y, bgColor.getRGB());
                }
            }
        }

        ImageIO.write(img, "png", outputFile); // save as PNG to preserve transparency if needed
    }
}

