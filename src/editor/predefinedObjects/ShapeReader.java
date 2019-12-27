/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.predefinedObjects;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import shapes.GameShape;
import shapes.RectangleShape;

/**
 *
 * @author christian
 */
public class ShapeReader {

    public GameShape readObject(Scanner reader) throws IOException {
        GameShape shape = null;
        if (!reader.hasNext()) {
            System.out.println("Empty object, ending reading");
            return null;
        }
        String shapeType = reader.next();
        switch (shapeType) {
            case "RectangleShape":
                shape = new RectangleShape(Color.yellow, 0, 0, 0, 0);
                break;
            default:
                System.out.println("Unknown shape type, skipping to next object");
                return null;
        }

        objectReading:
        while (reader.hasNext()) {
            String input = reader.next();
            switch (input.toLowerCase()) {
                case "end":
                    break objectReading;
                case "width":
                    if (!reader.hasNext()) {
                        System.out.println("width attribute not defined, skipping to next object");
                        return null;
                    }
                    String width = reader.next();
                    try {
                        shape.setWidth(Integer.parseInt(width));
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing \"" + width + "\" to int");
                    }
                    break;
                case "height":
                    if (!reader.hasNext()) {
                        System.out.println("width attribute not defined, skipping to next object");
                        return null;
                    }
                    String height = reader.next();
                    try {
                        shape.setHeight(Integer.parseInt(height));
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing \"" + height + "\" to int");
                    }
                    break;
                case "rotation":
                    if (!reader.hasNext()) {
                        System.out.println("rotation attribute not defined, skipping to next object");
                        return null;
                    }
                    String rotation = reader.next();
                    try {
                        shape.setRotation(Float.parseFloat(rotation));
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing \"" + rotation + "\" to float");
                    }
                case "paralax":
                    if (!reader.hasNext()) {
                        System.out.println("paralax attribute not defined, skipping to next object");
                        return null;
                    }
                    String paralax = reader.next();
                    try {
                        shape.setParalax(Float.parseFloat(paralax));
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing \"" + paralax + "\" to float");
                    }
                case "scale":
                case "image":
                case "body":
                case "movingBody":
            }
        }
        if (shape.getWidth() == 0 || shape.getHeight() == 0) {
            if (shape.getObjectImage() != null) {
                shape.setWidth(shape.getImageProperties().getWidth());
                shape.setHeight(shape.getImageProperties().getHeight());
            }
            else
            {
                System.out.println("Object has no image or fisical dimentions, skipping to next object");
                return null;
            }
        }
        return shape;
    }

    public void readObjectsFile() {
        Path dir = Paths.get("");
        try ( DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path file : stream) {
                if (file.getFileName().toString().contains("AnimationsManifest")) {
                    Scanner reader = new Scanner(new BufferedReader(new FileReader(file.getFileName().toString())));
                    while (reader.hasNext()) {
                        String intput = reader.next();
                        if (intput.toLowerCase().equals("object:")) {
                            GameShape shape = readObject(reader);
                            if (shape != null) {
                                //add to common object list
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {

        }
    }

}
