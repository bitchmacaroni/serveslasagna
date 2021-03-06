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
public class OvaloidTool extends Tool{

    public OvaloidTool(Color color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.drawOval(getX(), getY(), width, height);
    }

    @Override
    public boolean wasClicked(Point point) {
        if(Clickable.insideSquareArea(point, this.getPosition(), width, height))
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        return false;
    }

    @Override
    public boolean contactLine(Point point1, Point point2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OvaloidTool copy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
