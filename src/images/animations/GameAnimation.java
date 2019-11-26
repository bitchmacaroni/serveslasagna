/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images.animations;

/**
 *
 * @author christian
 */
public class GameAnimation {
    private Animation animation;
    private String Name;

    public GameAnimation(Animation animation, String Name) {
        this(animation);
        this.Name = Name;
    }

    public GameAnimation() {
    }

    public GameAnimation(Animation animation) {
        this.animation = animation;
    }
    

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    public Animation getClone()
    {
        return animation.cloneAnimation();
    }
}
