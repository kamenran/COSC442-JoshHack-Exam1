package game;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

class InventoryTest {
    @Test
    void getItemsReturnsIndependentCopyOfSlots() {
        Inventory inventory = new Inventory(3);
        Item first = new Item('*', Color.GRAY, "stone", null);
        Item middle = new Item('!', Color.GRAY, "potion", null);
        Item last = new Item('/', Color.GRAY, "sword", null);
        inventory.add(first);
        inventory.add(middle);
        inventory.add(last);
        inventory.remove(middle);

        Item[] slots = inventory.getItems();

        assertEquals(3, slots.length);
        assertSame(first, slots[0]);
        assertNull(slots[1]);
        assertSame(last, slots[2]);

        slots[0] = null;
        slots[1] = middle;

        assertSame(first, inventory.get(0));
        assertNull(inventory.get(1));
        assertSame(last, inventory.get(2));
    }
}
