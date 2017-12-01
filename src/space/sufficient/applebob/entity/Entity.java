package space.sufficient.applebob.entity;

import space.sufficient.applebob.world.Tile;
import space.sufficient.applebob.world.World;

import java.util.ArrayList;

public class Entity {
    private long entityID;
    private ArrayList<EntityComponent> mComponents;

    private World mWorld;
    private int mPosX, mPosY, mWidth, mHeight;
    private Tile[][] mRepr;

    public Entity(Tile[][] representation) {
        mRepr = representation;
        mWidth = (mRepr.length == 0 ? 0 : mRepr[0].length);
        mHeight = mRepr.length;
        mComponents = new ArrayList<>();
    }

    public Tile[][] getRepresentation() {
        Tile[][] clone = mRepr.clone();
        for (int i = 0; i < clone.length; i++) {
            clone[i] = clone[i].clone();
        }
        return mRepr.clone();
    }

    public int getX() {
        return mPosX;
    }

    public void setX(int posX) {
        this.mPosX = posX;
    }

    public int getY() {
        return mPosY;
    }

    public void setY(int posY) {
        this.mPosY = posY;
    }

    public void onTick() {
        for (EntityComponent entityComponent : mComponents) {
            entityComponent.onTick();
        }
    }

    public void attachComponent(EntityComponent ec) {
        mComponents.add(ec);
        ec.onAttach(this);
    }

    public void detachComponent(EntityComponent ec) {
        mComponents.remove(ec);
        ec.onDetach();
    }

    public int getHeight() {
        return mHeight;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWorld(World world) { this.mWorld = world; }

    public World getWorld() { return mWorld; }
}
