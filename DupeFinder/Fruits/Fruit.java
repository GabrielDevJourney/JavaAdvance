package Fruits;

import java.util.Objects;

public abstract class Fruit implements Comparable<Fruit> {
	private final int acidity;
	private final FruitType type;

	public Fruit(int acidity, FruitType type) {
		this.acidity = acidity;
		this.type = type;
	}

	@Override
	public int compareTo(Fruit o) {
		return o.acidity - this.acidity;
	}




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





	@Override
	public int hashCode() {
		return Objects.hash(acidity, type);
	}

	@Override
	public String toString() {
		return String.format("%-10s | Acidity: %-3d | Type: %s",
				getClass().getSimpleName(),
				acidity,
				type);
	}
}
