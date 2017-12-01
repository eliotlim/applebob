package space.sufficient.applebob.world;

public enum Tile {
    FLOOR(" "),
    WALL("#"),
    VOID("."),
    HEAD("O"),
    BODY("Å"),
    BODY2("B"),
    ARROW_UP("↥"),
    ARROW_DOWN("↧"),
    ARROW_LEFT("↤"),
    ARROW_RIGHT("↦"),
    EXP_1("."),
    EXP_2("o"),
    EXP_3("@"),
    EXP_4("!"),
    EXP_5("."),
    ;

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
                t == ARROW_RIGHT ||
                t == BODY ||
                t == BODY2 ||
                t == HEAD;
    }

    @Override
    public String toString() {
        return repr;
    }
}
