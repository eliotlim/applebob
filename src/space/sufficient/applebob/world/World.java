package space.sufficient.applebob.world;

import space.sufficient.applebob.entity.Entity;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;

public abstract class World implements IWorld, IGameWorld {

    protected List<Entity> mEntities = new ArrayList<>();
    private long tick = 0;

    private Entity focus;

    protected World() {
        mEntities = new ArrayList<>();

        Entity mapCenter = new Entity(new Tile[][]{});
        mapCenter.setX(getWidth()/2);
        mapCenter.setY(getHeight()/2);

        mEntities.add(mapCenter);
        focus = mapCenter;
    }

    protected abstract boolean boundsCheck(int x, int y);

    public void addEntity(Entity entity) {
        mEntities.add(entity);
    }

    public void removeEntity(Entity entity) {
        mEntities.remove(entity);
    }

    public void onTick() {
        tick++;
        for (Entity e : mEntities) {
            e.onTick();
        }
        onTickImpl();
    }

    public abstract void onTickImpl();

    public int getCameraX() {
        // TODO: Set Focus
        return focus.getX();
    }

    public int getCameraY() {
        // TODO: Set Focus
        return focus.getY();
    }

    @Override
    public void setFocus(Entity focus) {
        this.focus = focus;
    }

    @Override
    public Entity getFocus() {
        return focus;
    }
}
