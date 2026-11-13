
package game;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreatureTest {

    private World world;
    private Creature creature;

    @BeforeEach
    void setUp() {
        Tile[][][] tiles = new Tile[3][3][1];

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }

        world = new World(tiles);

        creature = new Creature(world, '@', Color.GRAY, "josh", 100, 10, 5);

        creature.x = 1;
        creature.y = 1;
        creature.z = 0;

        world.add(creature);
        new CreatureAi(creature);
    }

    @Test
    void normalDamageDoesNotCauseDeath() {
        creature.modifyHp(-20, "damage");

        assertEquals(80, creature.hp());
        assertEquals(creature, world.creature(1, 1, 0));
    }

    @Test
    void fatalDamageCausesDeath() {
        creature.modifyHp(-100, "damage");

        assertEquals(0, creature.hp());
        assertNull(world.creature(1, 1, 0));
        assertNotNull(world.item(1, 1, 0));
    }

    @Test
    void healingDoesNotExceedMaximumHp() {
        creature.modifyHp(20, "healing");

        assertEquals(100, creature.hp());
        assertEquals(creature, world.creature(1, 1, 0));
    }
    @Test
void deathAnnouncementIsSent() {
    StringBuilder messages = new StringBuilder();

    new CreatureAi(creature) {
        @Override
        public void onNotify(String message) {
            messages.append(message);
        }
    };

    creature.modifyHp(-100, "damage");

    assertTrue(messages.toString().contains("You die."));
}
}
