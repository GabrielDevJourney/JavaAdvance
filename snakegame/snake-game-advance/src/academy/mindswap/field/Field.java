package academy.mindswap.field;

import academy.mindswap.gameobjects.Obstacle.Obstacle;
import academy.mindswap.gameobjects.Obstacle.ObstacleType;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.fruit.FruitType;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

public final class Field {

    private static final String SNAKE_HEAD_STRING = "0";
    private static final String SNAKE_BODY_STRING = ".";
    private static final Terminal.Color TEXT_MENU_COLOR = Terminal.Color.WHITE;
    private static final String FRUIT_STRING = "\uF8FF";
    private static final String[] GAMEOVER_MESSAGE = {
            "  ▄████  ▄▄▄       ███▄ ▄███▓▓█████     ▒█████   ██▒   █▓▓█████  ██▀███  ",
            " ██▒ ▀█▒▒████▄    ▓██▒▀█▀ ██▒▓█   ▀    ▒██▒  ██▒▓██░   █▒▓█   ▀ ▓██ ▒ ██▒",
            "▒██░▄▄▄░▒██  ▀█▄  ▓██    ▓██░▒███      ▒██░  ██▒ ▓██  █▒░▒███   ▓██ ░▄█ ▒",
            "░▓█  ██▓░██▄▄▄▄██ ▒██    ▒██ ▒▓█  ▄    ▒██   ██░  ▒██ █░░▒▓█  ▄ ▒██▀▀█▄  ",
            "░▒▓███▀▒ ▓█   ▓██▒▒██▒   ░██▒░▒████▒   ░ ████▓▒░   ▒▀█░  ░▒████▒░██▓ ▒██▒",
            " ░▒   ▒  ▒▒   ▓▒█░░ ▒░   ░  ░░░ ▒░ ░   ░ ▒░▒░▒░    ░ ▐░  ░░ ▒░ ░░ ▒▓ ░▒▓░",
            "  ░   ░   ▒   ▒▒ ░░  ░      ░ ░ ░  ░     ░ ▒ ▒░    ░ ░░   ░ ░  ░  ░▒ ░ ▒░",
            "",
            "                      Press ENTER to Start a New Game",
            "                           Backspace to Exit Game"
    };

    private static int width;
    private static int height;
    private static Screen screen;
    private static ScreenWriter screenWriter;

    private Field() {
    }

