/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map.io;

import editor.tool.EditorButton;
import editor.tool.Tool;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import map.Map;
import shapes.GameShape;

/**
 *
 * @author christian
 */
public class EntityListIO {

    List<GameShape> entities;
    String fileName;

    public EntityListIO(List<GameShape> entities, String fileName) {
        this.entities = entities;
    }

    public final boolean writeProperties() {
        BufferedWriter bw = null;
        try {
            File file = new File("myfile.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            for (GameShape entity : entities) {
                if (!(entity instanceof Tool || entity instanceof EditorButton)) {
                    new EntityIO<>(entity.getClass().getSimpleName(), entity).writeEntity(bw);
                }
            }
            bw.close();
        } catch (IOException | IllegalArgumentException | IllegalAccessException ex) {
            System.out.println("Error al guardar mapa: " + ex.getMessage());
            return false;
        }
        return true;
    }
}
