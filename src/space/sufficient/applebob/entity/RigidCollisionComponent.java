package space.sufficient.applebob.entity;

import space.sufficient.applebob.world.Tile;

public class RigidCollisionComponent extends EntityComponent {
    private int mPrevX, mPrevY, mTempX, mTempY;

    /**
     * RigidCollisionComponent will reset any movement that results in a collision
     * This is best added immediately AFTER the movement component.
     */

    @Override
    protected void onTickImpl() {
        mPrevX = mTempX;
        mPrevY = mTempY;

        System.out.println("Collision Check");

        if (collisionCheck()) {
            getEntity().setX(mPrevX);
            getEntity().setY(mPrevY);
            System.out.println("Object collision detected.");
        }

        mTempX = getEntity().getX();
        mTempY = getEntity().getY();
    }

    @Override
    protected void onAttachImpl(Entity entity) {

    }

    @Override
    protected void onDetachImpl() {

    }

    public boolean collisionCheck() {
        boolean collision = false;
        for (int r = 0; r < getEntity().getHeight(); r++) {
            for (int c = 0; c < getEntity().getWidth(); c++) {
                if (Tile.isSolid(getEntity().getWorld().getCell(getEntity().getX() + c, getEntity().getY() + r))) {
                    collision = true;
                }
            }
        }
        return collision;
    }

}
