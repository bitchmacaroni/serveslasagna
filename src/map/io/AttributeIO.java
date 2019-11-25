/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map.io;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author christian
 */
public class AttributeIO<T> {

    String name;
    T value;

    public AttributeIO(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public void writeProperty(BufferedWriter bw) throws IOException {
        bw.write(String.format("<Attribute>%n"));
        bw.write(String.format(name + ":" + value.toString() + "%n"));
        bw.write(String.format("</Attribute>%n"));
    }

}
