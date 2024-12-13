package academy.mindswap.gameobjects;

import academy.mindswap.field.Position;

public class Obstacle {
	private final Position position;

	public Obstacle(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}
}
