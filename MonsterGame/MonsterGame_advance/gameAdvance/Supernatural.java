package gameAdvance;

import gameAdvance.Monsters.Monster;

public abstract class Supernatural {
	protected  int damage;
	protected  String name;

	public int getDamage() {
		return damage;
	}

	public String getName() {
		return name;
	}

	public void attack(Monster defenseMonster){

	}

	public void attack(Player attacker, Player defense, Monster attackerMonster, Monster defenseMonster){

	}
}
