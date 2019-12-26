/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import auxiliary.GameMouseEvent;
import auxiliary.MouseWheel;
import bodies.Body;
import bodies.MovingBody;
import editor.controller.entityClickers.ClickableCanvas;
import editor.controller.entityClickers.EntityClicker;
import editor.controller.entityClickers.ObjectListEntityClicker;
import images.GameImages;
import images.animations.GameAnimations;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import shapes.GameShape;
import shapes.RectangleShape;
import sidescrollerproto.SideScrollerProto;

/**
 *
 * @author christian
 */
public class ObjectListFrame extends Canvas implements Runnable, ClickableCanvas{

    private JFrame frame;
    private boolean running;
    EntityClicker editorEntityClicker;
    EntityClicker listEntityClicker;
    List<GameShape> entities;
    boolean shouldRender = true;
    SideScrollerProto frameReference;
    int mouseWheel;

    public ObjectListFrame(EntityClicker entityClicker) {
        setMinimumSize(new Dimension(200, 800));
        setMaximumSize(new Dimension(200, 800));
        setPreferredSize(new Dimension(200, 800));

        frame = new JFrame("Object Lists") {
            @Override
            public void dispose() {
                running = false;
                entityClicker.setEnabled(true);
                shouldRender = false;
                super.dispose(); //To change body of generated methods, choose Tools | Templates.
            }

        };

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
        
        entities = new ArrayList<>();
        
        GameMouseEvent mouseEvents = new GameMouseEvent();
        addMouseListener(mouseEvents);
        
        
        MouseWheel wheel = new MouseWheel((amount)->increaseMouseWheel(amount));
        addMouseWheelListener(wheel);
        
        
        //default object -- REMOVE ME
        int increment = 0;
        RectangleShape shape = new RectangleShape(Color.yellow, 100 , 100 + increment, this.getWidth(), 200);
        shape.setObjectImage("guy.png");
        shape.setBody(new MovingBody(shape));
        shape.getBody().setStandingAnimation(GameAnimations.getAnimation("ASprite-001.png"));
        shape.setScale(1);
        shape.setColor(Color.RED);
        entities.add(shape);
        increment += 3 + shape.getHeight();
        RectangleShape shape2 = new RectangleShape(Color.yellow, 100 , 150 + increment, this.getWidth(), 300);
        shape2.setObjectImage("guy.png");
        shape2.setBody(new Body(shape));
        shape2.getBody().setStandingAnimation(GameAnimations.getAnimation("ASprite-001.png"));
        shape2.setScale(1);
        shape2.setColor(Color.RED);
        entities.add(shape2);
        //
        
        this.editorEntityClicker = entityClicker;
        listEntityClicker = new ObjectListEntityClicker(mouseEvents, entities, this,editorEntityClicker);
    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        running = false;
    }

    public synchronized void dispose() {
        if (frame != null) {
            frame.dispose();
        } else {
            running = false;
            editorEntityClicker.setEnabled(true);
            shouldRender = false;
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1_000_000_000D / 60D;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;
        while (running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;

            lastTime = now;
            

            while (delta >= 1) {
                tick();
                delta = 0;
            }

            //*
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //*//*

            if (shouldRender) {
                render();
            }
            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
            }
        }
    }

    private void tick() {
        listEntityClicker.routineEditorCheck();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        
        for (GameShape entity : entities) {
            if (entity.getObjectImage() != null) {
                Image image = entity.getObjectImage();
                double x2 = WIDTH / 2 + entity.getParalax()  * (entity.getX()  - entity.getScale() * entity.getWidth() / 2);
                double y2 = HEIGHT / 2 + entity.getParalax()  * (entity.getY() - mouseWheel - entity.getScale() * entity.getHeight() / 2);
                
                double scale2 = (double) entity.getScale();
                double rotation2 = Math.toRadians(entity.getRotation());
                AffineTransform af2 = AffineTransform.getTranslateInstance(x2, y2);
                af2.scale(scale2 * entity.getWidth() / image.getWidth(this), scale2 * entity.getHeight() / image.getHeight(this));
                af2.rotate(rotation2, image.getWidth(this) / 2, image.getHeight(this) / 2);
                g2d.drawImage(image, af2, null);
            }
            entity.draw(g2d);
        }

        g.dispose();
        bs.show();
    }

    @Override
    public int getMouseX() {
        if(getMousePosition() != null)
            return getMousePosition().x;
        else
            return -1;
    }

    @Override
    public int getMouseY() {
        if(getMousePosition() != null)
            return getMousePosition().y + mouseWheel;
        else
            return -1;
    }

    @Override
    public boolean isMouseOutsideCanvas() {
        return getMousePosition() == null;
    }
    
    public void increaseMouseWheel(int amount)
    {
        mouseWheel += 20*amount;
    }
    
    public int getMouseWheel()
    {
        return mouseWheel;
    }
    
}
