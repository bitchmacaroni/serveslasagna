/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodies;

import java.util.List;
import shapes.GameShape;
import shapes.Point;
import shapes.PointShape;
import shapes.RectangleShape;
import shapes.TriangleShape;

/**
 *
 * @author christian
 */
public interface Movable {

    void move(List<Body> knownBodies);

    public static boolean checkMoveContact(GameShape movingShape, GameShape contactShape, MovingBody movingBody) {

        //System.out.println("\n\nstartin");
        //check general minimum condition
        int oldMaxX = movingShape.getMaxX();
        int newMaxX = oldMaxX + (int) movingBody.getSpeedX();
        int conMinX = contactShape.getMinX();
        //System.out.printf("oldMaxX = %d, newMaxX  = %d, conMinX  = %d ",oldMaxX,newMaxX,conMinX);
        if (oldMaxX >= conMinX || newMaxX >= conMinX) {
            int oldMinX = movingShape.getMinX();
            int newMinX = oldMinX + (int) movingBody.getSpeedX();
            int conMaxX = contactShape.getMaxX();
            //System.out.printf("oldMinX = %d, newMinX  = %d, conMaxX  = %d ",oldMinX,newMinX,conMaxX);
            if (oldMinX <= conMaxX || newMinX <= conMaxX) {
                int oldMaxY = movingShape.getMaxY();
                int newMaxY = oldMaxY + (int) movingBody.getSpeedY();
                int conMinY = contactShape.getMinY();
                //System.out.printf("oldMaxY = %d, newMaxY  = %d, conMinY  = %d ",oldMaxY,newMaxY,conMinY);
                if (oldMaxY >= conMinY || newMaxY >= conMinY) {
                    int oldMinY = movingShape.getMinY();
                    int newMinY = oldMinY + (int) movingBody.getSpeedY();
                    int conMaxY = contactShape.getMaxY();
                    //System.out.printf("oldMinY = %d, newMinY  = %d, conMaxY  = %d ",oldMinY,newMinY,conMaxY);
                    if (oldMinY <= conMaxY || newMinY <= conMaxY) {
                        //System.out.println("goot to go");
                        //Check for new position
                        if (movingShape instanceof PointShape || contactShape instanceof PointShape) {
                            if (movingShape instanceof PointShape) {
                                if (((PointShape) movingShape).pointInShape(contactShape)) {
                                    return true;
                                }
                            } else {
                                if (((PointShape) contactShape).pointInShape(movingShape)) {
                                    return true;
                                }
                            }
                        } else if (movingShape instanceof RectangleShape || contactShape instanceof RectangleShape) {
                            //System.out.println("going");

                            if (newMaxX >= conMinX && newMinX <= conMaxX && newMaxY >= conMinY && newMinY <= conMaxY) {
                                System.out.println("soft hit");
                                return true;
                            }
                        }
                        //TODO: Triangle crap

                        //Check for the whole movement
                        if (contactShape instanceof RectangleShape || (!(movingShape instanceof PointShape) && !(contactShape instanceof TriangleShape))) {
                            if (movingShape instanceof PointShape) {
                                Point crossPoint;
                                crossPoint = GameShape.positiontwoLinesContact(movingShape.getPosition(), new Point(newMinX, newMaxY), new Point(conMinX, conMinY), new Point(conMaxX, conMinY));
                                if (crossPoint.getX() <= conMaxX && crossPoint.getX() <= conMinX) {
                                    return true;
                                }
                                crossPoint = GameShape.positiontwoLinesContact(movingShape.getPosition(), new Point(newMinX, newMaxY), new Point(conMinX, conMaxY), new Point(conMaxX, conMaxY));
                                if (crossPoint.getX() <= conMaxX && crossPoint.getX() <= conMinX) {
                                    return true;
                                }
                                crossPoint = GameShape.positiontwoLinesContact(movingShape.getPosition(), new Point(newMinX, newMaxY), new Point(conMaxX, conMinY), new Point(conMaxX, conMaxY));
                                if (crossPoint.getY() <= conMaxY && crossPoint.getY() <= conMinY) {
                                    return true;
                                }
                                crossPoint = GameShape.positiontwoLinesContact(movingShape.getPosition(), new Point(newMinX, newMaxY), new Point(conMinX, conMinY), new Point(conMinX, conMaxY));
                                if (crossPoint.getY() <= conMaxY && crossPoint.getY() <= conMinY) {
                                    return true;
                                }
                            } else {
                                //In case the moving target is bigger than the contact target, moving vector might miss the target, which is solved by the following:
                                if (((oldMaxY >= conMinY && newMaxY >= conMinY) && (oldMinY <= conMaxY && newMinY <= conMaxY))
                                        || ((oldMaxX >= conMinX && newMaxX >= conMinX) && (oldMinX <= conMaxX && newMinX <= conMaxX))) {
                                    System.out.println("asdf case");
                                    System.out.println("oldMaxX: "+oldMaxX+ " conMinX: "+conMinX+ " newMaxX: "+newMaxX+" conMinX: "+conMinX+" oldMinX: "+oldMinX+" conMaxX: "+conMaxX+" newMinX: "+newMinX+" conMaxX: "+conMaxX);
                                    System.out.println("(oldMaxX >= conMinX && newMaxX >= conMinX): "+(oldMaxX >= conMinX && newMaxX >= conMinX)+" (oldMinX <= conMaxX && newMinX <= conMaxX): "+(oldMinX <= conMaxX && newMinX <= conMaxX));
                                    return true;
                                }

                                int avgMinX = (oldMinX + newMinX) / 2;
                                int avgMaxX = (oldMaxX + newMaxX) / 2;
                                int avgMinY = (oldMinY + newMinY) / 2;
                                int avgMaxY = (oldMaxY + newMaxY) / 2;

                                //Check the beast approach so that it calculate the most probable line to "hit" the contactObject
                                if (Math.abs(avgMinX - contactShape.getX()) < Math.abs(avgMaxX - contactShape.getX())) {
                                    long distMinY = ((avgMinX - contactShape.getX()) * (avgMinX - contactShape.getX())) + ((avgMinY - contactShape.getY()) * (avgMinY - contactShape.getY()));
                                    long distMaxY = ((avgMinX - contactShape.getX()) * (avgMinX - contactShape.getX())) + ((avgMaxY - contactShape.getY()) * (avgMaxY - contactShape.getY()));
                                    if (distMinY < distMaxY) {
                                        if (checkLineContactSquare(new Point(oldMinX, oldMinY), new Point(newMinX, newMinY), conMinX, conMaxX, conMinY, conMaxY)) {
                                            System.out.println("hard hit");
                                            return true;
                                        }
                                    } else {
                                        if (checkLineContactSquare(new Point(oldMinX, oldMaxY), new Point(newMinX, newMaxY), conMinX, conMaxX, conMinY, conMaxY)) {
                                            System.out.println("hard hit");
                                            return true;
                                        }
                                    }
                                } else {
                                    long distMinY = ((avgMaxX - contactShape.getX()) * (avgMaxX - contactShape.getX())) + ((avgMinY - contactShape.getY()) * (avgMinY - contactShape.getY()));
                                    long distMaxY = ((avgMaxX - contactShape.getX()) * (avgMaxX - contactShape.getX())) + ((avgMaxY - contactShape.getY()) * (avgMaxY - contactShape.getY()));
                                    if (distMinY < distMaxY) {
                                        if (checkLineContactSquare(new Point(oldMaxX, oldMinY), new Point(newMaxX, newMinY), conMinX, conMaxX, conMinY, conMaxY)) {
                                            System.out.println("hard hit");
                                            return true;
                                        }
                                    } else {
                                        if (checkLineContactSquare(new Point(oldMaxX, oldMaxY), new Point(newMaxX, newMaxY), conMinX, conMaxX, conMinY, conMaxY)) {
                                            System.out.println("hard hit");
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //System.out.println("was false");
        return false;
    }

    public static boolean checkLocationContact(GameShape contactShape, Body body) {
        GameShape shape = body.getShape();
        int bodyMaxX = shape.getMaxX();
        int conMinX = contactShape.getMinX();
        if (bodyMaxX >= conMinX) {
            int bodyMinX = shape.getMinX();
            int conMaxX = contactShape.getMaxX();
            if (bodyMinX <= conMaxX) {
                int bodyMaxY = shape.getMaxY();
                int conMinY = contactShape.getMinY();
                if (bodyMaxY >= conMinY) {
                    int bodyMinY = shape.getMinY();
                    int conMaxY = contactShape.getMaxY();
                    if (bodyMinY <= conMaxY) {
                        if (shape instanceof RectangleShape) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //Checks if a contactLine will hit a square
    public static boolean checkLineContactSquare(Point origin, Point destination, int conMinX, int conMaxX, int conMinY, int conMaxY) {
        Point crossPoint;
        crossPoint = GameShape.positiontwoLinesContact(origin, destination, new Point(conMinX, conMinY), new Point(conMaxX, conMinY));
        if (crossPoint.getX() <= conMaxX && crossPoint.getX() <= conMinX) {
            return true;
        }
        crossPoint = GameShape.positiontwoLinesContact(origin, destination, new Point(conMinX, conMaxY), new Point(conMaxX, conMaxY));
        if (crossPoint.getX() <= conMaxX && crossPoint.getX() <= conMinX) {
            return true;
        }
        crossPoint = GameShape.positiontwoLinesContact(origin, destination, new Point(conMaxX, conMinY), new Point(conMaxX, conMaxY));
        if (crossPoint.getY() <= conMaxY && crossPoint.getY() <= conMinY) {
            return true;
        }
        crossPoint = GameShape.positiontwoLinesContact(origin, destination, new Point(conMinX, conMinY), new Point(conMinX, conMaxY));
        return crossPoint.getY() <= conMaxY && crossPoint.getY() <= conMinY;
    }

}
