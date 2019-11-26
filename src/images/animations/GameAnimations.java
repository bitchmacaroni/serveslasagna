/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images.animations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author christian
 */
public class GameAnimations {

    public static List<GameAnimation> animations = new ArrayList<>();

    public static void LoadAllAnimations() throws IOException {
        Path dir = Paths.get("");
        System.out.println("getting path");
        try ( DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path file : stream) {
                if (file.getFileName().toString().contains("AnimationsManifest")) {
                    Scanner reader = new Scanner(new BufferedReader(new FileReader(file.getFileName().toString())));
                    while (reader.hasNext()) {
                        try {
                            String input = reader.next();
                            String animationSetName = input;
                            List<Integer> timeFrame = new ArrayList<>();
                            List<Integer> imageFrame = new ArrayList<>();
                            int sprites = -1;
                            int xBorder = -1;
                            int yBorder = -1;
                            int rows = -1;
                            int columns = -1;
                            if (reader.hasNext() && reader.next().equals("TimeFrame")) {
                                while (reader.hasNext()) {
                                    input = reader.next();
                                    if (input.equals("ImageFrame")) {
                                        break;
                                    }
                                    if (reader.hasNext()) {
                                        timeFrame.add(Integer.parseInt(input));
                                    }
                                }
                                while (reader.hasNext()) {
                                    input = reader.next();
                                    if (input.equals("Properties") || input.equals("End")) {
                                        break;
                                    }
                                    if (reader.hasNext()) {
                                        imageFrame.add(Integer.parseInt(input));
                                    }
                                }
                                if (input.equals("Properties")) {
                                    while (reader.hasNext()) {
                                        input = reader.next();
                                        if (input.equals("End")) {
                                            break;
                                        }
                                        if (reader.hasNext() && input.equals("xBorder")) {
                                            input = reader.next();
                                            xBorder = Integer.parseInt(input);
                                        }
                                        if (reader.hasNext() && input.equals("yBorder")) {
                                            input = reader.next();
                                            yBorder = Integer.parseInt(input);
                                        }
                                        if (reader.hasNext() && input.equals("Sprites")) {
                                            input = reader.next();
                                            sprites = Integer.parseInt(input);
                                        }
                                        if (reader.hasNext() && input.equals("Rows")) {
                                            input = reader.next();
                                            rows = Integer.parseInt(input);
                                        }
                                        if (reader.hasNext() && input.equals("Columns")) {
                                            input = reader.next();
                                            columns = Integer.parseInt(input);
                                        }
                                    }
                                }
                                if (timeFrame.size() == imageFrame.size()) {
                                    int[][] lapTimes = new int[2][];
                                    lapTimes[0] = Arrays.stream(timeFrame.toArray(new Integer[timeFrame.size()]))
                                            .mapToInt(Integer::new)
                                            .toArray();
                                    lapTimes[1] = Arrays.stream(imageFrame.toArray(new Integer[imageFrame.size()]))
                                            .mapToInt(Integer::new)
                                            .toArray();
                                    Animation animation = new Animation(
                                            new AnimationMaker()
                                                    .withName(animationSetName)
                                                    .withBorders(xBorder, yBorder)
                                                    .withSprites(sprites)
                                                    .withRowColumns(rows, columns)
                                                    .makeAnimation(),
                                            lapTimes);

                                    animations.add(new GameAnimation(animation, animationSetName));
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Parse error while trying to read Animation Manifest");
                        }
                    }
                }
            }
        }
    }

    public static Animation getAnimation(String name) {
        for (GameAnimation animation : animations) {
            if (animation.getName().equals(name)) {
                return animation.getClone();
            }
        }
        return null;
    }

    public static ArrayList<String> getImagesNames() {
        ArrayList<String> names = new ArrayList<>();
        for (GameAnimation animation : animations) {
            names.add(animation.getName());
        }
        return names;
    }

    public static String getImageName(Animation animation) {
        for (GameAnimation gameAnimation : animations) {
            if (Arrays.equals(gameAnimation.getAnimation().getImages(), animation.getImages())) {
                return gameAnimation.getName();
            }
        }
        return null;
    }

}
