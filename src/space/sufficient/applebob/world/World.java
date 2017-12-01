package space.sufficient.applebob.world;

import space.sufficient.applebob.entity.Entity;

public abstract class World implements IWorld {

    protected abstract boolean boundsCheck(int x, int y);

    private Entity focus;

    public int getCameraX() {
        // TODO: Set Focus
        return getWidth() / 2;
    }

    public int getCameraY() {
        // TODO: Set Focus
        return getHeight() / 2;
    }
}
