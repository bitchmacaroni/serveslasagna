/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliary;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
/**
 *
 * @author Keyk
 */

/**
 * Esta clase sirve de input para las teclas del teclado
 * de momento solo recibe el input de 'w', 's', 'd', 'a' y barra espaciadora
 * @author Keyk
 */
public class KeyIsPressed {
    private static boolean wPressed = false;
    private static boolean sPressed = false;
    private static boolean dPressed = false;
    private static boolean aPressed = false;
    private static boolean spacePressed = false;
    private static boolean fPressed = false;
    private static boolean ePressed = false;
    private static boolean qPressed = false;
    private static boolean iPressed = false;
    private static boolean enterPressed = false;
    private static boolean escapePressed = false;
    private static boolean deletePressed = false;
    public static boolean isWPressed() {
        synchronized (KeyIsPressed.class) {
            return wPressed;
        }
    }
    public static boolean isSPressed() {
        synchronized (KeyIsPressed.class) {
            return sPressed;
        }
    }
    public static boolean isDPressed() {
        synchronized (KeyIsPressed.class) {
            return dPressed;
        }
    }
    public static boolean isAPressed() {
        synchronized (KeyIsPressed.class) {
            return aPressed;
        }
    }
    public static boolean isSpacePressed() {
        synchronized (KeyIsPressed.class) {
            return spacePressed;
        }
    }
    public static boolean isFPressed() {
        synchronized (KeyIsPressed.class) {
            return fPressed;
        }
    }
    public static boolean isEPressed() {
        synchronized (KeyIsPressed.class) {
            return ePressed;
        }
    }
    public static boolean isQPressed() {
        synchronized (KeyIsPressed.class) {
            return qPressed;
        }
    }
    public static boolean isEnterPressed() {
        synchronized (KeyIsPressed.class) {
            return enterPressed;
        }
    }
    public static boolean isEscapePressed() {
        synchronized (KeyIsPressed.class) {
            return escapePressed;
        }
    }
    public static boolean isIPressed() {
        synchronized (KeyIsPressed.class) {
            return iPressed;
        }
    }
    public static boolean isDeletePressed() {
        synchronized (KeyIsPressed.class) {
            return deletePressed;
        }
    }
    
    public KeyIsPressed()
    {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (KeyIsPressed.class) {
                    switch (ke.getID()) {
                    case KeyEvent.KEY_PRESSED:
                        if (ke.getKeyCode() == KeyEvent.VK_W) {
                            wPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_S) {
                            sPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_D) {
                            dPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_A) {
                            aPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                            spacePressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_F) {
                            fPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_E) {
                            ePressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_Q) {
                            qPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_I) {
                            iPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                            enterPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            escapePressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
                            deletePressed = true;
                        }
                        break;

                    case KeyEvent.KEY_RELEASED:
                        if (ke.getKeyCode() == KeyEvent.VK_W) {
                            wPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_S) {
                            sPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_D) {
                            dPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_A) {
                            aPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                            spacePressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_F) {
                            fPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_E) {
                            ePressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_Q) {
                            qPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_I) {
                            iPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                            enterPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            escapePressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
                            deletePressed = false;
                        }
                        break;
                    }
                    return false;
                }
            }
        });
    }
}