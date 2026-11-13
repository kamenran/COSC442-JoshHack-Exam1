
package game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CombatRulesTest {

    @ParameterizedTest
    @CsvSource({
        "50, 20, 70, false",
        "90, 10, 100, false",
        "90, 11, 100, false",
        "50, -20, 30, false",
        "2, -1, 1, false",
        "2, -2, 0, true",
        "2, -3, -1, true",
        "50, 0, 50, false"
    })
    void testCombatRules(int currentHp, int amount,
                         int expectedHp, boolean expectedDead) {
        int actualHp = CombatRules.calculateNewHp(currentHp, 100, amount);

        assertEquals(expectedHp, actualHp);
        assertEquals(expectedDead, CombatRules.isDead(actualHp));
    }
}
