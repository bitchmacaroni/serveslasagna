/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.controller.entityClickers;

import auxiliary.GameMouseEvent;
import editor.EntityPropertyScreen;
import editor.controller.ShapeCreator;
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
public abstract class EntityClicker {

    protected GameShape selectedItem;
    protected GameMouseEvent mouseEvents;
    protected GameMouseEvent alternateMouseEvents;
    protected List<GameShape> entities;
    protected ShapeCreator shapeCreator;
    protected boolean triggered;
    protected boolean creatingShape;
    protected boolean enabled;
    protected boolean doubleClickingEnabled = true;
    protected long doubleClickingTime = 200_000_000L;
    protected long lastClick = -6_000_000_000L;   // Distribution: sec_miliSec_microSec_nanoSec L
    protected long clickingStart = -6_000_000_000L;
    protected long draggingStartTime = 200_000_000L;
    protected ClickableCanvas frameReference;

    protected GameShape clickedShape;
    protected Point clickedPoint;
    //deleteme
    protected int nClick = 0;

    public EntityClicker(GameMouseEvent mouseEvents, List<GameShape> entities, ClickableCanvas frameReference) {
        this.mouseEvents = mouseEvents;
        this.entities = entities;
        this.frameReference = frameReference;
        creatingShape = false;
        triggered = false;
        enabled = true;
    }

    public void routineEditorCheck() {
//        First click
        if (enabled) {
            if (frameReference.isMouseOutsideCanvas()) {
                outOfBounds();
            }
            try {
                clickedPoint = new Point(frameReference.getMouseX(), frameReference.getMouseY());
            } catch (NullPointerException e) {
            }
            if (mouseEvents.isPressing()) {
                if (!triggered) {
                    clickingStart = System.nanoTime();
                }
                triggered = true;
                long time = System.nanoTime();
                //System.out.println("draggable? "+(selectedItem instanceof Draggable));
                if (selectedItem instanceof Draggable
                        && time - clickingStart > draggingStartTime) {
                    dragged();
                } else {
                    clicking();
                }
            } else if (triggered) {
                long time = System.nanoTime();
                if (doubleClickingEnabled && time - lastClick < doubleClickingTime) {
                    triggered = false;
                    doubleClicked();
                } else {
                    lastClick = System.nanoTime();
                    triggered = false;
                    clicked();
                }
            }
        }
    }

    protected abstract void clicking();

    protected abstract void clicked();

    protected abstract void doubleClicked();

    protected abstract void dragged();

    protected abstract void outOfBounds();

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

    protected void setDraggingOn() {
        if (draggingStartTime > 0 && selectedItem != null) {
            this.clickingStart = System.nanoTime() - draggingStartTime;
        }
    }

    protected void addEntity(GameShape entity) {
        entities.add(entity);
    }

}
