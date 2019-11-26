/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author jorge matte
 */
public class GameImages {

    private static ImageFilter filter;
    private static ArrayList<GameImage> images = new ArrayList<>();

    /*
    private static Image lazer;
    private static Image lazer2;
    private static Image lazer3;
    private static Image ship1;
    private static Image ship2;
    private static Image avengerShip;
    private static Image superShip;
    private static Image smallShip;
    private static Image alienShip1;
    private static Image alienShip2;
    private static Image alienShip3;
    private static Image alienShip4;
    private static Image missile;
    private static Image bullet;
    private static Image space;
    private static Image shopGround;
    private static Image meteor;
    private static Image mediumShip;
    
    private static Image burningFuel;
     */
    private static Image guy;

    private static BufferedImage[] explosion;

    public static void loadAllImages() throws FileNotFoundException, IOException {
        filter = new RGBImageFilter() {
            int transparentColor = Color.white.getRGB() | 0xFF000000;

            @Override
            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == transparentColor) {
                    return 0x00FFFFFF & rgb;
                } else {
                    return rgb;
                }
            }
        };

        Path dir = Paths.get("");
        System.out.println("getting path");
        try ( DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.png")) {
            for (Path file : stream) {
                System.out.println(file.getFileName().toString());
                if (!file.getFileName().toString().toLowerCase().contains("animation")) {
                    ImageProducer filteredImgProd = new FilteredImageSource(new ImageIcon(file.getFileName().toString()).getImage().getSource(), filter);
                    Image image = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
                    GameImage gameImage = new GameImage(image, file.getFileName().toString());
                    images.add(gameImage);
                }
            }
        }
        /*
        ImageProducer filteredImgProd = new FilteredImageSource(new ImageIcon("laser.png").getImage().getSource(), filter);
        lazer = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("laser2.png").getImage().getSource(), filter);
        lazer2 = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("laser3.png").getImage().getSource(), filter);
        lazer3 = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("ship.png").getImage().getSource(), filter);
        ship1 = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("ship2.png").getImage().getSource(), filter);
        ship2 = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("alienShip1.png").getImage().getSource(), filter);
        alienShip1 = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("alienShip2.png").getImage().getSource(), filter);
        alienShip2 = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("alienShip3.png").getImage().getSource(), filter);
        alienShip3 = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("alienShip4.png").getImage().getSource(), filter);
        alienShip4 = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("missile.png").getImage().getSource(), filter);
        missile = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("bullet.png").getImage().getSource(), filter);
        bullet = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("superShip.png").getImage().getSource(), filter);
        superShip = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("smallShip.png").getImage().getSource(), filter);
        smallShip = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        ImageProducer filteredImgProd = new FilteredImageSource(new ImageIcon("guy.png").getImage().getSource(), filter);
        guy = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        /*
        filteredImgProd = new FilteredImageSource(new ImageIcon("orangeShip.png").getImage().getSource(), filter);
        mediumShip = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("fuel.png").getImage().getSource(), filter);
        burningFuel = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("avengerShip.png").getImage().getSource(), filter);
        avengerShip = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(new ImageIcon("meteor.png").getImage().getSource(), filter);
        meteor = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        
        space = new ImageIcon("space2.jpg").getImage();
        shopGround = new ImageIcon("shopGround.jpg").getImage();
         */

 /*
        File file = new File("explosions.png"); // I have bear.jpg in my working directory
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis); //reading the image file
        int filas = 4;
        int columnas = 4;
        int spriteAmount = filas * columnas;
        int widthParcial = image.getWidth() / columnas;
        int heightParcial = image.getHeight() / filas;
        int conteo = 0;
        explosion = new BufferedImage[spriteAmount];
        for (int y = 0; y < filas; y++) {
            for (int x = 0; x < columnas; x++) {
                explosion[conteo] = new BufferedImage(widthParcial, heightParcial, image.getType());
                Graphics2D gr = explosion[conteo++].createGraphics();
                gr.drawImage(image, 0, 0, widthParcial, heightParcial, widthParcial * x, heightParcial * y, widthParcial * (x+1), heightParcial * (y +1), null);
                gr.dispose();
            }
        }
         */
    }

    /*
    public static Image getLazer() {
        return lazer;
    }

    public static Image getShip1() {
        return ship1;
    }
    
    public static Image getAlienShip4() {
        return alienShip4;
    }

    public static Image getAlienShip3() {
        return alienShip3;
    }

    public static Image getAlienShip2() {
        return alienShip2;
    }

    public static Image getAlienShip1() {
        return alienShip1;
    }

    public static Image getMissile() {
        return missile;
    }

    public static Image getBullet() {
        return bullet;
    }

    public static Image getSpace() {
        return space;
    }

    public static Image getShip2() {
        return ship2;
    }

    public static Image getLazer2() {
        return lazer2;
    }

    public static Image getLazer3() {
        return lazer3;
    }

    public static Image getSuperShip() {
        return superShip;
    }

    public static Image getSmallShip() {
        return smallShip;
    }

    public static BufferedImage[] getExplosion() {
        return explosion;
    }

    public static Image getShopGround() {
        return shopGround;
    }

    
    public static Image getGuy() {
        return guy;
    }


    /*
    public static Image getMediumShip() {
        return mediumShip;
    }

    public static Image getBurningFuel() {
        return burningFuel;
    }

    public static Image getAvengerShip() {
        return avengerShip;
    }

    public static Image getMeteor() {
        return meteor;
    }
     */
    public static Image getImage(String name) {
        for (GameImage image : images) {
            if (name.equals(image.getName())) {
                return image.getImage();
            }
        }
        return null;
    }

    public static ArrayList<String> getImagesNames() {
        ArrayList<String> names = new ArrayList<>();
        for (GameImage image : images) {
            names.add(image.getName());
        }
        return names;
    }

    public static String getImageName(Image image) {
        for (GameImage gameImage : images) {
            if (image.equals(gameImage.getImage())) {
                return gameImage.getName();
            }
        }
        return null;
    }
}
