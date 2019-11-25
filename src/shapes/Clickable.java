/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import java.util.List;

/**
 *
 * @author christian
 */
public interface Clickable {

    boolean wasClicked(Point point);

    static Clickable checkClickableList(List<? extends Clickable> orderedClickableList, Point pointClicked) {
        for (Clickable clickable : orderedClickableList) {
            if (clickable.wasClicked(pointClicked)) {
                return clickable;
            }
        }
        return null;
    }

    static boolean insideSquareArea(Point clickPoint, Point position, int width, int height) {
        int minX = position.getX() - (width / 2);
        if (clickPoint.getX() >= minX) {
            int maxX = position.getX() + (width / 2);
            if (clickPoint.getX() <= maxX) {
                int minY = position.getY() - (height / 2);
                if (clickPoint.getY() >= minY) {
                    int maxY = position.getY() + (height / 2);
                    if (clickPoint.getY() <= maxY) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
