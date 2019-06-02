package com.example.task.util;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class to work with images
 */
public class ImageUtil {
    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");
    private static final int IMAGE_WIDTH = 320;
    private static final int IMAGE_HEIGHT = 240;
    
    private ImageUtil() {
    }
    
    /**
     * Check image format in existing format list
     *
     * @param file to check
     * @return true in case of right format
     */
    public static boolean isValidImageFormat(MultipartFile file) {
        String fileContentType = file.getContentType();
        return contentTypes.contains(fileContentType);
    }
    
    /**
     * method check image width and height
     *
     * @param bytes of image
     * @return true if width and height is valid
     * @throws IOException
     */
    public static boolean isValidImageWidthAndHeight(byte[] bytes) throws IOException {
        InputStream stream = new ByteArrayInputStream(bytes);
        BufferedImage bimg = ImageIO.read(stream);
        return bimg.getWidth() == 320 && bimg.getHeight() == 240;
    }
    
    /**
     * @param bytes  image bytes
     * @param format image format
     * @return resized image bytes
     * @throws IOException
     */
    public static byte[] resizeImage(byte[] bytes, String format) throws IOException {
        InputStream stream = new ByteArrayInputStream(bytes);
        BufferedImage resizeMe = ImageIO.read(stream);
        Dimension newMaxSize = new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT);
        BufferedImage resizedImg = Scalr.resize(resizeMe, Scalr.Mode.AUTOMATIC, newMaxSize.width, newMaxSize.height);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(resizedImg, getImageExtensionFromContentType(format), bos);
        return bos.toByteArray();
    }
    
    private static String getImageExtensionFromContentType(String contentType) {
        return contentType.split("/")[1];
    }
}
