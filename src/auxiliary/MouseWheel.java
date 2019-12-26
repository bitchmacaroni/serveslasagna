/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliary;

import editor.tool.Action;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.function.IntConsumer;

/**
 *
 * @author christian
 */
public class MouseWheel implements MouseWheelListener{

    IntConsumer action;
    
    public MouseWheel(IntConsumer action) {
        this.action = action;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent arg0) {
        action.accept(arg0.getWheelRotation());
    }
    
}
