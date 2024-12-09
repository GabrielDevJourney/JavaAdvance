package game.HelperFunctions;

import game.Enums.ObstacleType;
import game.Enums.TypeMonsters;
import game.Game;
import game.Monsters.Monster;
import game.Monsters.Mummy;
import game.Monsters.Vampire;
import game.Monsters.Werewolf;
import game.Obstacle.Fairy;
import game.Obstacle.Witch;
import game.Player;
import game.Supernatural;

public class Generator {
	public static Monster generateRandomDealCard() {
		int size = TypeMonsters.values().length;
		TypeMonsters randomNumberToDealCard = TypeMonsters.values()[Random.generateRandomNumber(size) - 1];

		switch (randomNumberToDealCard) {
			case WEREWOLF -> {
				return new Werewolf();
			}
			case MUMMY -> {
				return new Mummy();
			}
			case VAMPIRE -> {
				return new Vampire();
			}
		}
		throw new IllegalArgumentException("Type not found!");
	}

	public static Monster generateRandomRoundPick(Player player) {
		int randomIndex = Random.generateIndex(player);

		if (player.getCardsAlive() == null) {
			player.updateAliveCards();
		}

		Monster[] currentPlayerCards = player.getCardsAlive();
		return currentPlayerCards[randomIndex];

	}

	public static Supernatural generateRandomObstacle() {
		//if there are 30% for fairy and 70% for Witch
		int change = Random.generateRandomNumber(100);

		if (Random.generateExistenceOfObstacle()) {

			ObstacleType type = change <= 30 ? ObstacleType.FAIRY : ObstacleType.Witch;

			switch (type) {
				case FAIRY -> {
					return Game.obstacles[0];
				}
				case Witch -> {
					if(!((Witch)Game.obstacles[1]).isDead()){
						return Game.obstacles[1];
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
