package space.sufficient.applebob.entity;

import space.sufficient.applebob.world.Tile;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ShooterComponent extends InputComponent {
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
            Entity arrow = new Entity(new Tile[][]{{Tile.ARROW_RIGHT}});
            arrow.setX(getEntity().getX()+1);
            arrow.setY(getEntity().getY());

            // TODO: Projectile Speed
            arrow.attachComponent(new ProjectileComponent(1, 0));
            getEntity().getWorld().addEntity(arrow);
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
                    if (ke.getKeyCode() == KeyEvent.VK_1) {
                        shoot = true;
                    }
                    break;
                case KeyEvent.KEY_RELEASED:
                    // If Shoot key is released
                    if (ke.getKeyCode() == KeyEvent.VK_1) {
                        shoot = false;
                    }
            }
        }
    }
}
