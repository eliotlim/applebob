package space.sufficient.applebob.world;

import space.sufficient.applebob.entity.Entity;

public class ArrayWorld extends World {
    private final Tile[][] mBase, mWorld;
    private int mWidth, mHeight;

    public ArrayWorld(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
        mBase = new Tile[mHeight][mWidth];
        mWorld = new Tile[mHeight][mWidth];

        // Initialize the world to contain floor tiles only.
        for (int r = 0; r < mHeight; r++) {
            for (int c = 0; c < mWidth; c++) {
                mBase[r][c] = Tile.FLOOR;
                mWorld[r][c] = Tile.FLOOR;
            }
        }

        Entity mapCenter = new Entity(new Tile[][]{});
        mapCenter.setX(getWidth()/2);
        mapCenter.setY(getHeight()/2);
        setFocus(mapCenter);
    }


    @Override
    public Tile getCell(int x, int y) {
        if (boundsCheck(x, y)) return mBase[y][x];
        return Tile.VOID;
    }

    @Override
    public void setCell(int x, int y, Tile val) {
        if (!boundsCheck(x,y)) return;
        mWorld[y][x] = val;
        mBase[y][x] = val;
    }

    @Override
    public int getHeight() {
        return mHeight;
    }

    @Override
    public int getWidth() {
        return mWidth;
    }

    @Override
    public boolean boundsCheck(int x, int y){
        if (x < 0 || x >= mWidth) return false;
        if (y < 0 || y >= mHeight) return false;
        return true;
    }

    @Override
    public void onTickImpl() {
        for (int r = 0; r < getHeight(); r++) {
            for (int c = 0; c < getWidth(); c++) {
                mWorld[r][c] = mBase[r][c];
            }
        }

        for (Entity e : mEntities) {
            boolean rollback = false;
            for (int r = 0; r < e.getHeight(); r++) {
                for (int c = 0; c < e.getWidth(); c++) {
                    //TODO: Collision check
                    setRendered(e.getX()+c, e.getY()+r, e.getRepresentation()[r][c]);
                }
            }
        }
    }

    @Override
    public Tile renderCell(int x, int y) {
        if (boundsCheck(x, y)) return mWorld[y][x];
        return Tile.VOID;
    }

    @Override
    public void setRendered(int x, int y, Tile val) {
        if (boundsCheck(x, y)) mWorld[y][x] = val;
    }
}
