package game;

import game.Monsters.Monster;

public class Supernatural {

	protected String name;
	protected int damage;

	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}

	//for both fairy and Witch since they can attack both cards
	public void attack(int round, Player attacker, Player defense,Monster attackerMonster, Monster defenseMonster){

	}

	//for class mosnter since there will only be one mosnter attacked
	public void attack(Monster defenseMonster){

	}
}
