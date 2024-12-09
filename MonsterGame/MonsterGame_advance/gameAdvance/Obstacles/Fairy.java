package gameAdvance.Obstacles;

import gameAdvance.HelperClasses.GameConsole;
import gameAdvance.Monsters.Monster;
import gameAdvance.Player;
import gameAdvance.Supernatural;

public class Fairy extends Supernatural {

	public Fairy(){
		this.damage = 20;
		this.name = "Fairy";
	}

	@Override
	public void attack(Player attacker, Player defense, Monster attackerMonster, Monster defenseMonster) {

		attackerMonster.sufferHit(this.getDamage());
		defenseMonster.sufferHit(this.getDamage());

	}
}

