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
import javax.imageio.ImageIO;

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
                if(conteo < spriteAmount) {
                    imageSet[conteo] = new BufferedImage(widthParcial, heightParcial, image.getType());
                    Graphics2D gr = imageSet[conteo++].createGraphics();
                    gr.drawImage(image, 0, 0, widthParcial, heightParcial, widthParcial * x, heightParcial * y, widthParcial * (x + 1), heightParcial * (y + 1), null);
                    gr.dispose();
                }
            }
        }
        return imageSet;

    }
}
