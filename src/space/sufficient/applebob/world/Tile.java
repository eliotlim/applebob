package space.sufficient.applebob.world;

public enum Tile {
    FLOOR(" "),
    WALL("#"),
    VOID("."),
    HEAD("O"),
    BODY("Å"),
    ARROW_UP("↥"),
    ARROW_DOWN("↧"),
    ARROW_LEFT("↤"),
    ARROW_RIGHT("↦");

    private final String repr;

    Tile(String representation) {
        this.repr = representation;
    }

    public static boolean isSolid(Tile t) {
        return t == WALL ||
                t == VOID ||
                t == ARROW_UP ||
                t == ARROW_DOWN ||
                t == ARROW_LEFT ||
                t == ARROW_RIGHT;
    }

    @Override
    public String toString() {
        return repr;
    }
}
