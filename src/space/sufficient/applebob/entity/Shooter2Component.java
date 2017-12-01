package space.sufficient.applebob.entity;

import space.sufficient.applebob.world.Tile;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Shooter2Component extends InputComponent {
    private boolean shoot = false;
    private long mTick = 0;

    private static int sShootInterval = 30;

    @Override
    protected void onTickImpl() {
        mTick++;
        if (shoot && mTick > sShootInterval) {
            // If shoot cooldown has elapsed
            mTick = 0;

            // Create a new Arrow Entity.
            // TODO: Player Direction
            Object od = getEntity().getProperty("direction");
            if (od != null && od instanceof Entity.Direction) {
                Entity.Direction d = (Entity.Direction) od;
                if (d == Entity.Direction.NEUTRAL) d = Entity.Direction.RIGHT;

                Tile t = getArrow(d);
                Entity arrow = new Entity(new Tile[][]{{t}});

                int velX = 0, velY = 0;
                if (d == Entity.Direction.UP) velY = -1;
                else if (d == Entity.Direction.DOWN) velY = 1;

                if (d == Entity.Direction.LEFT) velX = -1;
                else if (d == Entity.Direction.RIGHT) velX = 1;


                arrow.setX(getEntity().getX() + velX);
                arrow.setY(getEntity().getY() + velY);

                // TODO: Projectile Speed
                arrow.attachComponent(new ProjectileComponent(velX, velY));
                getEntity().getWorld().addEntity(arrow);
            }
        }
    }

    @Override
    protected void onAttachImpl(Entity entity) {

    }

    @Override
    protected void onDetachImpl() {

    }

    @Override
    public void onInputEvent(InputEvent e) {
        if (e instanceof KeyEvent) {
            KeyEvent ke = (KeyEvent)e;
            switch (ke.getID()) {
                case KeyEvent.KEY_PRESSED:
                    // If Shoot key is pressed
                    if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                        shoot = true;
                    }
                    break;
                case KeyEvent.KEY_RELEASED:
                    // If Shoot key is released
                    if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                        shoot = false;
                    }
            }
        }
    }

    public Tile getArrow(Entity.Direction dir) {
        switch (dir) {
            case UP:
                return Tile.ARROW_UP;
            case DOWN:
                return Tile.ARROW_DOWN;
            case LEFT:
                return Tile.ARROW_LEFT;
            case RIGHT:
                return Tile.ARROW_RIGHT;
            default:
                return null;
        }
    }
}
