/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodies;

import static java.lang.Math.max;
import static java.lang.Math.abs;

/**
 *
 * @author christian
 */
public class SolidProperty {

    private int roughness = 1;
    private int elasticity = 0;
    private float mass = 1;

    public int getRoughness() {
        return roughness;
    }

    public void setRoughness(int roughness) {
        this.roughness = roughness;
    }

    public int getElasticity() {
        return elasticity;
    }

    public void setElasticity(int elasticity) {
        this.elasticity = elasticity;
    }
    
    public float getFriction(float speed)
    {
        int sign = (speed > 0)?1:-1;
        return sign*max(abs(speed)-roughness,0);
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }
    
    public SolidProperty copy()
    {
        return copyInto(new SolidProperty());
    }
    
    protected SolidProperty copyInto(SolidProperty solidity)
    {
        solidity.setElasticity(elasticity);
        solidity.setMass(mass);
        solidity.setRoughness(roughness);
        return solidity;
    }
    
}
