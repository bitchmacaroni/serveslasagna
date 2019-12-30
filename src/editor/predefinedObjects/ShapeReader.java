/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.predefinedObjects;

import bodies.Body;
import bodies.MovingBody;
import images.GameImages;
import images.animations.Animation;
import images.animations.GameAnimations;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import shapes.GameShape;
import shapes.RectangleShape;

/**
 *
 * @author christian
 */
public class ShapeReader {

    private static GameShape readObject(Scanner reader) throws IOException {
        GameShape shape;
        String shapeType = "";
        while (shapeType.isBlank()) {
            if (!reader.hasNext()) {
                System.out.println("Empty object, ending reading");
                return null;
            }
            shapeType = reader.next();
        }
        
        switch (shapeType.toLowerCase()) {
            case "rectangleshape":
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
                        System.out.println("Error parsing width \"" + width + "\" to int");
                    }
                    break;
                case "height":
                    if (!reader.hasNext()) {
                        System.out.println("height attribute not defined, skipping to next object");
                        return null;
                    }
                    String height = reader.next();
                    try {
                        shape.setHeight(Integer.parseInt(height));
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing height \"" + height + "\" to int");
                    }
                    break;
                case "imagewidth":
                    if (!reader.hasNext()) {
                        System.out.println("imageWidth attribute not defined, skipping to next object");
                        return null;
                    }
                    String imageWidth = reader.next();
                    try {
                        shape.getImageProperties().setWidth(Integer.parseInt(imageWidth));
                        System.out.println("Property width: "+shape.getImageProperties().getWidth());
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing imageWidth \"" + imageWidth + "\" to int");
                    }
                    break;
                case "imageheight":
                    if (!reader.hasNext()) {
                        System.out.println("imageHeight attribute not defined, skipping to next object");
                        return null;
                    }
                    String imageHeight = reader.next();
                    try {
                        shape.getImageProperties().setHeight(Integer.parseInt(imageHeight));
                        System.out.println("Property height: "+shape.getImageProperties().getHeight());
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing imageHeight \"" + imageHeight + "\" to int");
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
                        System.out.println("Error parsing rotation \"" + rotation + "\" to float");
                    }
                    break;
                case "paralax":
                    if (!reader.hasNext()) {
                        System.out.println("paralax attribute not defined, skipping to next object");
                        return null;
                    }
                    String paralax = reader.next();
                    try {
                        shape.setParalax(Float.parseFloat(paralax));
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing paralax \"" + paralax + "\" to float");
                    }
                    break;
                case "scale":
                    if (!reader.hasNext()) {
                        System.out.println("scale attribute not defined, skipping to next object");
                        return null;
                    }
                    String scale = reader.next();
                    try {
                        shape.setScale(Float.parseFloat(scale));
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing scale \"" + scale + "\" to float");
                    }
                    break;
                case "image":
                    if (!reader.hasNext()) {
                        System.out.println("image attribute not defined, skipping to next object");
                        return null;
                    }
                    String imageName = reader.next();
                    Image image;
                    if (imageName.trim().startsWith("animation:") && !imageName.trim().equals("animation:")) {
                        image = GameAnimations.getAnimationImage(imageName.split(":")[1]);
                    } else {
                        image = GameImages.getImage(imageName);
                    }
                    if (image == null) {
                        System.out.println("Warning, image name \"" + imageName + "\" does not exist. Setting image to null.");
                    } else {
                        shape.setObjectImage(image);
                        //shape.setObjectImage(GameImages.getImage("guy.png"));
                    }
                    break;
                case "body:":
                    Body body = new Body(shape);
                    shape.setBody(body);
                    defineBody(body, reader);
                    break;
                case "movingbody:":
                    MovingBody mBody = new MovingBody(shape);
                    shape.setBody(mBody);
                    defineBody(mBody, reader);
                    break;
            }
        }
        if (shape.getWidth() == 0 || shape.getHeight() == 0) {
            if (shape.getObjectImage() != null) {
                shape.setWidth(shape.getImageProperties().getWidth());
                shape.setHeight(shape.getImageProperties().getHeight());
            } else {
                System.out.println("Object has no image or fisical dimentions, skipping to next object");
                return null;
            }
        }
        return shape;
    }

    private static Body defineBody(Body body, Scanner reader) {
        if (body == null || reader == null) {
            return null;
        }
        System.out.println("has a body");
        bodySection:
        while (reader.hasNext()) {
            String bodyInput = reader.next();
            switch (bodyInput.toLowerCase()) {
                case "solidity":
                    soliditySection:
                    while (reader.hasNext()) {
                        String solidityInput = reader.next();
                        switch (solidityInput.toLowerCase()) {
                            case "null":
                            case "false":
                                body.setSolid(false);
                                break;
                            case "elasticity":
                                body.setSolid(true);
                                if (!reader.hasNext()) {
                                    System.out.println("elasticity attribute not defined, skipping body");
                                    return null;
                                }
                                String elasticity = reader.next();
                                try {
                                    body.getSolidity().setElasticity(Float.parseFloat(elasticity));
                                } catch (NumberFormatException e) {
                                    System.out.println("Error parsing elasticity \"" + elasticity + "\" to float");
                                }
                                break;
                            case "mass":
                                body.setSolid(true);
                                if (!reader.hasNext()) {
                                    System.out.println("mass attribute not defined, skipping body");
                                    return null;
                                }
                                String mass = reader.next();
                                try {
                                    body.getSolidity().setMass(Float.parseFloat(mass));
                                } catch (NumberFormatException e) {
                                    System.out.println("Error parsing mass \"" + mass + "\" to float");
                                }
                                break;
                            case "roughness":
                                body.setSolid(true);
                                if (!reader.hasNext()) {
                                    System.out.println("roughness attribute not defined, skipping body");
                                    return null;
                                }
                                String roughness = reader.next();
                                try {
                                    body.getSolidity().setRoughness(Float.parseFloat(roughness));
                                } catch (NumberFormatException e) {
                                    System.out.println("Error parsing roughness \"" + roughness + "\" to float");
                                }
                                break;
                            default:
                                break soliditySection;
                        }
                    }
                    break;
                case "standinganimation":
                    if (!reader.hasNext()) {
                        System.out.println("standingAnimation attribute not defined, skipping body");
                        return null;
                    }
                    String StandingAnimationName = reader.next();
                    Animation standingAnimation = GameAnimations.getAnimation(StandingAnimationName);
                    if (standingAnimation == null) {
                        System.out.println("Warning, animation name \"" + StandingAnimationName + "\" does not exist. Setting standing animation to null.");
                    } else {
                        body.setStandingAnimation(standingAnimation);
                    }
                    break;
                case "gravityon":
                    if (body instanceof MovingBody) {
                        ((MovingBody) body).setAfectedByGravity(true);
                    }
                    break;
                case "movinganimation":
                    if (body instanceof MovingBody) {
                        if (!reader.hasNext()) {
                            System.out.println("movingAnimation attribute not defined, skipping body");
                            return null;
                        }
                        String movingAnimationName = reader.next();
                        Animation movingAnimation = GameAnimations.getAnimation(movingAnimationName);
                        if (movingAnimation == null) {
                            System.out.println("Warning, animation name \"" + movingAnimationName + "\" does not exist. Setting moving animation to null.");
                        } else {
                            body.setStandingAnimation(movingAnimation);
                        }
                    }
                    break;
                case "fallinganimation":
                    if (body instanceof MovingBody) {
                        if (!reader.hasNext()) {
                            System.out.println("fallingAnimation attribute not defined, skipping body");
                            return null;
                        }
                        String fallingAnimationName = reader.next();
                        Animation fallingAnimation = GameAnimations.getAnimation(fallingAnimationName);
                        if (fallingAnimation == null) {
                            System.out.println("Warning, animation name \"" + fallingAnimationName + "\" does not exist. Setting falling animation to null.");
                        } else {
                            body.setStandingAnimation(fallingAnimation);
                        }
                    }
                    break;
                case "endbody":
                    break bodySection;
            }
        }
        return body;
    }

    public static List<GameShape> readObjectsFile() {
        List<GameShape> prefabObjects = new ArrayList<>();
        Path dir = Paths.get("");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path file : stream) {
                if (file.getFileName().toString().contains("PredefinedObjects")) {
                    Scanner reader = new Scanner(new BufferedReader(new FileReader(file.getFileName().toString())));
                    while (reader.hasNext()) {
                        String intput = reader.next();
                        if (intput.toLowerCase().equals("object:")) {
                            GameShape shape = readObject(reader);
                            if (shape != null) {
                                prefabObjects.add(shape);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {

        }
        return prefabObjects;
    }

}
