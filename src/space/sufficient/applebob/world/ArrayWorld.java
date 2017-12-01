package space.sufficient.applebob.world;

public class ArrayWorld extends World {
    private final Tile[][] mWorld;
    private int mWidth, mHeight;

    public ArrayWorld(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
        mWorld = new Tile[mHeight][mWidth];

        // Initialize the world to contain floor tiles only.
        for (int r = 0; r < mHeight; r++) {
            for (int c = 0; c < mWidth; c++) {
                mWorld[r][c] = Tile.FLOOR;
            }
        }
    }


    @Override
    public Tile getCell(int x, int y) {
        if (boundsCheck(x, y)) return mWorld[y][x];
        return Tile.VOID;
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

    @Override
    public boolean boundsCheck(int x, int y){
        if (x < 0 || x >= mWidth) return false;
        if (y < 0 || y >= mHeight) return false;
        return true;
    }
}
