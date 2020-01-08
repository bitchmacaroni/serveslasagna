/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images.animations;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author christian
 */
public class GameAnimations {

    public static List<GameAnimation> animations = new ArrayList<>();

    public static void LoadAllAnimations() throws IOException {
        Path dir = Paths.get("");
        System.out.println("getting path");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path file : stream) {
                if (file.getFileName().toString().contains("AnimationsManifest")) {
                    Scanner reader = new Scanner(new BufferedReader(new FileReader(file.getFileName().toString())));
                    readingAnimationFile:
                    while (reader.hasNext()) {
                        String input = reader.next();
                        if (!input.isBlank()) {
                            String animationSetName = input;
                            List<Integer> timeFrame = new ArrayList<>();
                            List<Integer> imageFrame = new ArrayList<>();
                            int sprites = -1;
                            int xBorder = -1;
                            int yBorder = -1;
                            int rows = -1;
                            int columns = -1;
                            int xOffset = 0;
                            int yOffset = 0;
                            boolean skipReading = false;
                            boolean gifAnimation = false;
                            readingAnimationSegment:
                            while (reader.hasNext() || skipReading) {
                                if (!skipReading) {
                                    input = reader.next();
                                } else {
                                    skipReading = false;
                                }
                                switchOptions:
                                switch (input.toLowerCase()) {
                                    case "gifanimation":
                                        //System.out.println("is gif!");
                                        gifAnimation = true;
                                    case "timeframe":
                                        while (reader.hasNext()) {
                                            String nextNumberInput = reader.next();
                                            try {
                                                timeFrame.add(Integer.parseInt(nextNumberInput));
                                            } catch (NumberFormatException e) {
                                                input = nextNumberInput;
                                                skipReading = true;
                                                break switchOptions;
                                            }
                                        }
                                        System.out.println("Animation " + animationSetName + " not ended properly.");
                                        return;
                                    case "imageframe":
                                        while (reader.hasNext()) {
                                            String nextNumberInput = reader.next();
                                            try {
                                                imageFrame.add(Integer.parseInt(nextNumberInput));
                                            } catch (NumberFormatException e) {
                                                input = nextNumberInput;
                                                skipReading = true;
                                                break switchOptions;
                                            }
                                        }
                                        System.out.println("Animation " + animationSetName + " not ended properly.");
                                        return;
                                    case "properties":
                                        while (reader.hasNext() || skipReading) {
                                            if (!skipReading) {
                                                input = reader.next();
                                            } else {
                                                skipReading = false;
                                            }
                                            switch (input.toLowerCase()) {
                                                case "end":
                                                    break readingAnimationSegment;
                                                case "xoffset":
                                                    if (reader.hasNext()) {
                                                        input = reader.next();
                                                        try {
                                                            xOffset = Integer.parseInt(input);
                                                        } catch (NumberFormatException e) {
                                                            System.out.println("Could not parse \"" + input + "\" to int, in xOffset.");
                                                            skipReading = true;
                                                        }
                                                    } else {
                                                        System.out.println("Animation " + animationSetName + " not ended properly.");
                                                        return;
                                                    }
                                                    break;
                                                case "yoffset":
                                                    if (reader.hasNext()) {
                                                        input = reader.next();
                                                        try {
                                                            yOffset = Integer.parseInt(input);
                                                        } catch (NumberFormatException e) {
                                                            System.out.println("Could not parse \"" + input + "\" to int, in yOffset.");
                                                            skipReading = true;
                                                        }
                                                    } else {
                                                        System.out.println("Animation " + animationSetName + " not ended properly.");
                                                        return;
                                                    }
                                                    break;
                                                case "xborder":
                                                    if (reader.hasNext()) {
                                                        input = reader.next();
                                                        try {
                                                            xBorder = Integer.parseInt(input);
                                                        } catch (NumberFormatException e) {
                                                            System.out.println("Could not parse \"" + input + "\" to int, in xBorder.");
                                                            skipReading = true;
                                                        }
                                                    } else {
                                                        System.out.println("Animation " + animationSetName + " not ended properly.");
                                                        return;
                                                    }
                                                    break;
                                                case "yborder":
                                                    if (reader.hasNext()) {
                                                        input = reader.next();
                                                        try {
                                                            yBorder = Integer.parseInt(input);
                                                        } catch (NumberFormatException e) {
                                                            System.out.println("Could not parse \"" + input + "\" to int, in yBorder.");
                                                            skipReading = true;
                                                        }
                                                    } else {
                                                        System.out.println("Animation " + animationSetName + " not ended properly.");
                                                        return;
                                                    }
                                                    break;
                                                case "sprites":
                                                    if (reader.hasNext()) {
                                                        input = reader.next();
                                                        try {
                                                            sprites = Integer.parseInt(input);
                                                        } catch (NumberFormatException e) {
                                                            System.out.println("Could not parse \"" + input + "\" to int, in sprites.");
                                                            skipReading = true;
                                                        }
                                                    } else {
                                                        System.out.println("Animation " + animationSetName + " not ended properly.");
                                                        return;
                                                    }
                                                    break;
                                                case "rows":
                                                    if (reader.hasNext()) {
                                                        input = reader.next();
                                                        try {
                                                            rows = Integer.parseInt(input);
                                                        } catch (NumberFormatException e) {
                                                            System.out.println("Could not parse \"" + input + "\" to int, in rows.");
                                                            skipReading = true;
                                                        }
                                                    } else {
                                                        System.out.println("Animation " + animationSetName + " not ended properly.");
                                                        return;
                                                    }
                                                    break;
                                                case "columns":
                                                    if (reader.hasNext()) {
                                                        input = reader.next();
                                                        try {
                                                            columns = Integer.parseInt(input);
                                                        } catch (NumberFormatException e) {
                                                            System.out.println("Could not parse \"" + input + "\" to int, in columns.");
                                                            skipReading = true;
                                                        }
                                                    } else {
                                                        System.out.println("Animation " + animationSetName + " not ended properly.");
                                                        return;
                                                    }
                                                    break;
                                            }
                                        }
                                        System.out.println("Animation " + animationSetName + " not ended properly.");
                                        return;
                                    case "end":
                                        break readingAnimationSegment;
                                }
                            }
                            if (!input.toLowerCase().equals("end")) {
                                System.out.println("Animation " + animationSetName + " not ended properly.");
                                return;
                            }
                            if (timeFrame.size() == imageFrame.size() || imageFrame.isEmpty() || timeFrame.isEmpty()) {
                                int[][] lapTimes = new int[2][];
                                int[][] offsetMatrix = null;
                                BufferedImage[] images;
                                if (gifAnimation) {
                                    AnimationMaker aMaker = new AnimationMaker();
                                    images = aMaker.readGifAnimation(animationSetName);
                                    offsetMatrix = aMaker.getOffsetsMatrix();
                                    
                                } else {
                                    images = new AnimationMaker()
                                            .withName(animationSetName)
                                            .withBorders(xBorder, yBorder)
                                            .withSprites(sprites)
                                            .withRowColumns(rows, columns)
                                            .makeAnimation();
                                }

                                if (imageFrame.isEmpty() || timeFrame.isEmpty()) {
                                    timeFrame = new ArrayList<>();
                                    for (int i = 0; i < images.length; i++) {
                                        timeFrame.add(3);
                                    }
                                    imageFrame = new ArrayList<>();
                                    for (int i = 0; i < images.length; i++) {
                                        imageFrame.add(i);
                                    }
                                }
                                lapTimes[0] = Arrays.stream(timeFrame.toArray(new Integer[timeFrame.size()]))
                                        .mapToInt(Integer::new)
                                        .toArray();
                                lapTimes[1] = Arrays.stream(imageFrame.toArray(new Integer[imageFrame.size()]))
                                        .mapToInt(Integer::new)
                                        .toArray();
                                Animation animation = new Animation(images, lapTimes, offsetMatrix);
                                animation.setxOffset(xOffset);
                                animation.setyOffset(yOffset);
                                animations.add(new GameAnimation(animation, animationSetName));
                            } else {
                                System.out.println("Time frame and image frame invalid. Make sure they have equal number of elements.");
                            }
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

    public static Image getAnimationImage(String name) {
        for (GameAnimation animation : animations) {
            if (animation.getName().equals(name)) {
                return animation.getAnimation().getFirstImage();
            }
        }
        return null;
    }
    
    public static Image getAnimationImage(String name, int i) {
        for (GameAnimation animation : animations) {
            if (animation.getName().equals(name)) {
                return animation.getAnimation().getSpecificImage(i);
            }
        }
        return null;
    }

    public static ArrayList<String> getAnimationNames() {
        ArrayList<String> names = new ArrayList<>();
        for (GameAnimation animation : animations) {
            names.add(animation.getName());
        }
        return names;
    }

    public static String getAnimationName(Animation animation) {
        for (GameAnimation gameAnimation : animations) {
            if (Arrays.equals(gameAnimation.getAnimation().getImages(), animation.getImages())) {
                return gameAnimation.getName();
            }
        }
        return null;
    }

}
