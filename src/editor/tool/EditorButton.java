/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.tool;

import java.awt.Color;
import java.awt.Graphics2D;
import shapes.Clickable;
import shapes.GameShape;
import shapes.Point;

/**
 *
 * @author christian
 */
public abstract class EditorButton extends GameShape implements Action{

    public EditorButton(Color color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.drawRect(getX()-width/2, getY()-height/2, width, height);
    }

    @Override
    public boolean wasClicked(Point point) {
        return Clickable.insideSquareArea(point, getPosition(), width, height);
    }

    @Override
    public abstract void doIt();
    
}
