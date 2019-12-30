/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images.animations;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author jorge matte
 */
public class Animation {

    private int x;
    private int y;
    private int xOffset;
    private int yOffset;
    private final BufferedImage[] images;
    private Image backgroundImage;
    private float imageSize;
    private float rotation;
    private float backgroundSize;

    private int spriteAmount;
    private int current;
    private int animationLap;
    private int lapCount;
    private final int[][] lapTimes;
    private boolean ended;

    public Animation(final BufferedImage[] parImages, final int[][] pLapTimes) {
        images = parImages;
        spriteAmount = parImages.length;
        lapTimes = pLapTimes;
        current = 0;
        lapCount = 0;
        animationLap = 3;
        ended = false;
        imageSize = 1;
        backgroundImage = null;
        backgroundSize = 1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public float getImageSize() {
        return imageSize;
    }

    public void setImageSize(float imageSize) {
        this.imageSize = imageSize;
    }

    public float getBackgroundSize() {
        return backgroundSize;
    }

    public void setBackgroundSize(float backgroundSize) {
        this.backgroundSize = backgroundSize;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public int getAnimationLap() {
        return animationLap;
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public void setAnimationLap(int animationLap) {
        this.animationLap = animationLap;
    }

    public BufferedImage getImage() {
        return images[lapTimes[1][current]];
    }
    
    public Image getFirstImage()
    {
        return images[0];
    }
    

    public void tickAnimation() {
        lapCount++;
        if (lapCount > lapTimes[0][current]) {
            current++;
            lapCount = 0;
        }
        if (current >= lapTimes[0].length) {
            current = 0;
            //ended = true;
        }
    }

    public boolean isEnded() {
        return ended;
    }

    public Animation cloneAnimation() {
        Animation clone = new Animation(images, lapTimes);
        clone.setxOffset(xOffset);
        clone.setyOffset(yOffset);
        return clone;
    }
}
