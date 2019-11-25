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
    
    private AnimationMaker withSprites(int amount)
    {
        this.spriteAmount = amount;
        return this;
    }
    
    private AnimationMaker withName(String name)
    {
        this.name = name;
        return this;
    }
    
    private AnimationMaker withXBorder(int limit)
    {
        this.xBorderLimit = limit;
        return this;
    }
    
    private AnimationMaker withYBorder(int limit)
    {
        this.yBorderLimit = limit;
        return this;
    }
    
    private AnimationMaker withBorders(int xLimit, int yLimit)
    {
        this.xBorderLimit = xLimit;
        this.yBorderLimit = yLimit;
        return this;
    }
    
    private AnimationMaker withRowColumns(int row, int columns)
    {
        this.columnas = columns;
        this.filas = row;
        return this;
    }
    
    private AnimationMaker withColumns(int columns)
    {
        this.columnas = columns;
        return this;
    }
    
    private AnimationMaker withRows(int rows)
    {
        this.filas = rows;
        return this;
    }
    
    public BufferedImage[] makeAnimation() throws FileNotFoundException, IOException
    {
        File file = new File(name); 
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis); //reading the image file
        if(spriteAmount == -1)
        {
            spriteAmount = filas*columnas;
        }
        int widthParcial = (xBorderLimit == -1 ? image.getWidth(): xBorderLimit) / columnas;
        int heightParcial = (yBorderLimit == -1 ? image.getHeight(): yBorderLimit) / filas;
        int conteo = 0;
        imageSet = new BufferedImage[spriteAmount];
        for (int y = 0; y < filas; y++) {
            for (int x = 0; x < columnas; x++) {
                while(conteo < spriteAmount){
                    imageSet[conteo] = new BufferedImage(widthParcial, heightParcial, image.getType());
                    Graphics2D gr = imageSet[conteo++].createGraphics();
                    gr.drawImage(image, 0, 0, widthParcial, heightParcial, widthParcial * x, heightParcial * y, widthParcial * (x+1), heightParcial * (y +1), null);
                    gr.dispose();
                }
            }
        }
        return imageSet;
        
    }
}
