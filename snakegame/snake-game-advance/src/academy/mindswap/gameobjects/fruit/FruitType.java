package academy.mindswap.gameobjects.fruit;

import com.googlecode.lanterna.terminal.Terminal;

public enum FruitType {
	NORMAL(1, Terminal.Color.RED),
	DOUBLESIZE(2, Terminal.Color.BLUE),
	SUPERSIZE(5, Terminal.Color.YELLOW);

	private int increaseSize;
	private Terminal.Color color;

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
