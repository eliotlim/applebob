package space.sufficient.applebob.world;

public enum Tile {
    FLOOR(" "),
    WALL("#"),
    VOID("."),
    HEAD("O"),
    BODY("⤧"),
    ARROW_RIGHT("↦");

    private final String repr;

    Tile(String representation) {
        this.repr = representation;
    }

    @Override
    public String toString() {
        return repr;
    }
}
