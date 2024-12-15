package academy.mindswap.gameobjects.Obstacle;

import academy.mindswap.field.Position;
import com.googlecode.lanterna.terminal.Terminal;

public class Obstacle {
	private final Position position;
	private final String obstacleSymbol;
	private int decreaseSize;
	private int timeIvisible;
	private boolean invisible;
	private boolean dead;
	private Terminal.Color color;
	private ObstacleType type;

	public Obstacle(Position position, ObstacleType type) {
		this.position = position;
		this.decreaseSize = type.getDecreaseSize();
		this.timeIvisible = type.getTimeIvisible();
		this.invisible = type.isInvisible();
		this.color = type.getColor();
		this.obstacleSymbol = type.getObstacleSymbol();
		this.type = type;
	}

	public Position getPosition() {
		return position;
	}

	public ObstacleType getType() {
		return type;
	}

	public int getDecreaseSize() {
		return decreaseSize;
	}

	public int getTimeIvisible() {
		return timeIvisible;
	}

	public boolean isInvisible() {
		return invisible;
	}

	public boolean isDead() {
		return dead;
	}

	public Terminal.Color getColor() {
		return color;
	}

	public String getObstacleSymbol() {
		return obstacleSymbol;
	}
}
