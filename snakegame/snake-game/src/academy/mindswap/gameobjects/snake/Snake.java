package academy.mindswap.gameobjects.snake;

import academy.mindswap.field.Position;

import java.util.LinkedList;

public class Snake {

	private final static int SNAKE_INITIAL_SIZE = 3;
	private Direction direction;
	private boolean alive;
	//instead of having more variables i can use whole body to get both head last position and tail first one
	private LinkedList<Position> body;

	public Snake(Direction direction) {
		this.body = new LinkedList<>();
		initSnakeInitialSize();
		this.direction = direction;
		alive = true;
	}

	public int getSnakeSize() {
		return body.size();
	}

	public LinkedList<Position> getFullSnake() {
		return body;
	}

	public Position getHead() {
		return body.getFirst();
	}

	public Position getTail() {
		return body.getLast();
	}

	public Direction getDirection() {
		return direction;
	}

	public boolean isAlive() {
		return alive;
	}

	private void initSnakeInitialSize() {
		for (int i = 0; i < SNAKE_INITIAL_SIZE; i++) {
			body.add(new Position(4, i + 20));
		}
	}

	public void increaseSize() {
		Position tail = getTail();
		body.addLast(new Position(tail.getRow(), tail.getCol()));
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
		System.out.println("Snake died!");
	}

}

