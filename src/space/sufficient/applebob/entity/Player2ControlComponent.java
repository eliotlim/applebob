package space.sufficient.applebob.entity;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Player2ControlComponent extends InputComponent {
    Entity.Direction nextDir = Entity.Direction.NEUTRAL;
    private long tick = 0;

    @Override
    protected void onTickImpl() {
        // TODO: Wait for player input
        tick--;
        if (tick <= 0) {
            // If movement cooldown has elapsed
            switch(nextDir) {
                case UP:
                    getEntity().setY(getEntity().getY() - 1);
                    tick = 7;
                    break;
                case DOWN:
                    getEntity().setY(getEntity().getY() + 1);
                    tick = 7;
                    break;
                case LEFT:
                    getEntity().setX(getEntity().getX() - 1);
                    tick = 4;
                    break;
                case RIGHT:
                    getEntity().setX(getEntity().getX() + 1);
                    tick = 4;
            }
            if (nextDir != Entity.Direction.NEUTRAL)
                getEntity().setProperty("direction", nextDir);
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
            KeyEvent ke = (KeyEvent) e;
            switch (ke.getID()) {
                case KeyEvent.KEY_PRESSED:
                    nextDir = getDirection(ke.getKeyCode());
                    break;
                case KeyEvent.KEY_RELEASED:
                    if (nextDir == getDirection(ke.getKeyCode())) {
                        nextDir = Entity.Direction.NEUTRAL;
                    }
            }
        }
    }
    
    public Entity.Direction getDirection(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            return Entity.Direction.UP;
        } else if (keyCode == KeyEvent.VK_S) {
            return Entity.Direction.DOWN;
        } else if (keyCode == KeyEvent.VK_A) {
            return Entity.Direction.LEFT;
        } else if (keyCode == KeyEvent.VK_D) {
            return Entity.Direction.RIGHT;
        }
        return Entity.Direction.NEUTRAL;
    }
}
