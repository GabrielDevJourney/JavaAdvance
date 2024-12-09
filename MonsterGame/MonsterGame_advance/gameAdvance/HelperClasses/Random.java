package gameAdvance.HelperClasses;

import gameAdvance.Enums.TypeMonsters;
import gameAdvance.Player;

public class Random {



	public static int generateRandomNumber(int range){
		return (int)(Math.random() * range + 1);
	}

	public static int randomIndex(Player player) {
		return (int) (Math.random() * player.getAliveIndexCounter());
	}

	public static int randomTypeMonstersIndex(){
		return (int)(Math.random() * TypeMonsters.values().length);
	}

	public static boolean generateExistenceOfObstacle(){
		return generateRandomNumber(10) <= 4;
	}
}
