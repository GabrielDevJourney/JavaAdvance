package academy.mindswap.field;

import academy.mindswap.gameobjects.Obstacle.Obstacle;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

public final class Field {

    private static final String SNAKE_BODY_STRING = "\u2605";
    private static final String SNAKE_HEAD_STRING = "\u2666";
    private static final String INVIIBLE_SNAKE_BODY_STRING = " ";
    private static final String INVISIBLE_SNAKE_HEAD_STRING = " ";
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

    public static void clearTail(Position position) {
        screen.putString(position.getCol(), position.getRow(), " ", null, null);
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
}
