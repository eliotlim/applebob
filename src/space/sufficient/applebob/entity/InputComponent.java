package space.sufficient.applebob.entity;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class InputComponent extends EntityComponent {
    boolean moveUp, moveDown, moveLeft, moveRight;
    long tick = 0;

    @Override
    protected void onTickImpl() {
        // TODO: Wait for player input
        tick++;
        if ((tick % 20) == 0) {
            if (moveUp) {
                getEntity().setY(getEntity().getY()-1);
                System.out.println("InputEvent Up!");
            } else if (moveDown) {
                getEntity().setY(getEntity().getY()+1);
                System.out.println("InputEvent Down!");
            } else if (moveRight) {
                getEntity().setX(getEntity().getX()+1);
                System.out.println("InputEvent Right!");
            } else if (moveLeft) {
                getEntity().setX(getEntity().getX()-1);
                System.out.println("InputEvent Left!");
            }
        }
    }

    @Override
    protected void onAttachImpl(Entity entity) {
        // Do nothing
    }

    @Override
    protected void onDetachImpl() {
        // Do nothing
    }

    public void onInputEvent(InputEvent e) {
        if (e instanceof KeyEvent) {
            KeyEvent ke = (KeyEvent)e;
            switch (ke.getID()) {
                case KeyEvent.KEY_PRESSED:
                    if (ke.getKeyCode() == KeyEvent.VK_UP) {
                        moveUp = true;
                        moveDown = moveLeft = moveRight = false;
                    } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                        moveDown = true;
                        moveUp = moveLeft = moveRight = false;
                    } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                        moveLeft = true;
                        moveUp = moveDown = moveRight = false;
                    } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                        moveRight= true;
                        moveUp = moveDown = moveLeft = false;
                    }
                break;
                case KeyEvent.KEY_RELEASED:
                    if (ke.getKeyCode() == KeyEvent.VK_UP) {
                        moveUp = false;
                    } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                        moveDown = false;
                    } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                        moveLeft = false;
                    } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                        moveRight= false;
                    }
            }
        }
    }
}
