/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sidescrollerproto;

import auxiliary.GameMouseEvent;
import auxiliary.KeyIsPressed;
import bodies.Body;
import bodies.MovingBody;
import camera.Camera;
import editor.controller.EntityClicker;
import editor.controller.ShapeCreator;
import editor.tool.EditorButton;
import editor.tool.RectangleTool;
import editor.tool.Tool;
import editor.tool.TriangleTool;
import images.GameImages;
import images.animations.GameAnimations;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import map.Map;
import shapes.GameShape;
import shapes.Point;

/**
 *
 * @author christian
 */
public class SideScrollerProto extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 400;
    private static final int HEIGHT = WIDTH / 12 * 9;
    private static final int SCALE = 3;
    private static final String NAME = "Sidescroller Game";
    private JFrame frame;
    private boolean running = false;
    private int tickCount = 0;
    private GameMouseEvent mouseEvents;
    private EntityClicker entityClicker;
    private Camera mainCamera;
    private KeyIsPressed key = new KeyIsPressed();
    private boolean testMovements;
    private MovingBody player;

    //A optimizar
    public LinkedList<GameShape> entities;
    public LinkedList<Body> gameBodies;
    public Tool SelectedTool;

    public SideScrollerProto() {
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        try {
            GameImages.loadAllImages();
            GameAnimations.LoadAllAnimations();
        } catch (IOException e) {
            System.out.println("error huge");
        }
        mouseEvents = new GameMouseEvent();
        addMouseListener(mouseEvents);

        Field[] fields = getClass().getFields();
        if (fields.length > 0) {
            String nameField = fields[0].getName();
        }
        editorInit();
    }

    public final void editorInit() {
        Camera.setGameReference(this);
        mainCamera = new Camera(3000, 1500, WIDTH * SCALE, HEIGHT * SCALE, 300, 300);
        //mainCamera.setZoom(0.1f);
        mainCamera.setZoom(1f);
        entities = new LinkedList<>();
        RectangleTool rectangle = new RectangleTool(Color.YELLOW, getWidth() - 50, 20, 40, 20);
        entities.add(rectangle);
        rectangle = new RectangleTool(Color.YELLOW, getWidth() - 50, 110, 40, 20);
        entities.add(rectangle);
        entityClicker = new EntityClicker(mouseEvents, entities, this);

        gameBodies = new LinkedList<>();

        testMovements = false;

        EditorButton button = new EditorButton(Color.BLUE, getWidth() - 50, getHeight() - 200, 40, 20) {
            @Override
            public void doIt() {
                new Map(1000, 1000).saveMap("asdf", entities);
            }

            @Override
            public boolean contactLine(Point point1, Point point2) {
                return false;
            }
        };
        entities.add(button);

        EditorButton testButton = new EditorButton(Color.PINK, getWidth() - 50, getHeight() - 300, 40, 20) {
            @Override
            public void doIt() {
                if (testMovements) {
                    testMovements = false;
                } else {
                    testMovements = true;
                }
            }

            @Override
            public boolean contactLine(Point point1, Point point2) {
                return false;
            }
        };
        entities.add(testButton);

        //testing
        /*
        System.out.println("from screen x: "+Camera.getXReferenceFromScreen(Camera.getXReferenceToCamera(500)));
        System.out.println("to camera x: "+Camera.getXReferenceToCamera(Camera.getXReferenceFromScreen(500)));
        System.out.println("from screen y: "+Camera.getYReferenceFromScreen(Camera.getYReferenceToCamera(500)));
        System.out.println("to camera y: "+Camera.getYReferenceToCamera(Camera.getYReferenceFromScreen(500)));*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new SideScrollerProto().start();
    }

    private synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    private synchronized void stop() {
        running = false;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1_000_000_000D / 60D;

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;
        while (running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;

            lastTime = now;
            boolean shouldRender = true;

            while (delta >= 1) {
                /*
                time1 = System.currentTimeMillis();
                //*/
                ticks++;
                if (testMovements) {
                    tick();
                } else {
                    editorTick();
                }
                delta = 0;
                shouldRender = true;
                /*
                time2 = System.currentTimeMillis();
                System.out.println("time "+(time2-time1)+" delta "+delta);
                //*/
            }

            //*
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //*//*

            if (shouldRender) {
                frames++;
                renderEditor();
            }
            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println(frames + "," + ticks);
                frames = 0;
                ticks = 0;
            }
        }
    }

    private void tick() {
        tickCount++;

        for (Body body : gameBodies) {

            if (body instanceof MovingBody) {
                ((MovingBody) body).move(gameBodies);
                if (((MovingBody) body).getMovingAnimation() != null) {
                    ((MovingBody) body).getMovingAnimation().tickAnimation();
                }
                if (((MovingBody) body).getFallingAnimation() != null) {
                    ((MovingBody) body).getMovingAnimation().tickAnimation();
                }
            }
            if (body.getStandingAnimation() != null) {
                body.getStandingAnimation().tickAnimation();
            }
        }

        if (player != null && tickCount % 10 == 0) {
            if (KeyIsPressed.isWPressed()) {
                player.setSpeedY(player.getSpeedY() - 10);
                //mainCamera.moveDown(5);
            }
            if (KeyIsPressed.isSPressed()) {
                player.setSpeedY(player.getSpeedY() + 10);
            }
            if (KeyIsPressed.isDPressed()) {
                player.setSpeedX(player.getSpeedX() + 5);
                //mainCamera.moveRight(5);
            }
            if (KeyIsPressed.isAPressed()) {
                player.setSpeedX(player.getSpeedX() - 5);
                //mainCamera.moveLeft(5);
            }
        }
    }

    private void editorTick() {
        tickCount++;
        entityClicker.routineEditorCheck();
        if (KeyIsPressed.isWPressed()) {
            mainCamera.moveDown(5);
        }
        if (KeyIsPressed.isSPressed()) {
            mainCamera.moveUp(5);
        }
        if (KeyIsPressed.isDPressed()) {
            mainCamera.moveRight(5);
        }
        if (KeyIsPressed.isAPressed()) {
            mainCamera.moveLeft(5);
        }
        if (KeyIsPressed.isDeletePressed()) {
            entityClicker.deleteSelected();
        }

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
        g2d.drawImage(GameImages.getImage("guy.png"), 0, 0, getWidth(), getHeight(), this);

        g.dispose();
        bs.show();
    }

    private void renderEditor() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        double x = WIDTH * SCALE / 2 + mainCamera.getZoom() * (0 - mainCamera.getxPos() - 1 * GameImages.getImage("guy.png").getWidth(this) / 2);
        double y = HEIGHT * SCALE / 2 + mainCamera.getZoom() * (0 - mainCamera.getyPos() - 1 * GameImages.getImage("guy.png").getHeight(this) / 2);
        double scale = (double) mainCamera.getZoom() * 1;
        double rotation = Math.toRadians(tickCount);

        AffineTransform af = AffineTransform.getTranslateInstance(x, y);
        af.scale(scale, scale);
        af.rotate(rotation,/*0/**/ GameImages.getImage("guy.png").getWidth(this) / 2 /**/, /*0/**/ GameImages.getImage("guy.png").getHeight(this) / 2 /**/);
        //g2d.drawImage(GameImages.getImage("guy.png"), af, null);

        //g2d.drawImage(GameImages.getGuy(), 0, 0, getWidth(), getHeight(), this);
        for (GameShape entity : entities) {
            if (entity.getObjectImage() != null) {
                Image image = entity.getObjectImage();
                if (entity.getBody() != null) {
                    Body body = entity.getBody();
                    if (body instanceof MovingBody) {
                        MovingBody mBody = (MovingBody) body;
                        if (mBody.getMovingAnimation() != null && mBody.isMoving()) {
                        } else if (mBody.getFallingAnimation() != null && mBody.isFalling()) {
                        } else if (mBody.getStandingAnimation() != null && mBody.isStill()) {
                            image = mBody.getStandingAnimation().getImage();
                        }
                    } else if (body.getStandingAnimation() != null) {
                        image = body.getStandingAnimation().getImage();
                    }
                }
                double x2 = WIDTH * SCALE / 2 + entity.getParalax() * mainCamera.getZoom() * (entity.getX() - mainCamera.getxPos() - entity.getScale() * entity.getWidth() / 2);
                double y2 = HEIGHT * SCALE / 2 + entity.getParalax() * mainCamera.getZoom() * (entity.getY() - mainCamera.getyPos() - entity.getScale() * entity.getHeight() / 2);
                double scale2 = (double) mainCamera.getZoom() * entity.getScale();
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

    public int getMouseX() {
        return getMousePosition().x;
    }

    public int getMouseY() {
        return getMousePosition().y;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getSCALE() {
        return SCALE;
    }

    public Camera getMainCamera() {
        return mainCamera;
    }

    public List<Body> getGameBodies() {
        return gameBodies;
    }

    public void setPlayer(MovingBody player) {
        this.player = player;
    }

}
