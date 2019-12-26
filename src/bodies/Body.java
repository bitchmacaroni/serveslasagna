/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodies;

import images.animations.Animation;
import shapes.GameShape;

/**
 *
 * @author christian
 */
public class Body {

    protected GameShape shape;
    protected SolidProperty solidity;
    protected Animation standingAnimation;

    public Body(GameShape shape) {
        this.shape = shape;
        solidity = new SolidProperty();
    }

    public GameShape getShape() {
        return shape;
    }

    public void setShape(GameShape shape) {
        this.shape = shape;
    }

    public boolean isSolid() {
        return solidity != null;
    }

    public void setSolid(boolean solid) {
        if (solid) {
            if (!isSolid()) {
                solidity = new SolidProperty();
            }
        } else {
            solidity = null;
        }
    }

    private void setSolid(SolidProperty solidity) {
        this.solidity = solidity;
    }

    public SolidProperty getSolidity() {
        return solidity;
    }

    public Animation getStandingAnimation() {
        return standingAnimation;
    }

    public void setStandingAnimation(Animation standingAnimation) {
        this.standingAnimation = standingAnimation;
    }

    protected Body copyInto(Body body) {
        body.setSolid(solidity.copy());
        if (standingAnimation != null) {
            body.setStandingAnimation(standingAnimation.cloneAnimation());
        }
        return body;
    }

    public Body copy(GameShape shapeOfBody) {
        return copyInto(new Body(shapeOfBody));
    }
}
