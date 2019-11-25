/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.controller;

import auxiliary.GameMouseEvent;
import editor.EntityPropertyScreen;
import editor.tool.EditorButton;
import editor.tool.Tool;
import java.awt.Color;
import java.util.List;
import shapes.Clickable;
import shapes.GameShape;
import shapes.Point;
import sidescrollerproto.SideScrollerProto;

/**
 *
 * @author christian
 */
public class EntityClicker {

    private GameShape selectedItem;
    private GameMouseEvent mouseEvents;
    private List<GameShape> entities;
    private ShapeCreator shapeCreator;
    private boolean triggered;
    private boolean creatingShape;
    private boolean enabled;
    private long lastClick = -6_000_000_000L;
    private SideScrollerProto frameReference;

    //deleteme
    private int nClick = 0;

    public EntityClicker(GameMouseEvent mouseEvents, List<GameShape> entities, SideScrollerProto frameReference) {
        this.mouseEvents = mouseEvents;
        this.entities = entities;
        this.frameReference = frameReference;
        creatingShape = false;
        triggered = false;
        enabled = true;
    }

    public void routineEditorCheck() {
        //First click
        if (enabled) {
            if (mouseEvents.isPressing()) {
                Point clickedPoint = new Point(frameReference.getMouseX(), frameReference.getMouseY());
                if (creatingShape) {
                    shapeCreator.handlesClicking(clickedPoint);
                } else {
                    GameShape shape = (GameShape) Clickable.checkClickableList(entities, clickedPoint);
                    if (shape instanceof Tool) {
                        triggered = true;
                    } else {
                        if (!(selectedItem instanceof Tool)) {
                            triggered = true;
                        } else {
                            if (shapeCreator == null) {
                                shapeCreator = new ShapeCreator((Tool) selectedItem, entities, clickedPoint);
                                creatingShape = true;
                            }
                        }
                    }
                }
            } else if (triggered) {    //Click Release
                //double click implement here
                nClick++;
                triggered = false;
                Point clickedPoint = new Point(frameReference.getMouseX(), frameReference.getMouseY());
                GameShape shape = (GameShape) Clickable.checkClickableList(entities, clickedPoint);
                long time = System.nanoTime();
                if (time - lastClick < 200_000_000L && shape != null && !(shape instanceof Tool || shape instanceof EditorButton)) {
                    enabled = false;
                    shape.setColor(Color.GREEN);
                    new EntityPropertyScreen(shape, this, frameReference.getGameBodies(),frameReference).setVisible(true);
                } else {
                    lastClick = System.nanoTime();
                    //System.out.println("number of click : "+nClick+" last time: "+lastClick +" now: "+System.nanoTime());
                    if (shape != null) {
                        if (!shape.equals(selectedItem)) {
                            if (shape instanceof EditorButton) {
                                ((EditorButton)shape).doIt();
                            } else {
                                if (selectedItem != null) {
                                    selectedItem.setColor(Color.YELLOW);
                                }
                                selectedItem = shape;
                                selectedItem.setColor(Color.GREEN);
                            }
                        } else {
                            selectedItem.setColor(Color.YELLOW);
                            selectedItem = null;
                        }
                    }
                }
            } else if (creatingShape) {
                shapeCreator.lastAction(entities);
                shapeCreator = null;
                creatingShape = false;
            }
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void Unselect() {
        selectedItem = null;
    }

    public void deleteSelected() {
        if (!(selectedItem instanceof Tool || selectedItem instanceof EditorButton) && selectedItem != null) {
            entities.remove(selectedItem);
        }
    }

}
