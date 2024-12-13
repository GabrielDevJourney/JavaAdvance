package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.fruit.FruitType;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game {

	private Snake snake;
	private Fruit fruit;
	private int gameplay_Row;
	private int gameplay_Col;
	private int delay;
	private final Random random;
	private List<Position> allPositions;

	public Game(int cols, int rows, int delay) {
		Field.init(cols, rows);
		snake = new Snake(Direction.LEFT);
		this.delay = delay;
		random = new Random();
		//othewise my limits of game will be outside of game setting it to -1
		gameplay_Row = Field.getHeight() - 1;
		gameplay_Col = Field.getWidth() - 1;
		populateListOfAllPositions();
	}

	public void start() throws InterruptedException {

		generateFruit();

		while (snake.isAlive()) {
			Thread.sleep(delay);
			checkCollisions();
			Field.clearTail(snake);
			moveSnake();
			//after collision draw walls again so they dont disapear
			Field.drawWalls();
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
			wrapAround();
			return;
		}

		//fruit collision
		if (fruit.getPosition().equals(snake.getFullSnake().getFirst())) {
			snake.increaseSize(fruit.getSizeToIncrease());
			generateFruit();

		}

		if (snake.getFullSnake().subList(1, snake.getFullSnake().size()).contains(head)) {
			snake.die();
			System.out.println("Self collision! Head at row: " + head.getRow() + ", col: " + head.getCol() +
					" hit body segment");
		}

	}


	private int randomNumber(int min, int max) {
		return random.nextInt(max - min) + min;
	}

	private void generateFruit() {
		Position position = getRandomAvailablePosition();
		if (position == null) {
			snake.die();
			return;
		}
		FruitType type = generateFruitType();
		fruit = new Fruit(position, type);
		Field.drawFruit(fruit);
	}

	private boolean isBorder(Position position) {
		return position.getRow() == 0 || position.getRow() == gameplay_Row || position.getCol() == 0 || position.getCol() == gameplay_Col;
	}

	private void populateListOfAllPositions() {
		allPositions = new ArrayList<>();
		//will not allow spawns in "boarder" even tho game will be warp-around
		for (int row = 1; row < gameplay_Row; row++) {
			for (int col = 1; col < gameplay_Col; col++) {
				allPositions.add(new Position(row, col));
			}
		}
	}

	public FruitType generateFruitType() {
		int change = randomNumber(1, 100);
		if (change <= 50) {
			return FruitType.NORMAL;
		} else if (change <= 75) {
			return FruitType.DOUBLESIZE;
		}
		return FruitType.SUPERSIZE;
	}

	public Position getRandomAvailablePosition() {
		// Create a new list with current available positions
		List<Position> currentAvailable = new ArrayList<>(allPositions);

		// Remove all snake positions from available positions
		for (Position snakePos : snake.getFullSnake()) {
			currentAvailable.removeIf(position -> position.getRow() == snakePos.getRow()
					&& position.getCol() == snakePos.getCol());
		}

		if (currentAvailable.isEmpty()) {
			// Handle the case where no positions are available
			return null;
		}

		// Get a random position from available positions
		int randomIndex = random.nextInt(currentAvailable.size());
		return currentAvailable.get(randomIndex);
	}

	public void wrapAround(){
		int col;
		int row;
		int colPossible = randomNumber(1, gameplay_Col - 1);
		int rowPossible = randomNumber(1, gameplay_Row - 1);
		int chooseBorder = randomNumber(1, 4);  // 1-4 for four possible borders

		switch(chooseBorder) {
			case 1:  // Top border
				row = 0;
				col = colPossible;
				//instead of using move to set direction use setdiretion otherwise might not
				//move curretly since here would change .move dirextion so move itself wuld be called 2 times
				//in different places
				snake.setDirection(Direction.DOWN);
				break;
			case 2:  // Bottom border
				row = gameplay_Row;
				col = colPossible;
				snake.setDirection(Direction.UP);

				break;
			case 3:  // Left border
				row = rowPossible;
				col = 0;
				snake.setDirection(Direction.RIGHT);
				break;
			default:  // Right border
				row = rowPossible;
				col = gameplay_Col;
				snake.setDirection(Direction.LEFT);
				break;
		}

		System.out.println(row + " " + col);

		//need to cleat tail like it done in move method otherwise tail trail will be left behind
		Field.clearTail(snake);
		Position newHeadPosition = new Position(row,col);
		snake.getFullSnake().removeFirst();
		snake.getFullSnake().addFirst(newHeadPosition);
		Field.drawSnake(snake);
	}
}
