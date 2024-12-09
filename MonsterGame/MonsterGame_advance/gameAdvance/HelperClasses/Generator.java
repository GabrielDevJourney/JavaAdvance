package gameAdvance.HelperClasses;

import gameAdvance.Enums.ObstacleType;
import gameAdvance.Game;
import gameAdvance.Monsters.Monster;
import gameAdvance.Obstacles.Witch;
import gameAdvance.Player;
import gameAdvance.Supernatural;

public class Generator {


	public static Monster generateRoundPick(Player player) {
		int randomIndex = Random.randomIndex(player);

		if (player.getCardsAlive() == null) {
			player.updateAliveCards();
		}

		Monster[] currentPlayerCards = player.getCardsAlive();
		return currentPlayerCards[randomIndex];

	}

	public static boolean generateDecisionToTakeDamage(){
		//40% change for player to take damage
		return Random.generateRandomNumber(10) <= 4;
	}

	public static Supernatural generateRandomObstacle() {
		//if there are 30% for fairy and 70% for Witch
		int change = Random.generateRandomNumber(100);

		if (Random.generateExistenceOfObstacle()) {

			ObstacleType type = change <= 30 ? ObstacleType.FAIRY : ObstacleType.Witch;

			switch (type) {
				case FAIRY -> {
					return Game.getObstacles()[0];
				}
				case Witch -> {
					if(!((Witch)Game.getObstacles()[1]).isDead()){
						return Game.getObstacles()[1];
					}
					else{
						return null;
					}
				}
			}
		}

		return null;
	}

}
