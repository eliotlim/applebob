package space.sufficient.applebob.world;

public interface IWorld {

    Tile getCell(int x, int y);
    void setCell(int x, int y, Tile val);

    int getHeight();
    int getWidth();

}
