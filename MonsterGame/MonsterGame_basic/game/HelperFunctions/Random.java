package game.HelperFunctions;

import game.Player;

public class Random {

	public static int generateRandomNumber(int range){
		return (int)(Math.random() * range + 1);
	}

	public static int generateIndex(Player player) {
		return (int) (Math.random() * player.getAliveIndexCounter());
	}

	//are there Obstacles true 1 or false 2
	public static boolean generateExistenceOfObstacle() {
		return generateRandomNumber(10)  <= 4;
	}

}
