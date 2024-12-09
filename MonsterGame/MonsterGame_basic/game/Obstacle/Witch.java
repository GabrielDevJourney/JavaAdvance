package game.Obstacle;

import game.HelperFunctions.GameConsole;
import game.Monsters.Monster;
import game.Player;
import game.Strikeable;
import game.Supernatural;

public class Witch extends Supernatural implements Strikeable {

	protected int health;
	protected boolean isDead;

	public Witch() {
		this.name = "Witch";
		this.damage = 40;
		this.health = 150;
	}

	public boolean isDead() {
		return isDead;
	}

	public void attack(int round, Player attacker, Player defense, Monster attackerMonster, Monster defenseMonster) {

		if (isDead()) {

			System.out.println(getName() + " is dead so will not attack anymore!!");

		} else {

			//print health before hit also
			GameConsole.printForObstacleRound(round, this.getName(), attackerMonster.getName(),
					defenseMonster.getName(), attackerMonster.getHealth(),
					defenseMonster.getHealth());


			attackerMonster.sufferHit(this.getDamage());
			defenseMonster.sufferHit(this.getDamage());


			//print health after hit
			GameConsole.printAfterDamageObstacleRound(attackerMonster.getName(), defenseMonster.getName(),
					attackerMonster.getHealth(),
					defenseMonster.getHealth());


			this.sufferHit(attackerMonster.getDamage() + defenseMonster.getDamage());


			System.out.println(this.getName() + " health after hit HP: " + this.health);
		}
	}

	@Override
	public void sufferHit(int damage) {
		this.updateCurrentHealth(damage);
	}

	@Override
	public void updateCurrentHealth(int damage) {
		if (health - damage / 2 < 0) {
			health = 0;
			isDead = true;
		} else {
			health -= damage;
		}
	}
}
