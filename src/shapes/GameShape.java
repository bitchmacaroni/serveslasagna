/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import bodies.Body;
import images.GameImages;
import images.ImageProperties;
import java.awt.Color;
import java.awt.Image;
import sidescrollerproto.SideScrollerProto;

/**
 *
 * @author christian
 */
public abstract class GameShape implements Drawable, Clickable {

    protected Color color;
    private Point position;
    protected int width, height;
    private float rotation;
    private ImageProperties imageProperties;
    private float scale;
    private float paralax;
    Body body = null;

    public GameShape(Color color, int x, int y, int width, int height) {
        this.color = color;
        position = new Point(x, y);
        this.width = width;
        this.height = height;
        scale = 1;
        rotation = 0;
        paralax = 1;
        imageProperties = new ImageProperties();
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return position.getX();
    }

    public void setX(int x) {
        position.setX(x);
    }

    public void increaseX(int cant) {
        position.setX(position.getX() + cant);
    }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        position.setY(y);
    }

    public void increaseY(int cant) {
        position.setY(position.getY() + cant);
    }

    public Point getPosition() {
        return position;
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

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public ImageProperties getImageProperties() {
        return imageProperties;
    }

    public Image getObjectImage() {
        return imageProperties.getImage();
    }

    public void setObjectImage(String name) {
        setObjectImage(GameImages.getImage(name));
    }

    public void setObjectImage(Image image) {
        imageProperties.setImage(image);
        imageProperties.setWidth(getObjectImage().getWidth(SideScrollerProto.getCurrentInstance()));
        imageProperties.setHeight(getObjectImage().getHeight(SideScrollerProto.getCurrentInstance()));
    }

    public float getParalax() {
        return paralax;
    }

    public void setParalax(float paralax) {
        this.paralax = paralax;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    // --- Utility Meth ---
    public int getMaxX() {
        return position.getX() + (width / 2);
    }

    public int getMinX() {
        return position.getX() - (width / 2);
    }

    public int getMaxY() {
        return position.getY() + (height / 2);
    }

    public int getMinY() {
        return position.getY() - (height / 2);
    }

    public void center(Point point) {
        position.setX(point.getX());
        position.setY(point.getY());
    }

    public abstract GameShape copy();

    public GameShape copyInto(GameShape returnShape) {
        returnShape.setColor(color);
        returnShape.setX(getX());
        returnShape.setY(getY());
        returnShape.setWidth(width);
        returnShape.setHeight(height);
        returnShape.getImageProperties().setHeight(getImageProperties().getHeight());
        returnShape.getImageProperties().setWidth(getImageProperties().getWidth());
        returnShape.setRotation(rotation);
        returnShape.setParalax(paralax);
        returnShape.setScale(scale);
        if (getObjectImage() != null) {
            returnShape.setObjectImage(getObjectImage());
        }
        if (body != null) {
            returnShape.setBody(body.copy(returnShape));
        }
        return returnShape;
    }

    //Checks if a line between two points is inside the contact shape
    public abstract boolean contactLine(Point point1, Point point2);

    /**
     * This method determine the point where two line cross. There are some
     * exceptions to this as the purpose of this method was made to calculate
     * whether a a straight moving line is going to meet one of the lines of a
     * rectangle.
     *
     * @param point1Line1
     * @param point2Line1
     * @param point1Line2
     * @param point2Line2
     * @return
     */
    public static Point positiontwoLinesContact(Point point1Line1, Point point2Line1, Point point1Line2, Point point2Line2) {
        int x11 = point1Line1.getX(),
                y11 = point1Line1.getY(),
                x12 = point2Line1.getX(),
                y12 = point2Line1.getY(),
                x21 = point1Line2.getX(),
                y21 = point1Line2.getY(),
                x22 = point2Line2.getX(),
                y22 = point2Line2.getY();

        //This is a safe net in cas things get weird, but they shouldn't.
        if (x11 == x12 || x21 == x22) {
            if (x11 == x21) {
                return point1Line2;
            } else {
                return new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
            }
        } else if ((y12 - y11) * (x22 - x21) == (y22 - y21) * (x12 - x11)) {
            if (((y11 * x12) - (y12 * x11)) * (x22 - x21) == ((y21 * x22) - (y22 * x21)) * (x12 - x11)) {
                return point1Line2;
            } else {
                return new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
            }
        }

        int x = (int) (((((y21 * x22) - (y22 * x21)) * (x12 - x11)) - (((y11 * x12) - (y12 * x11) * (x22 - x21)))) / (((y12 - y11) * (x22 - x21)) - ((y22 - y21) * (x12 - x11))));
        int y;
        if (x11 == x12) {
            y = (int) (((x * (y22 - y21)) - (y22 * x21) + (y21 * x22)) / (x22 - x21));
        } else {
            y = (int) (((x * (y12 - y11)) - (y12 * x11) + (y11 * x12)) / (x12 - x11));
        }
        return new Point(x, y);
    }

    //This method is an aproximation, not for use in precise case
    public static Point positiontwoLinesContact(float m1, int n1, float m2, int n2) {
        int x = (int) ((n2 - n1) / (m1 - m2));
        int y = (int) ((x * m1) + n1);
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "GameShape{" + "color=" + color + ", position=" + position + ", width=" + width + ", height=" + height + ", rotation=" + rotation + ", imageProperties=" + imageProperties + ", scale=" + scale + ", paralax=" + paralax + ", body=" + body + '}';
    }
    
    
}
