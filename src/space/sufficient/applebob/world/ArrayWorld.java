package space.sufficient.applebob.world;

public class ArrayWorld extends World {
    private final Tile[][] mWorld;
    private int mHeight, mWidth;

    public ArrayWorld(int height, int width) {
        this.mHeight = height;
        this.mWidth = width;
        mWorld = new Tile[height][width];
    }


    @Override
    public Tile getCell(int x, int y) {
        return Tile.FLOOR;
    }

    @Override
    public void setCell(int x, int y, Tile val) {

    }

    @Override
    public int getHeight() {
        return mHeight;
    }

    @Override
    public int getWidth() {
        return mWidth;
    }
}
