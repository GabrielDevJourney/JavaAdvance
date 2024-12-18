package academy.mindswap.gameobjects.fruit;

import academy.mindswap.Generatable;
import com.googlecode.lanterna.terminal.Terminal;

public enum FruitType implements Generatable {
	NORMAL(1, Terminal.Color.MAGENTA),
	DOUBLESIZE(2, Terminal.Color.GREEN),
	SUPERSIZE(5, Terminal.Color.YELLOW);

	private final int increaseSize;
	private final Terminal.Color color;

	FruitType(int increaseSize,Terminal.Color color) {
		this.increaseSize = increaseSize;
		this.color = color;
	}

	public Terminal.Color getColor() {
		return color;
	}

	public int getIncreaseSize() {
		return increaseSize;
	}
}
