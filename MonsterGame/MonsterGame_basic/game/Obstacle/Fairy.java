package game.Obstacle;

import game.HelperFunctions.GameConsole;
import game.Monsters.Monster;
import game.Player;
import game.Supernatural;

public class Fairy extends Supernatural {

	public Fairy() {
		this.damage = 20;
		this.name = "Fairy";
	}


	public void attack(int round, Player attacker, Player defense, Monster attackerMonster, Monster defenseMonster){

		GameConsole.printForObstacleRound(round,this.getName(), attackerMonster.getName(),
				defenseMonster.getName(),attackerMonster.getHealth(),
				defenseMonster.getHealth());


		attackerMonster.sufferHit(this.getDamage());
		defenseMonster.sufferHit(this.getDamage());


		GameConsole.printAfterDamageObstacleRound(attackerMonster.getName(),defenseMonster.getName(),
				attackerMonster.getHealth(),
				defenseMonster.getHealth());
	}

}


