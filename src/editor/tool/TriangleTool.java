/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.tool;

import java.awt.Color;
import java.awt.Graphics2D;
import shapes.GameShape;
import shapes.Point;

/**
 *
 * @author christian
 */
public class TriangleTool extends Tool{
    
    private boolean inverted;

    public TriangleTool(Color color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
        inverted = false;
    }
    
    
    
    public void invert()
    {
        inverted = !inverted;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        int x1 = getX()-(width/2), x2 = getX()+(width/2), y1 = getY()-(height/2),y2 = getY()+(height/2);
        if(inverted)
        {
            g2d.drawPolygon(new int[]{x1,x2,x2}, new int[]{y2,y2,y1},3);
        }
        else
        {
            g2d.drawPolygon(new int[]{x1,x1,x2}, new int[]{y1,y2,y1},3);
        }
        
    }

    @Override
    public boolean wasClicked(Point point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contactLine(Point point1, Point point2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TriangleTool copy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
