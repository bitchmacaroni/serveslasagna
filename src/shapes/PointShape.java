/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author christian
 */
public class PointShape extends GameShape{

    public PointShape(Color color, int x, int y) {
        super(color, x, y, 0, 0);
    }

    @Override
    public void draw(Graphics2D g2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean wasClicked(Point point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public boolean contactLine(Point point1, Point point2) {
        return false;
    }
}
