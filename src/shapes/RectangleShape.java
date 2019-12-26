/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import bodies.MovingBody;
import camera.Camera;
import editor.controller.entityClickers.Draggable;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author christian
 */
 public class RectangleShape extends GameShape implements Draggable{

    public RectangleShape(Color color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        float zoom = Camera.getCurrentZoom();
        g2d.drawRect(Camera.getXReferenceToCamera(getX()-width/2), Camera.getYReferenceToCamera(getY()-height/2), (int)(width*zoom), (int)(height*zoom));
    }

    @Override
    public boolean wasClicked(Point cameraPoint) {
        Point point = Camera.getPointReferenceFromScreen(cameraPoint);
        return Clickable.insideSquareArea(point, getPosition(), width, height);
    }

    @Override
    public boolean contactLine(Point point1, Point point2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RectangleShape copy() {
        RectangleShape returnShape = new RectangleShape(getColor(), getX(), getY(), getWidth(), getHeight());
        super.copyInto(returnShape);
        return returnShape;
    }
    
    
}
