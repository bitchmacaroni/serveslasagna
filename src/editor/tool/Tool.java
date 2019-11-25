/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.tool;
import java.awt.Color;
import shapes.GameShape;

/**
 *
 * @author christian
 */
public abstract class Tool extends GameShape{
    protected boolean selected;

    public Tool(Color color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
        selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
}
