/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images.animations;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author christian
 */
public class AnimationMaker {
    private BufferedImage[] imageSet;
    private String name;
    private int spriteAmount = -1;
    private int xBorderLimit = -1;
    private int yBorderLimit = -1;
    private int filas = 1;
    private int columnas = 1;

    public AnimationMaker() {
    }

    public AnimationMaker withSprites(int amount) {
        if (amount > 0) {
            this.spriteAmount = amount;
        }
        return this;
    }

    public AnimationMaker withName(String name) {
        this.name = name;
        return this;
    }

    public AnimationMaker withXBorder(int limit) {
        if (limit > 0) {
            this.xBorderLimit = limit;
        }
        return this;
    }

    public AnimationMaker withYBorder(int limit) {
        if (limit > 0) {
            this.yBorderLimit = limit;
        }
        return this;
    }

    public AnimationMaker withBorders(int xLimit, int yLimit) {
        if (xLimit > 0) {
            this.xBorderLimit = xLimit;
        }
        if (yLimit > 0) {
            this.yBorderLimit = yLimit;
        }
        return this;
    }

    public AnimationMaker withRowColumns(int row, int columns) {
        if (columns > 0) {
            this.columnas = columns;
        }
        if (row > 0) {
            this.filas = row;
        }
        return this;
    }

    public AnimationMaker withColumns(int columns) {
        if (columns > 0) {
            this.columnas = columns;
        }
        return this;
    }

    public AnimationMaker withRows(int rows) {
        if (rows > 0) {
            this.filas = rows;
        }
        return this;
    }

    public BufferedImage[] makeAnimation() throws FileNotFoundException, IOException {
        File file = new File(name);
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis); //reading the image file
        if (spriteAmount == -1) {
            spriteAmount = filas * columnas;
        }
        int widthParcial = (xBorderLimit == -1 ? image.getWidth() : xBorderLimit) / columnas;
        int heightParcial = (yBorderLimit == -1 ? image.getHeight() : yBorderLimit) / filas;
        int conteo = 0;
        imageSet = new BufferedImage[spriteAmount];
        for (int y = 0; y < filas; y++) {
            for (int x = 0; x < columnas; x++) {
                if (conteo < spriteAmount) {
                    imageSet[conteo] = new BufferedImage(widthParcial, heightParcial, image.getType());
                    Graphics2D gr = imageSet[conteo++].createGraphics();
                    gr.drawImage(image, 0, 0, widthParcial, heightParcial, widthParcial * x, heightParcial * y, widthParcial * (x + 1), heightParcial * (y + 1), null);
                    gr.dispose();
                }
            }
        }
        return imageSet;
    }

    public static BufferedImage[] readGifAnimation(String name) {
        BufferedImage[] images = null;
        try {
            String[] imageatt = new String[]{
                "imageLeftPosition",
                "imageTopPosition",
                "imageWidth",
                "imageHeight"
            };

            ImageReader reader = (ImageReader) ImageIO.getImageReadersByFormatName("gif").next();
            ImageInputStream ciis = ImageIO.createImageInputStream(new File(name));
            reader.setInput(ciis, false);

            int noi = reader.getNumImages(true);
            BufferedImage master = null;
            images = new BufferedImage[noi - 1];

            for (int i = 0; i < noi; i++) {
                BufferedImage image = reader.read(i);
                IIOMetadata metadata = reader.getImageMetadata(i);

                Node tree = metadata.getAsTree("javax_imageio_gif_image_1.0");
                NodeList children = tree.getChildNodes();

                for (int j = 0; j < children.getLength(); j++) {
                    Node nodeItem = children.item(j);

                    if (nodeItem.getNodeName().equals("ImageDescriptor")) {
                        Map<String, Integer> imageAttr = new HashMap<String, Integer>();

                        for (int k = 0; k < imageatt.length; k++) {
                            NamedNodeMap attr = nodeItem.getAttributes();
                            Node attnode = attr.getNamedItem(imageatt[k]);
                            imageAttr.put(imageatt[k], Integer.valueOf(attnode.getNodeValue()));
                        }
                        if (i == 0) {
                            master = new BufferedImage(imageAttr.get("imageWidth"), imageAttr.get("imageHeight"), BufferedImage.TYPE_INT_ARGB);
                        }
                        master.getGraphics().drawImage(image, imageAttr.get("imageLeftPosition"), imageAttr.get("imageTopPosition"), null);
                    }
                }
                //ImageIO.write(image, "GIF", new File(i + ".gif"));
                if (i > 0) {
                    images[i - 1] = image;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("image count: " + images.length);
        return images;
    }
}
