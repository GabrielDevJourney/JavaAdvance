package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;
import academy.mindswap.gameobjects.Obstacle.Obstacle;
import academy.mindswap.gameobjects.Obstacle.ObstacleType;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.fruit.FruitType;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;

import java.util.*;


public class Game {

	private final Snake snake;
	private Fruit fruit;
	private final int gameplay_Row;
	private final int gameplay_Col;
	private int delay;
	private final Random random;
	private List<Position> allPositions;
	private Map<Position, Obstacle> allObstacles = new HashMap<>();
	private Obstacle obstacle;
	public boolean restart = false;

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

	public Position getRandomAvailablePosition() {
		// Create a new list with current available positions
		List<Position> currentAvailablePositions = new ArrayList<>(allPositions);

		// Remove all snake positions from available positions
		for (Position snakePosition : snake.getFullSnake()) {
			currentAvailablePositions.remove(new Position(snakePosition.getRow(), snakePosition.getCol()));
		}

		if (currentAvailablePositions.isEmpty()) {
			// Handle the case where no positions are available
			return null;
		}

		// Get a random position from available positions
		int randomIndex = random.nextInt(currentAvailablePositions.size());
		return currentAvailablePositions.get(randomIndex);
	}

	private boolean isBorder(Position position) {
		return position.getRow() == 0 || position.getRow() == gameplay_Row || position.getCol() == 0 || position.getCol() == gameplay_Col;
	}


	//* ALL GAME LOGIC MEHODS *//

	public void start() throws InterruptedException {

		Field.gameMenu();
		Field.clearScreen();

		generateFruit();

		while (snake.isAlive()) {
			Thread.sleep(delay);

			handleInvisiblility();

			checkCollisions();
			Field.clearPosition(snake.getTail());
			moveSnake();
			Field.drawSnake(snake);

		}
	}

	private void handleInvisiblility() {
		if (snake.isIsvisible() && System.currentTimeMillis() > snake.getEndInvisibleTime()) {
			snake.setIsvisible(false);
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

	private void checkCollisions() throws InterruptedException {
		Position head = snake.getHead();

		//border collision
		if (checkWallCollision(head)) return;

		//fruit collision
		checkFruitCollision();

		//self collision
		checkSelfCollision(head);

		//obstacle collision
		checkObstacleCollision(head);

	}

	private boolean checkWallCollision(Position head) {
		if (isBorder(head)) {
			wrapAround();
			return true;
		}
		return false;
	}

	private void checkSelfCollision(Position head) throws InterruptedException {
		if (snake.getFullSnake().subList(1, snake.getFullSnake().size()).contains(head)) {
			snake.die();
			endGame();
		}
	}

	private void checkFruitCollision() {
		if (fruit.getPosition().equals(snake.getFullSnake().getFirst())) {
			snake.increaseSize(fruit.getSizeToIncrease());
			delay -= 5;
			generateFruit();
			generateObstacle();
		}
	}

	private void checkObstacleCollision(Position head) throws InterruptedException {
		Obstacle obstacle = allObstacles.get(head);

		if (obstacle != null) {
			ObstacleType obstacleType = obstacle.getType();
			switch (obstacleType) {
				case INVISIBLESNAKE:
					invisibleSnake(obstacleType);
					System.out.println("Snake became invisible");
					break;
				case DEACREASESIZE:
					deceraseSize(obstacleType);
					System.out.println("Snake size decreased");
					break;
				case INSTANTDEATH:
					instantDeath();
					System.out.println("Snake died instantly");
					break;
			}
			allObstacles.remove(head);
		}
	}


	private int randomNumber(int min, int max) {
		return random.nextInt(max - min) + min;
	}

	private void invisibleSnake(ObstacleType type) {
		snake.startInvisibility(type.getTimeIvisible());
		for (Position position : snake.getFullSnake()) {
			Field.clearPosition(position);
		}
	}

	private void instantDeath() throws InterruptedException {
		snake.die();
		endGame();
	}

	private void deceraseSize(ObstacleType type) {
		snake.decreaseSize(type.getDecreaseSize());
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

	public FruitType generateFruitType() {
		int change = randomNumber(1, 100);
		if (change <= 50) {
			return FruitType.NORMAL;
		} else if (change <= 75) {
			return FruitType.DOUBLESIZE;
		}
		return FruitType.SUPERSIZE;
	}

	private ObstacleType generateObstacleType() {
		int change = randomNumber(1, 100);
		if (change <= 50) {
			return ObstacleType.DEACREASESIZE;
		} else if (change <= 75) {
			return ObstacleType.INVISIBLESNAKE;
		}
		return ObstacleType.INSTANTDEATH;
	}

	private void generateObstacle() {
		Position position = getRandomAvailablePosition();

		if (position == null) {
			snake.die();
			return;
		}

		ObstacleType type = generateObstacleType();

		obstacle = new Obstacle(position, type);
		allObstacles.put(obstacle.getPosition(), obstacle);
		Field.drawObstacle(obstacle);

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

	public void wrapAround() {
		Position oldHead = snake.getHead();

		int col;
		int row;
		int colPossible = randomNumber(1, gameplay_Col);
		int rowPossible = randomNumber(1, gameplay_Row);
		int chooseBorder = randomNumber(1, 4);  // 1-4 for four possible borders

		switch (chooseBorder) {
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
		Field.clearPosition(snake.getTail());
		Field.clearHead(oldHead);

		Position newHeadPosition = new Position(row, col);
		snake.getFullSnake().removeFirst();
		snake.getFullSnake().addFirst(newHeadPosition);
		Field.drawSnake(snake);
	}

	private void endGame() throws InterruptedException {
		Field.gameOverMessage();

		while (true) {
			Thread.sleep(50);
			Key k = Field.readInput();

			if (k != null) {
				switch (k.getKind()) {
					case Enter: {
						restart = true;
						return;
					}
					case Backspace: {
						Field.closeTerminal();
					}
				}
			}
		}
	}

}
