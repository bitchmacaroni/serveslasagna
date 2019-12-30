/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.predefinedObjects;

import java.util.List;
import shapes.GameShape;

/**
 *
 * @author christian
 */
public class GamePredefinedObjects {
    private static List<GameShape> prefabObjects;
    
    public static void loadPredefinedObjects()
    {
        prefabObjects = ShapeReader.readObjectsFile();
        for (GameShape prefabObject : prefabObjects) {
            System.out.println("w: "+prefabObject.getWidth());
            System.out.println("h: "+prefabObject.getHeight());
            System.out.println("iw: "+prefabObject.getImageProperties().getWidth());
            System.out.println("ih: "+prefabObject.getImageProperties().getHeight());
        }
    }
    
    public static List<GameShape> getPredefinedObjects()
    {
        return prefabObjects;
    }
    
}
