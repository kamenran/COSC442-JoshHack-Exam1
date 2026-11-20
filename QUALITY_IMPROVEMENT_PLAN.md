# JoshHack Quality Improvement Plan

COSC 442 · Project 5 · Task 4

## Task 3 baseline

| Measure | Result |
| --- | --- |
| Passing JUnit 5 tests | 17 |
| Overall Java coverage | 9.13% |
| PIT mutation score | 7% |
| Surviving mutants | 39 |
| SpotBugs findings | 38 |

See the [Task 3 quality baseline](QUALITY_BASELINE.md) for the full results.

## 1. Add JUnit tests for item pickup and drop

- **Improvement.** Test that pickup moves the same item from the ground into the inventory, that a full inventory leaves the item on the ground, and that dropping an equipped weapon puts it back in the world and clears the equipment slot.
- **Evidence.** `Inventory.java` has 12.24% coverage. PIT marks mutations in `Creature.pickup()` and `Creature.drop()` as uncovered, including mutations that skip ground removal, inventory removal, or unequipping.
- **Rationale.** Checking both the world and the inventory after each transfer catches regressions that could lose or duplicate items or leave equipment in an incorrect state.
- **Files.** Add `src/test/java/game/CreatureItemTransferTest.java`.

## 2. Strengthen the creature death test

- **Improvement.** Extend the existing fatal-damage test to verify that the corpse has a food value of `500` for the test creature with 100 maximum HP.
- **Evidence.** The test checks that the corpse exists. Two PIT mutants survived in `leaveCorpse()`. One removes the food-value assignment and the other changes the multiplication to division.
- **Rationale.** An exact food-value assertion verifies the corpse's behavior as a consumable and targets both surviving mutants.
- **Files.** Modify `src/test/java/game/CreatureTest.java`.

## 3. Protect inventory from external modifications

- **Improvement.** Return a shallow copy from `Inventory.getItems()` and add a JUnit test that clears a slot in the returned array and verifies that the original inventory still contains the item. The copy preserves slot order, empty slots, and item identity.
- **Evidence.** SpotBugs reports `EI_EXPOSE_REP` for `Inventory.getItems()` because it returns the backing array.
- **Rationale.** Copying the array prevents callers from overwriting inventory slots directly and bypassing game logic that keeps inventory and equipment state consistent.
- **Files.** Modify `src/main/java/game/Inventory.java` and add `src/test/java/game/InventoryTest.java`.
