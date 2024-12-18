package academy.mindswap.gameobjects.Obstacle;

import academy.mindswap.Generatable;
import com.googlecode.lanterna.terminal.Terminal;

public enum ObstacleType implements Generatable {
	INSTANTDEATH(0, false, 0, Terminal.Color.RED),
	DEACREASESIZE(2, false, 0, Terminal.Color.WHITE),
	INVISIBLESNAKE(0, true, 1, Terminal.Color.BLUE);

	private final String obstacleSymbol = "\u2622";
	private int decreaseSize;
	private int timeIvisible;
	private boolean invisible;
	private Terminal.Color color;


	ObstacleType(int decreaseSize, boolean invisible, int timeIvisible, Terminal.Color color) {
		this.decreaseSize = decreaseSize;
		this.invisible = invisible;
		this.timeIvisible = timeIvisible;
		this.color = color;
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

	public Terminal.Color getColor() {
		return color;
	}

	public String getObstacleSymbol() {
		return obstacleSymbol;
	}
}
