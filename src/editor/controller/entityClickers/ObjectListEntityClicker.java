/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.controller.entityClickers;

import auxiliary.GameMouseEvent;
import camera.Camera;
import editor.ObjectListFrame;
import java.util.List;
import shapes.Clickable;
import shapes.GameShape;
import shapes.Point;
import sidescrollerproto.SideScrollerProto;

/**
 *
 * @author christian
 */
public class ObjectListEntityClicker extends EntityClicker {

    private EntityClicker handOverEntityClicker;
    private GameShape draggingObject;
    private boolean initialClick = true;
    private boolean initialDragged = true;

    public ObjectListEntityClicker(
            GameMouseEvent mouseEvents,
            List<GameShape> entities,
            ClickableCanvas frameReference,
            EntityClicker handOverEntityClicker) {
        super(mouseEvents, entities, frameReference);
        this.handOverEntityClicker = handOverEntityClicker;
    }

    @Override
    protected void clicking() {

        if (initialClick) {
            //System.out.println("clicking");
            //System.out.println(this.clickedPoint.getX() + " , " + this.clickedPoint.getY());
            //System.out.println("initial locks");
            initialClick = false;
            int i = 0;
            for (GameShape entity : entities) {
                i++;
                if (Clickable.insideSquareArea(clickedPoint, entity.getPosition(), entity.getWidth(), entity.getHeight())) {
                    selectedItem = entity;
                    break;
                }
            }
        }
    }

    @Override
    protected void clicked() {
        entities.remove(draggingObject);
        initialDragged = true;
        initialClick = true;
        draggingObject = null;
    }

    @Override
    protected void doubleClicked() {
        clicked();
    }

    @Override
    protected void dragged() {
        if (initialDragged) {
            initialDragged = false;
            draggingObject = selectedItem.copy();
            entities.add(draggingObject);
        }
        try {
            draggingObject.center(clickedPoint);
        } catch (NullPointerException e) {
        }
    }

    @Override
    protected void outOfBounds() {
        if (draggingObject != null) {
            handOverEntityClicker.selectedItem = draggingObject;
            handOverEntityClicker.setDraggingOn();
            handOverEntityClicker.addEntity(draggingObject);
            handOverEntityClicker.alternateMouseEvents = handOverEntityClicker.mouseEvents;
            handOverEntityClicker.mouseEvents = this.mouseEvents;
            handOverEntityClicker.enabled = true;
            handOverEntityClicker.triggered = true;
            entities.remove(draggingObject);
            draggingObject = null;
            //((ObjectListFrame) frameReference).dispose();
        }
    }

}