    public static void init(int width, int height) {

        screen = TerminalFacade.createScreen();

        Field.width = width;
        Field.height = height;
        screen.getTerminal().getTerminalSize().setColumns(width);
        screen.getTerminal().getTerminalSize().setRows(height);

        screenWriter = new ScreenWriter(screen);
        screen.setCursorPosition(null);
        screen.startScreen();

        screen.refresh();
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void drawSnake(Snake snake) {

        if (snake.isIsvisible()) {
            return;
        }

        Terminal.Color snakeColor = Terminal.Color.GREEN;

        if (!snake.isAlive()){
            snakeColor = Terminal.Color.RED;
        }

        Position head = snake.getHead();

        for (Position p : snake.getFullSnake()) {
            if (!p.equals(head)) {
                screen.putString(p.getCol(), p.getRow(), SNAKE_BODY_STRING, snakeColor, null);
            } else {
                screen.putString(p.getCol(), p.getRow(), SNAKE_HEAD_STRING, snakeColor, null);
            }
        }
        screen.refresh();
    }


    public static void drawFruit(Fruit fruit) {
        screen.putString(fruit.getPosition().getCol(), fruit.getPosition().getRow(), FRUIT_STRING, fruit.getColor(), null);
    }

    public static void drawObstacle(Obstacle obstacle) {
        screen.putString(obstacle.getPosition().getCol(), obstacle.getPosition().getRow(), obstacle.getObstacleSymbol(),
                obstacle.getColor(),
                null);
    }

    public static void clearPosition(Position position) {
        screen.putString(position.getCol(), position.getRow(), " ", null, null);
        screen.refresh();
    }


    public static Key readInput() {
        return screen.readInput();
    }

    public static void gameOverMessage() {
        int startRow = 5;
        int startCol = 10;

        for (String line : GAMEOVER_MESSAGE) {
            screen.putString(startRow, startCol, line, Terminal.Color.RED, null, ScreenCharacterStyle.Blinking);
            startRow++;
            startCol++;
        }
        screen.refresh();
    }

    public static void stopScreen(){
        screen.stopScreen();
    }

    public static void closeTerminal(){
        screen.stopScreen();
        System.exit(0);
    }

    public static void clearHead(Position head) {
        screen.putString(head.getCol(), head.getRow(), " ", null, null);
    }

    public static void gameMenu() {
        int startRow = 5;
        int startCol = 10;

        // Header
        screen.putString(startCol, startRow++, "                              SNAKE GAME - HOW TO PLAY                               ", Terminal.Color.GREEN, null);
        screen.putString(startCol, startRow++, "═══════════════════════════════════════════════════════════════════════════════════", Terminal.Color.GREEN, null);
        startRow++;  // Empty line

        // Controls
        screen.putString(startCol, startRow++, "CONTROLS:", Terminal.Color.GREEN, null);
        screen.putString(startCol, startRow++, "  ↑ ↓ ← → : Control snake direction", TEXT_MENU_COLOR, null);
        startRow++;  // Empty line

        // Fruits
        screen.putString(startCol, startRow++, "FRUITS:", Terminal.Color.GREEN, null);
        screen.putString(startCol, startRow, "  " + FRUIT_STRING + " Normal Fruit", FruitType.NORMAL.getColor(), null);
        screen.putString(startCol + 10, startRow++, " : +1 to snake length", TEXT_MENU_COLOR, null);

        screen.putString(startCol, startRow, "  " + FRUIT_STRING + " Double Fruit", FruitType.DOUBLESIZE.getColor(), null);
        screen.putString(startCol + 10, startRow++, " : +2 to snake length", TEXT_MENU_COLOR, null);

        screen.putString(startCol, startRow, "  " + FRUIT_STRING + " Super Fruit", FruitType.SUPERSIZE.getColor(), null);
        screen.putString(startCol + 10, startRow++, " : +5 to snake length", TEXT_MENU_COLOR, null);
        startRow++;  // Empty line

        // Obstacles
        screen.putString(startCol, startRow++, "OBSTACLES:", Terminal.Color.GREEN, null);
        screen.putString(startCol, startRow, "  " + ObstacleType.INSTANTDEATH.getObstacleSymbol() + " Deadly", ObstacleType.INSTANTDEATH.getColor(), null);
        screen.putString(startCol + 10, startRow++, " : Instant death", TEXT_MENU_COLOR, null);

        screen.putString(startCol, startRow, "  " + ObstacleType.DEACREASESIZE.getObstacleSymbol() + " Decrease", ObstacleType.DEACREASESIZE.getColor(), null);
        screen.putString(startCol + 10, startRow++, " : Decreases snake size by 2", TEXT_MENU_COLOR, null);

        screen.putString(startCol, startRow, "  " + ObstacleType.INVISIBLESNAKE.getObstacleSymbol() + " Invisible", ObstacleType.INVISIBLESNAKE.getColor(), null);
        screen.putString(startCol + 10, startRow++, " : Makes snake invisible for 1 second", TEXT_MENU_COLOR, null);
        startRow++;  // Empty line

        // Special Features
        screen.putString(startCol, startRow++, "SPECIAL FEATURES:", Terminal.Color.GREEN, null);
        screen.putString(startCol, startRow++, "  • Snake wraps around borders", TEXT_MENU_COLOR, null);
        screen.putString(startCol, startRow++, "  • Game speed increases with each fruit eaten", TEXT_MENU_COLOR, null);
        startRow++;  // Empty line

        screen.putString(startCol, startRow++, "                     Press any key to start the game!                    ", Terminal.Color.GREEN, null);
        screen.refresh();

        // Wait for any key press
        while (readInput() == null) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void clearScreen() {
        // Clear the entire screen by filling with spaces
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                screen.putString(col, row, " ", null, null);
            }
        }
        screen.refresh();
    }
}
