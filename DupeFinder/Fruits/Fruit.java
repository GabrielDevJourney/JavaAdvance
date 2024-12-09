package Fruits;

import java.util.Objects;

public abstract class Fruit implements Comparable<Fruit> {
	private int acidity;
	private FruitType type;

	public Fruit(int acidity, FruitType type) {
		this.acidity = acidity;
		this.type = type;
	}

	@Override
	public int compareTo(Fruit o) {
		return o.acidity - this.acidity;
	}
	//equals override

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Fruit fruit = (Fruit) o;
		return acidity == fruit.acidity && this.type == fruit.type;
	}

	//hashset override
	@Override
	public int hashCode() {
		return Objects.hash(acidity, type);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " acidity: " + acidity + ", type: " + type;
	}
}
