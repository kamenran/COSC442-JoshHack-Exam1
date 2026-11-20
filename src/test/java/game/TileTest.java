package game;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class TileTest {

    @Test
    void onlyWallsAreDiggable() {
        EnumSet<Tile> diggable = EnumSet.noneOf(Tile.class);
        for (Tile tile : Tile.values()) {
            if (tile.isDiggable()) {
                diggable.add(tile);
            }
        }

        assertEquals(EnumSet.of(Tile.WALL), diggable);
    }
}
