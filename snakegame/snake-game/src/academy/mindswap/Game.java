package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;

import java.util.Random;


public class Game {

	private Snake snake;
	private Fruit fruit;
	private int gameplay_Row;
	private int gameplay_Col;
	private int delay;
	private final Random random;

	public Game(int cols, int rows, int delay) {
		Field.init(cols, rows);
		snake = new Snake(Direction.LEFT);
		this.delay = delay;
		random = new Random();
		//othewise my limits of game will be outside of game setting it to -1
		gameplay_Row = Field.getHeight() - 1;
		gameplay_Col = Field.getWidth() - 1;
	}

	public void start() throws InterruptedException {

		generateFruit();

		while (snake.isAlive()) {
			Thread.sleep(delay);
			Field.clearTail(snake);
			moveSnake();
			checkCollisions();
			Field.drawSnake(snake);
		}
	}

	private void moveSnake() {

		Key k = Field.readInput();

		if (k != null) {
			switch (k.getKind()) {
				case ArrowUp:
					snake.move(Direction.UP);
					return;

				case ArrowDown:
					snake.move(Direction.DOWN);
					return;

				case ArrowLeft:
					snake.move(Direction.LEFT);
					return;

				case ArrowRight:
					snake.move(Direction.RIGHT);
					return;
			}
		}
		snake.move();
	}

	private void checkCollisions() {
		Position head = snake.getHead();

		//border collision
		if (isBorder(head)) {
			snake.die();
		}



		//fruit collision
		if (fruit.getPosition().equals(snake.getFullSnake().getFirst())) {
			snake.increaseSize();
			generateFruit();

		}

		if (snake.getFullSnake().subList(1, snake.getFullSnake().size()).contains(head)) {
			snake.die();
			System.out.println("Self collision! Head at row: " + head.getRow() + ", col: " + head.getCol() +
					" hit body segment");
		}

	}

	private Position generatePosition(int rowMax, int colMax) {
		int row = randomNumber(1, rowMax);
		int col = randomNumber(1, colMax);

		return new Position(row, col);
	}

	private Position generateRandomObject(int rowMax, int colMax) {
		Position position = null;

		do {
			position = generatePosition(rowMax, colMax);

		} while (!isEmptyPosition(position));

		return position;
	}

	private int randomNumber(int min, int max) {
		return random.nextInt(max - min) + min;

	}

	private void generateFruit() {
		Position position = generateRandomObject(Field.getHeight(), Field.getWidth());
		fruit = new Fruit(position);
		Field.drawFruit(fruit);
	}

	private boolean isEmptyPosition(Position position) {
		return !(snake.getFullSnake().contains(position));
	}

	private boolean isBorder(Position position) {
		return position.getRow() == 0 || position.getRow() == gameplay_Row || position.getCol() == 0 || position.getCol() == gameplay_Col;
	}

}
