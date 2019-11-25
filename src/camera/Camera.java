/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package camera;

import shapes.Point;
import sidescrollerproto.SideScrollerProto;

/**
 *  Esta clase funciona como una camara, es el punto de referencia desde el cual se dibujan los componentes del juego
 * @author Keyk
 */
public class Camera {
    private static SideScrollerProto gameReference = null;
    
    private final int xLimit;
    private final int yLimit;
    private final int width;
    private final int height;
    private int xPos;
    private int yPos;
    private float zoom;

    public Camera(int xLimit, int yLimit, int width, int height) {
        this.xLimit = xLimit;
        this.yLimit = yLimit;
        this.width = width;
        this.height = height;
        zoom = 1;
    }

    public Camera(int xLimit, int yLimit,int width, int height, int xPos, int yPos) {
        this(xLimit,yLimit,width,height);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos=xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos=yPos;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }
    
    public void moveDown(int y)
    {
        this.yPos -= y;
    }
    
    public void moveUp(int y)
    {
        this.yPos += y;
    }
    
    public void moveLeft(int x)
    {
        this.xPos -= x;
    }
    
    public void moveRight(int x)
    {
        this.xPos += x;
    }

    public static void setGameReference(SideScrollerProto gameReference) {
        Camera.gameReference = gameReference;
    }

    public static SideScrollerProto getGameReference() {
        return gameReference;
    }
    
    public static Point getPointReferenceToCamera(Point point)
    {
        Camera mainCamera = Camera.gameReference.getMainCamera();
        int x = (int)(SideScrollerProto.getWIDTH() * SideScrollerProto.getSCALE() / 2 +  mainCamera.getZoom() * (point.getX() - mainCamera.getxPos()));
        int y = (int)(SideScrollerProto.getHEIGHT() * SideScrollerProto.getSCALE() / 2 +  mainCamera.getZoom() * (point.getY() - mainCamera.getyPos()));
        return new Point(x,y);
    }
    
    public static Point getPointReferenceFromScreen(Point point)
    {
        return new Point(Camera.getXReferenceFromScreen(point.getX()),Camera.getYReferenceFromScreen(point.getY()));
    }
    
    public static int getXReferenceToCamera(int parX)
    {
        Camera mainCamera = Camera.gameReference.getMainCamera();
        int x = (int)(SideScrollerProto.getWIDTH() * SideScrollerProto.getSCALE() / 2 +  mainCamera.getZoom() * (parX - mainCamera.getxPos()));
        return x;
    }
    
    public static int getXReferenceFromScreen(int parX)
    {
        Camera mainCamera = Camera.gameReference.getMainCamera();
        int x = mainCamera.getxPos()+(int)((2*parX-(SideScrollerProto.getWIDTH()*SideScrollerProto.getSCALE()))/(2*mainCamera.getZoom()));
        return x;
    }
    
    public static int getYReferenceToCamera(int parY)
    {
        Camera mainCamera = Camera.gameReference.getMainCamera();
        int y = (int)(SideScrollerProto.getHEIGHT() * SideScrollerProto.getSCALE() / 2 +  mainCamera.getZoom() * (parY - mainCamera.getyPos()));
        return y;
    }
    
    public static int getYReferenceFromScreen(int parY)
    {
        Camera mainCamera = Camera.gameReference.getMainCamera();
        int y = mainCamera.getyPos()+(int)((2*parY-(SideScrollerProto.getHEIGHT()*SideScrollerProto.getSCALE()))/(2*mainCamera.getZoom()));
        return y;
    }
    
    public static float getCurrentZoom()
    {
        return Camera.gameReference.getMainCamera().zoom;
    }
    
    //deleteme
    public static Camera getCurrentCamera()
    {
        return gameReference.getMainCamera();
    }
    
}
