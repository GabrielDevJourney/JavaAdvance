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
        this.direction = direction;
    }

    public int getSnakeSize() {
        return body.size();
    }

    public LinkedList<Position> getFullSnake(){
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



    public void increaseSize() {
        Position tail = getTail();
        body.addLast(new Position(tail.getRow(), tail.getCol()));
    }

    public void move(Direction direction) {
        //todo
        //create snake head
        Position head = getHead();

        //new head position based on diretion change
        switch (direction) {
            case UP:
                head = new Position(head.getRow(), head.getCol() - 1);
                break;
            case DOWN:
                head = new Position(head.getRow(), head.getCol() + 1);
                break;
            case LEFT:
                head = new Position(head.getRow() - 1, head.getCol());
                break;
            case RIGHT:
                head = new Position(head.getRow() + 1, head.getCol());
                break;
        }
        //store the actual head in body
        body.addFirst(head);
    }

    public void move(){
        move(direction);
    }

    public void die() {
        alive = false;
    }

}

