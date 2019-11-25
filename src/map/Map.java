/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import map.io.EntityListIO;
import shapes.GameShape;

/**
 * El mapa representa una indexacion de las posiciones de los objetos del juego
 * Su representacion usa un arreglo 2D de char
 *
 * @author Keyk
 */
public class Map {

    private int mapWidth;
    private int mapHeight;

    public Map(int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;

    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public boolean saveMap(String fileName, List<GameShape> entities) {
        new EntityListIO(entities, "asdf.txt").writeProperties();
        return true;
    }

    /*
    public boolean loadMap(String fileName, ArrayList<Decoration> decorations, LinkedList<GameObject> gameObjects) {
        Scanner reader = null;
        boolean returnValue = true;

        try {
            reader = new Scanner(new BufferedReader(new FileReader(fileName)));

            while (reader.hasNext()) {
                String input;
                input = reader.next();
                if (input.equals("<decoration>")) {
                    input = reader.next();
                    while (!input.equals("</decoration>") && reader.hasNext()) {
                        int x = Integer.parseInt(reader.next());
                        int y = Integer.parseInt(reader.next());
                        float scale = Float.parseFloat(reader.next());
                        float paralaxLevel = Float.parseFloat(reader.next());
                        Decoration decoration = new Decoration(x, y, GameImages.getImage(input));
                        decoration.setScale(scale);
                        decoration.setParalax(paralaxLevel);
                        decorations.add(decoration);

                        input = reader.next();
                    }
                } else if (input.equals("<ship>")) {
                    input = reader.next();
                    while (!input.equals("</ship>") && reader.hasNext()) {
                        int x = Integer.parseInt(reader.next());
                        int y = Integer.parseInt(reader.next());
                        int team = Integer.parseInt(reader.next());
                        gameObjects.add(ObjectCreator.createShip(input, x, y, team));

                        input = reader.next();
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            returnValue = false;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return returnValue;
    }
    //*/
}
