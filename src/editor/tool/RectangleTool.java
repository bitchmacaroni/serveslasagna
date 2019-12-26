/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.tool;

import java.awt.Color;
import java.awt.Graphics2D;
import shapes.Clickable;
import shapes.Point;

/**
 *
 * @author christian
 */
public class RectangleTool extends Tool{

    public RectangleTool(Color color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
    }

    @Override
    public boolean wasClicked(Point point) {
        return Clickable.insideSquareArea(point, getPosition(), width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.drawRect(getX()-width/2, getY()-height/2, width, height);
        
    }

    @Override
    public boolean contactLine(Point point1, Point point2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RectangleTool copy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
