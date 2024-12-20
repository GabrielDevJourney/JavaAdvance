package academy.mindswap.gameobjects.snake;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;

import java.util.LinkedList;

public class Snake {

	private final static int SNAKE_INITIAL_SIZE = 3;
	private Direction direction;
	private boolean alive;
	//instead of having more variables i can use whole body to get both head last position and tail first one
	private LinkedList<Position> body;
	private boolean isIsvisible;
	private long endInvisibleTime = 0;

	public Snake(Direction direction) {
		this.body = new LinkedList<>();
		initSnakeInitialSize();
		this.direction = direction;
		alive = true;
		this.isIsvisible = false;
	}

	public LinkedList<Position> getFullSnake() {
		return body;
	}

	public long getEndInvisibleTime() {
		return endInvisibleTime;
	}

	public Position getHead() {
		return body.getFirst();
	}

	public Position getTail() {
		return body.getLast();
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setEndInvisibleTime(long endInvisibleTime) {
		this.endInvisibleTime = endInvisibleTime;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isIsvisible() {
		return isIsvisible;
	}

	public void setIsvisible(boolean isvisible) {
		isIsvisible = isvisible;
	}

	private void initSnakeInitialSize() {
		for (int i = 0; i < SNAKE_INITIAL_SIZE; i++) {
			body.add(new Position(4, i + 20));
		}
	}

	public void increaseSize(int sizeToGrow) {
		Position tail = getTail();
		for (int i = 0; i < sizeToGrow; i++) {
			body.addLast(new Position(tail.getRow(), tail.getCol()));

		}
	}

	public void decreaseSize(int sizeToShrink) {
		for (int i = 0; i < sizeToShrink; i++) {
			if (body.size() > 1) {

				Position positionToClear = body.removeLast();
				Field.clearPosition(positionToClear);
			}
		}
	}

	public void move(Direction newDirection) {
		//create empty snake head
		Position head = getHead();

		if ((newDirection == Direction.UP && direction == Direction.DOWN) ||
				(newDirection == Direction.DOWN && direction == Direction.UP) ||
				(newDirection == Direction.LEFT && direction == Direction.RIGHT) ||
				(newDirection == Direction.RIGHT && direction == Direction.LEFT)) {
			// Keep current direction
			newDirection = direction;
		}

		//new head position based on diretion change
		//down - up nothing up - down nothing
		switch (newDirection) {
			case UP:
				head = new Position(head.getRow() - 1, head.getCol());
				break;
			case DOWN:
				head = new Position(head.getRow() + 1, head.getCol());
				break;
			case LEFT:
				head = new Position(head.getRow(), head.getCol() - 1);
				break;
			case RIGHT:
				head = new Position(head.getRow(), head.getCol() + 1);
				break;
		}

		direction = newDirection;
		body.removeLast();
		body.addFirst(head);
	}

	public void move() {
		move(direction);
	}

	public void die() {
		alive = false;
	}

	public void startInvisibility(int durationInSeconds) {
		this.isIsvisible = true;  // Make snake invisible
		this.endInvisibleTime = System.currentTimeMillis() + (durationInSeconds * 1000);
	}

}

