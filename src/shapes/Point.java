/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

/**
 *
 * @author christian
 */
public class Point {
    private int x;
    private int y;

    public Point(int x,int y) {
        this.x = x;
        this.y = y;
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
    
    public double distanceToPoint(Point point)
    {
        return Math.sqrt(((x-point.x)*(x-point.x))+((y-point.y)*(y-point.y)));
    }
    
    public static boolean pointInOvaloid(int x, int y, int k, int h, int a, int b)
    {
        int xMinusK = x - k, yMinusH = y - h;
        return (((xMinusK*xMinusK)/(a*a)) + ((yMinusH*yMinusH)/(b*b))) <= 1;
    }
    
    public boolean pointInRectangle(GameShape shape)
    {
        return getX() < shape.getMaxX() && getX() > shape.getMinX() && getY() < shape.getMaxY() && getY() > shape.getMinY();
    }
    
    public boolean pointInShape(GameShape shape)
    {
        if(shape instanceof RectangleShape)
        {
            return getX() < shape.getMaxX() && getX() > shape.getMinX() && getY() < shape.getMaxY() && getY() > shape.getMinY();
        }
        else if(shape instanceof OvaloidShape)
        {
            return Point.pointInOvaloid(getX(), getY(), shape.getX(), shape.getY(), shape.getWidth()/2, shape.getHeight()/2);
        }
        return false;
    }
    
}
