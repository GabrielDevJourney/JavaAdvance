package gameAdvance.Obstacles;

import gameAdvance.Monsters.Monster;
import gameAdvance.Player;
import gameAdvance.Strikeable;
import gameAdvance.Supernatural;

public class Witch extends Supernatural implements Strikeable {

	protected int health;
	protected boolean isDead;

	public Witch(){
		this.name = "Witch";
		this.damage = 40;
		this.health = 150;
	}

	public boolean isDead() {
		return isDead;
	}

	@Override
	public void attack(Player attacker, Player defense, Monster attackerMonster, Monster defenseMonster) {
		if(isDead){
			System.out.println(getName() + " is dead so will not attack anymore!");
		}else{

			attackerMonster.sufferHit(this.getDamage());
			defenseMonster.sufferHit(this.getDamage());

			this.sufferHit(attackerMonster.getDamage() + defenseMonster.getDamage());

			System.out.println(this.getName() + " health after hit HP: " + this.health);
		}
	}

	@Override
	public void sufferHit(int damage) {
		if (health - damage/2 <= 0) {
			health = 0;
			isDead = true;
		} else {
			health -= damage;
		}

	}

}
