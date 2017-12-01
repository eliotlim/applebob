package space.sufficient.applebob.world;

public enum Tile {
    FLOOR(" "),
    WALL("#"),
    VOID("X"),
    HEAD("O"),
    BODY("⤧");

    private final String repr;

    Tile(String representation) {
        this.repr = representation;
    }

    @Override
    public String toString() {
        return repr;
    }
}
