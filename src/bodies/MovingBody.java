/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodies;

import images.animations.Animation;
import java.util.List;
import shapes.GameShape;

/**
 *
 * @author christian
 */
public class MovingBody extends Body implements Movable {

    protected float speedX;
    protected float speedXSurplus;
    protected float speedY;
    protected float speedYSurplus;
    protected float accelerationX;
    protected float accelerationY;
    protected boolean afectedByGravity;
    protected Animation movingAnimation;
    protected Animation fallingAnimation;

    public MovingBody() {
        super();
        afectedByGravity = false;
    }

    // <editor-fold defaultstate="collapsed" desc="getters/setters">
    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    protected float getSpeedXSurplus() {
        return speedXSurplus;
    }

    protected void setSpeedXSurplus(float speedXSurplus) {
        this.speedXSurplus = speedXSurplus;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    protected float getSpeedYSurplus() {
        return speedYSurplus;
    }

    protected void setSpeedYSurplus(float speedYSurplus) {
        this.speedYSurplus = speedYSurplus;
    }

    public float getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(float accelerationX) {
        this.accelerationX = accelerationX;
    }

    public float getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationY(float accelerationY) {
        this.accelerationY = accelerationY;
    }

    public boolean isAfectedByGravity() {
        return afectedByGravity;
    }

    public void setAfectedByGravity(boolean afectedByGravity) {
        this.afectedByGravity = afectedByGravity;
    }

    public Animation getMovingAnimation() {
        return movingAnimation;
    }

    public void setMovingAnimation(Animation movingAnimation) {
        this.movingAnimation = movingAnimation;
    }

    public Animation getFallingAnimation() {
        return fallingAnimation;
    }

    public void setFallingAnimation(Animation fallingAnimation) {
        this.fallingAnimation = fallingAnimation;
    }

    public boolean isMoving() {
        return false;
    }

    public boolean isFalling() {
        return false;
    }

    public boolean isStill() {
        System.out.println("speed is "+getSpeedX()+" , "+getSpeedY());
        return (-0.4 < speedX && speedX <= 0.4) && (speedY < 0.4 && speedY > -0.4);
    }

    // </editor-fold>
    @Override
    public void move(List<Body> knownBodies) {
        generalFisics();
        if (isSolid()) {
            for (Body knownBody : knownBodies) {
                if (!(this.equals(knownBody)) && knownBody.isSolid()) {
                    if (Movable.checkMoveContact(this.getShape(), knownBody.getShape(), this)) {
                        //System.out.println("initial ("+getShape().getX()+","+getShape().getY()+")");

                        GameShape contactShape = knownBody.getShape();

                        if (getShape().getMaxY() >= contactShape.getMinY()
                                && getShape().getMinY() <= contactShape.getMaxY()
                                && getShape().getMaxX() >= contactShape.getMinX()
                                && getShape().getMinX() <= contactShape.getMaxX()) {
                            solidsSpaceConflict(contactShape);
                        } else {
                            //System.out.println("MAX Y 1)"+contactShape.getMinY()+" 2)"+getShape().getMaxY());
                            if (getShape().getMaxY() < contactShape.getMinY()) {    //--- Moving object is on the top of the contact object ---                                
                                relocateUpward(contactShape);
                            } else if (getShape().getMinY() > contactShape.getMaxY()) {     //--- Moving object is on the bottom of the contact object ---
                                relocateDownward(contactShape);
                            } else {
                                if (walkOver()) {
                                    //TODO: WALK OVER OBSTACLES
                                } else {
                                    if (getSpeedX() != 0) {     //  If speed on x is 0, it's an error. (no solution for now *if it occurs*)
                                        if (getShape().getX() > contactShape.getX()) {
                                            relocateRightward(contactShape);
                                        } else {
                                            relocateLeftward(contactShape);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
    }

    private void solidsSpaceConflict(GameShape contactShape) {
        if (Math.abs(getShape().getX() - contactShape.getX()) - (getShape().getWidth() / 2) - (contactShape.getWidth() / 2) < Math.abs(getShape().getY() - contactShape.getY()) - (getShape().getHeight() / 2) - (contactShape.getHeight() / 2)) {
            if (getShape().getY() > contactShape.getY()) {
                relocateDownward(contactShape);
            } else {
                relocateUpward(contactShape);
            }
        } else {
            if (getShape().getX() > contactShape.getX()) {
                relocateRightward(contactShape);
            } else {
                relocateLeftward(contactShape);
            }
        }
    }

    private void relocateUpward(GameShape contactShape) {
        int newX = (int) (((contactShape.getMinY() - getShape().getMaxY()) * getSpeedX()) / getSpeedY()) + getShape().getX();
        getShape().setX(newX);
        getShape().setY(contactShape.getMinY() - getShape().getMaxY() + getShape().getY() - 1);

        setSpeedY(0);
        setSpeedYSurplus(0);
        

        setSpeedX(getSolidity().getFriction(getSpeedX()));
        
        System.out.println("speed is "+getSpeedX()+" , "+getSpeedY());
    }

    private void relocateDownward(GameShape contactShape) {
        int newX = (int) (((contactShape.getMaxY() - getShape().getMinY()) * getSpeedX()) / getSpeedY()) + getShape().getX();
        getShape().setX(newX);
        getShape().setY(getShape().getY() + (contactShape.getMaxY() - getShape().getMinY()) + 1);

        setSpeedY(0);
        setSpeedYSurplus(0);
    }

    private void relocateLeftward(GameShape contactShape) {
        int newY = (int) (((contactShape.getMinX() - getShape().getMaxX()) * getSpeedY()) / getSpeedX()) + getShape().getY();
        getShape().setY(newY);
        getShape().setX(contactShape.getMinX() - getShape().getMaxX() + getShape().getX() - 1);

        setSpeedX(0);
        setSpeedXSurplus(0);
    }

    private void relocateRightward(GameShape contactShape) {
        int newY = (int) (((contactShape.getMaxX() - getShape().getMinX()) * getSpeedY()) / getSpeedX()) + getShape().getY();
        getShape().setY(newY);
        getShape().setX(getShape().getX() + (contactShape.getMaxX() - getShape().getMinX()) + 1);

        setSpeedX(0);
        setSpeedXSurplus(0);
    }

    private boolean walkOver() {
        return false;
    }

    private void generalFisics() {
        speedXSurplus += speedX - Math.floor(speedX);
        int increaseX = (int) speedX + (int) speedXSurplus;
        if (speedXSurplus > 1f) {
            speedXSurplus--;
        }
        getShape().increaseX(increaseX);

        speedYSurplus += speedY - Math.floor(speedY);
        int increaseY = (int) speedY + (int) speedYSurplus;
        if (speedYSurplus > 1f) {
            speedYSurplus--;
        }
        getShape().increaseY(increaseY);

        speedX += accelerationX;
        speedY += accelerationY;

        if (isAfectedByGravity()) {
            speedY += 5;
        }

    }

}
