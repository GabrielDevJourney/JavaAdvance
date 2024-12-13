package academy.mindswap.gameobjects.fruit;

import academy.mindswap.field.Position;
import com.googlecode.lanterna.terminal.Terminal;

public class Fruit {
    private Position position;
    private Terminal.Color color;
    private int sizeToIncrease;


    public Fruit(Position position, FruitType type) {
        this.position = position;
        this.color = type.getColor();
        this.sizeToIncrease = type.getIncreaseSize();
    }

    public Position getPosition() {
        return position;
    }

    public Terminal.Color getColor() {
        return color;
    }

    public int getSizeToIncrease() {
        return sizeToIncrease;
    }
}
