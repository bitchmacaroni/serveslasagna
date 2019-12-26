/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.controller.entityClickers;

import auxiliary.GameMouseEvent;
import camera.Camera;
import editor.EntityPropertyScreen;
import editor.controller.ShapeCreator;
import editor.tool.EditorButton;
import editor.tool.Tool;
import java.awt.Color;
import java.util.List;
import shapes.Clickable;
import shapes.GameShape;
import sidescrollerproto.SideScrollerProto;

/**
 *
 * @author christian
 */
public class EditorEntityClicker extends EntityClicker {
    
    

    public EditorEntityClicker(GameMouseEvent mouseEvents, List<GameShape> entities, SideScrollerProto frameReference) {
        super(mouseEvents, entities, frameReference);
    }

    @Override
    protected void clicking() {
        {
            if (creatingShape) {
                shapeCreator.handlesClicking(clickedPoint);
            } else {
                if (selectedItem instanceof Tool) {
                    GameShape shape = (GameShape) Clickable.checkClickableList(entities, clickedPoint);
                    if (!(shape instanceof Tool) && (shapeCreator == null)) {
                        shapeCreator = new ShapeCreator((Tool) selectedItem, entities, clickedPoint);
                        creatingShape = true;
                        triggered = true;
                    }
                }
            }
        }
    }

    @Override
    protected void clicked() {
        if (creatingShape) {
            shapeCreator.lastAction(entities);
            shapeCreator = null;
            creatingShape = false;
        } else {
            clickedShape = (GameShape) Clickable.checkClickableList(entities, clickedPoint);
            if (clickedShape != null) {
                if (!clickedShape.equals(selectedItem)) {
                    if (clickedShape instanceof EditorButton) {
                        ((EditorButton) clickedShape).doIt();
                    } else {
                        if (selectedItem != null) {
                            selectedItem.setColor(Color.YELLOW);
                        }
                        selectedItem = clickedShape;
                        selectedItem.setColor(Color.GREEN);
                    }
                } else {
                    selectedItem.setColor(Color.YELLOW);
                    selectedItem = null;
                }
            }
        }
        
        if(alternateMouseEvents != null)
        {
            mouseEvents = alternateMouseEvents;
            alternateMouseEvents = null;
        }
    }

    @Override
    protected void doubleClicked() {
        clickedShape = (GameShape) Clickable.checkClickableList(entities, clickedPoint);
        if (clickedShape != null && !(clickedShape instanceof Tool || clickedShape instanceof EditorButton)) {
            triggered = false;
            enabled = false;
            clickedShape.setColor(Color.GREEN);
            new EntityPropertyScreen(clickedShape, this,((SideScrollerProto)frameReference).getGameBodies(), (SideScrollerProto)frameReference).setVisible(true);
        } else {
            clicked();
        }

    }

    @Override
    protected void dragged() {
        selectedItem.center(Camera.getPointReferenceFromScreen(clickedPoint));
    }

    @Override
    protected void outOfBounds() {
        
    }

}
