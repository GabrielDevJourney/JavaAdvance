package academy.mindswap.gameobjects.fruit;

import academy.mindswap.field.Position;
import com.googlecode.lanterna.terminal.Terminal;

public class Fruit {
    private final Position position;
    private final Terminal.Color color;
    private final int sizeToIncrease;


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
