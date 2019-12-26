/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.predefinedObjects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import shapes.GameShape;

/**
 *
 * @author christian
 */
public class ShapeReader {
    
    
    
    
    public GameShape readObject() throws IOException
    {
        GameShape shape = null;
        Path dir = Paths.get("");
        try ( DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path file : stream) {
                if (file.getFileName().toString().contains("AnimationsManifest")) {
                    Scanner reader = new Scanner(new BufferedReader(new FileReader(file.getFileName().toString())));
                    while (reader.hasNext()) {
                        
                    }
                }
            }
        }
        
        return shape;
    }
}
