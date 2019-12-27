/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images;

import java.awt.Image;

/**
 *
 * @author christian
 */
public class ImageProperties {

    private Image image;
    private int width;
    private int height;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        if (image == null) {
            System.out.println("image is null");
        }
        else
        {
            System.out.println("image is not null");
        }
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
