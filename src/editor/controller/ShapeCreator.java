/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.controller;

import camera.Camera;
import editor.tool.RectangleTool;
import editor.tool.Tool;
import images.GameImages;
import java.awt.Color;
import java.util.List;
import shapes.GameShape;
import shapes.Point;
import shapes.RectangleShape;

/**
 *
 * @author christian
 */
public class ShapeCreator {

    private Tool tool;
    private GameShape shape;
    private boolean released;
    private int originalX, originalY;

    public ShapeCreator(Tool tool, List<GameShape> shapes, Point mousePos) {
        this.tool = tool;
        originalX = mousePos.getX();
        originalY = mousePos.getY();
        shape = this.getShapeFromTool(tool,Camera.getXReferenceFromScreen(originalX),Camera.getYReferenceFromScreen(originalY));
        shape.setObjectImage("guy.png");
        shape.setScaleX(1);
        shape.setColor(Color.RED);
        shapes.add(shape);
        released = false;
    }

    public final GameShape getShapeFromTool(Tool tool,int x, int y) throws IllegalArgumentException
    {
        if(tool instanceof RectangleTool)
        {
            return new RectangleShape(Color.YELLOW,x,y,0,0);
        }
        else
            throw new IllegalArgumentException("Tipo de herramienta desconocida");
    }
    
    public void handlesClicking(Point mousePos)
    {
        //Camera.getYReferenceFromScreen((originalY+mousePos.getY())/2);
        shape.setX(Camera.getXReferenceFromScreen((originalX+mousePos.getX())/2));
        //shape.setY((originalY+mousePos.getY())/2);
        shape.setY(Camera.getYReferenceFromScreen((originalY+mousePos.getY())/2));
        shape.setWidth(mousePos.getX()-originalX);
        shape.setHeight(mousePos.getY()-originalY);
    }
    
    public void lastAction(List<GameShape> shapes)
    {
        if(shape.getHeight() < 10 && shape.getWidth() < 10)
        {
            shapes.remove(shape);
        }
        else
        {
            shape.setColor(Color.YELLOW);
        }
    }
}
