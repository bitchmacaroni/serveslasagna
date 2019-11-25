/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliary;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author jorge matte
 */
public class GameMouseEvent implements MouseListener{
    
    private boolean pressing;

    public GameMouseEvent() {
        pressing = false;
    }

    public boolean isPressing() {
        return pressing;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        pressing = true;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        pressing = false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}
