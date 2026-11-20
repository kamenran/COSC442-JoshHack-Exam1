# JoshHack Quality Baseline

COSC 442 · Project 5 · Task 3

## Baseline results

| Measure | Result |
| --- | --- |
| Passing JUnit 5 tests | 17 |
| Overall Java coverage | 9.13% |
| `game` package coverage | 11.86% |
| `CombatRules.java` coverage | 84.62% |
| `Creature.java` coverage | 15.02% |
| `CreatureAi.java` coverage | 6.90% |
| `Inventory.java` coverage | 12.24% |
| Branch coverage | Not separately reported |
| PIT mutation score | 7% |
| Surviving mutants | 39 |
| Mutations with no coverage | 926 |
| SpotBugs findings | 38 |

## Main weaknesses

1. **Low coverage of core gameplay functionality.** The existing tests focus on HP rules, creature death, and point equality. Low coverage in `Creature`, `CreatureAi`, and `Inventory` leaves gameplay such as movement, visibility, and item transfers vulnerable to regressions that the suite would miss.
2. **Weak mutation testing results and insufficient assertions.** PIT reported a 7% mutation score, with 926 mutations in code the tests did not reach and 39 mutants that survived after being reached. For example, the death test checks that a corpse exists, but a mutation that removes the corpse's food-value assignment still passes. That gap lets a gameplay outcome change without a test failure. [Creature death test](src/test/java/game/CreatureTest.java#L48)
3. **Exposure of mutable internal game state.** SpotBugs flags `Inventory.getItems()` because it returns the backing array. A caller can clear an equipped item's slot directly, bypassing the drop logic that also unequips it. The creature can then retain an equipment reference to an item absent from its inventory. [Inventory getter](src/main/java/game/Inventory.java#L6) · [Creature drop logic](src/main/java/game/Creature.java#L376)
