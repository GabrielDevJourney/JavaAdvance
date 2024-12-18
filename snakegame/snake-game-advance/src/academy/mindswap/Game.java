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
	private final int GAME_AVAILABLE_HEIGHT = Field.getHeight() - 1;
	private final int GAME_AVAILABLE_WIDTH = Field.getWidth() - 1;
	private final int CHANGE_50 = 50;
	private final int CHANGE_75 = 75;
	private int delay;
	private final Random random;
	private List<Position> allPositions;
	private final Map<Position, Obstacle> allObstacles = new HashMap<>();
	private Obstacle obstacle;
	public boolean restart;
	private boolean isPause = false;

	public Game(int cols, int rows, int delay) {
		Field.init(cols, rows);
		snake = new Snake(Direction.LEFT);
		this.delay = delay;
		random = new Random();
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
		return position.getRow() == 0 || position.getRow() == GAME_AVAILABLE_HEIGHT || position.getCol() == 0 || position.getCol() == GAME_AVAILABLE_WIDTH;
	}


	//* ALL GAME LOGIC METHODS *//

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
		Key userInput = Field.readInput();

		if (userInput != null) {
			// Handle pause first if true simply skip moving logic
			if (userInput.getKind() == Key.Kind.NormalKey &&
					(userInput.getCharacter() == 'P' || userInput.getCharacter() == 'p')) {
				isPause = !isPause;
				return;
			}

			// Handle movement if not paused
			if (!isPause) {
				switch (userInput.getKind()) {
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
		}
		if (!isPause) {
			snake.move();
		}
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

		if (change <= CHANGE_50) {
			return FruitType.NORMAL;
		} else if (change <= CHANGE_75) {
			return FruitType.DOUBLESIZE;
		}
		return FruitType.SUPERSIZE;
	}

	private ObstacleType generateObstacleType() {
		int change = randomNumber(1, 100);

		if (change <= CHANGE_50) {
			return ObstacleType.DEACREASESIZE;
		} else if (change <= CHANGE_75) {
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
		for (int row = 1; row < GAME_AVAILABLE_HEIGHT; row++) {
			for (int col = 1; col < GAME_AVAILABLE_WIDTH; col++) {
				allPositions.add(new Position(row, col));
			}
		}
	}

	public void wrapAround() {
		Position oldHead = snake.getHead();

		int col;
		int row;
		int colPossible = randomNumber(1, GAME_AVAILABLE_WIDTH);
		int rowPossible = randomNumber(1, GAME_AVAILABLE_HEIGHT);
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
				row = GAME_AVAILABLE_HEIGHT;
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
				col = GAME_AVAILABLE_WIDTH;
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
			Key userInput = Field.readInput();

			if (userInput != null) {
				switch (userInput.getKind()) {
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
