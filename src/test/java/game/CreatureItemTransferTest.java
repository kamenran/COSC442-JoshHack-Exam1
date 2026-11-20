package game;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreatureItemTransferTest {
    private static final int X = 1;
    private static final int Y = 1;
    private static final int Z = 0;

    private World world;
    private Creature creature;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        Tile[][][] tiles = new Tile[3][3][1];
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y][Z] = Tile.FLOOR;
            }
        }

        world = new World(tiles);
        creature = new Creature(world, '@', Color.GRAY, "josh", 100, 10, 5);
        creature.x = X;
        creature.y = Y;
        creature.z = Z;
        world.add(creature);
        new CreatureAi(creature);
        inventory = creature.inventory();
    }

    @Test
    void pickupTransfersItemFromWorldToInventory() {
        Item item = new Item('*', Color.GRAY, "stone", null);
        assertTrue(world.addAtEmptySpace(item, X, Y, Z));

        creature.pickup();

        assertNull(world.item(X, Y, Z));
        assertSame(item, inventory.get(0));
    }

    @Test
    void pickupWithFullInventoryLeavesItemInWorld() {
        int capacity = inventory.getItems().length;
        for (int slot = 0; slot < capacity; slot++) {
            inventory.add(new Item('*', Color.GRAY, "stone " + slot, null));
        }
        Item[] originalSlots = inventory.getItems().clone();
        assertTrue(inventory.isFull());
        Item groundItem = new Item('!', Color.GRAY, "potion", null);
        assertTrue(world.addAtEmptySpace(groundItem, X, Y, Z));

        creature.pickup();

        assertSame(groundItem, world.item(X, Y, Z));
        assertFalse(inventory.contains(groundItem));
        assertArrayEquals(originalSlots, inventory.getItems());
    }

    @Test
    void dropEquippedWeaponReturnsItToWorldAndClearsWeaponSlot() {
        Item sword = new Item('/', Color.GRAY, "sword", null);
        sword.modifyAttackValue(5);
        inventory.add(sword);
        creature.equip(sword);
        assertSame(sword, creature.weapon());
        assertNull(world.item(X, Y, Z));

        creature.drop(sword);

        assertSame(sword, world.item(X, Y, Z));
        assertFalse(inventory.contains(sword));
        assertNull(creature.weapon());
    }
}
