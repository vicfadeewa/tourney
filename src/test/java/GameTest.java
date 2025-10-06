import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    // проверка победы первого игрока при большей силе
    @Test
    void firstPlayerWinsWhenStronger() {
        Game game = new Game();
        game.register(new Player(1, "Иван", 100));
        game.register(new Player(2, "Мария", 80));
        assertEquals(1, game.round("Иван", "Мария"));
    }

    // проверка победы второго игрока при обратном порядке
    @Test
    void secondPlayerWinsWhenStronger() {
        Game game = new Game();
        game.register(new Player(1, "Иван", 100));
        game.register(new Player(2, "Мария", 80));
        assertEquals(2, game.round("Мария", "Иван"));
    }

    // проверка ничьи при равной силе
    @Test
    void drawWhenEqualStrength() {
        Game game = new Game();
        game.register(new Player(1, "Иван", 100));
        game.register(new Player(3, "Сергей", 100));
        assertEquals(0, game.round("Иван", "Сергей"));
    }

    // проверка отсутствие первого игрока
    @Test
    void throwWhenFirstNotRegistered() {
        Game game = new Game();
        game.register(new Player(2, "Мария", 80));

        Exception e = assertThrows(
                RuntimeException.class,
                () -> game.round("Иван", "Мария")
        );
        assertTrue(e.getMessage().contains("Иван не найден"));
    }

    // проверка боя игрока с самим собой
    @Test
    void selfFightIsAlwaysDraw() {
        Game game = new Game();
        game.register(new Player(1, "Иван", 100));
        assertEquals(0, game.round("Иван", "Иван"));
    }
}
