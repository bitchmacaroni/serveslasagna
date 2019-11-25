/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author christian
 */
public class EntityIO<T> {

    String name;
    T entity;
    List<AttributeIO> attributes;

    public EntityIO(String name, T entity) throws IllegalArgumentException, IllegalAccessException {
        attributes = new LinkedList<>();
        this.name = name;
        this.entity = entity;
        Class currentClass = entity.getClass();
        while (currentClass != null) {
            for (Field field : currentClass.getDeclaredFields()) {
                field.setAccessible(true);
                AttributeIO attribute = new AttributeIO(field.getName(), field.get(entity));
                attributes.add(attribute);
                field.setAccessible(false);
            }
            currentClass = currentClass.getSuperclass();
        }
        System.out.println("Attributes: " + attributes.size());
    }

    public void writeEntity(BufferedWriter bw) throws IOException {
        bw.write(String.format("<Entity>%n"));
        bw.write(String.format("<Name>%n"));
        bw.write(String.format(name + "%n"));
        bw.write(String.format("</Name>%n"));
        for (AttributeIO attribute : attributes) {
            attribute.writeProperty(bw);
        }
        bw.write(String.format("</Entity>%n"));
    }

}
