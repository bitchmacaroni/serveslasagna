/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.controller.entityClickers;

/**
 *
 * @author christian
 */
public interface ClickableCanvas {
    boolean isMouseOutsideCanvas();
    int getMouseX();
    int getMouseY();
}
