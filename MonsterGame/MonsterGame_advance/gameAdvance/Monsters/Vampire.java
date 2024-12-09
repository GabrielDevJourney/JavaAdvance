package gameAdvance.Monsters;

import gameAdvance.Enums.TypeMonsters;

public class Vampire extends Monster {

	private int randomBiteChange;

	public Vampire(int randomBiteChange) {
		super(TypeMonsters.VAMPIRE);
		this.randomBiteChange = randomBiteChange;
	}

	@Override
	public void specialAbility() {
		bite();
	}

	//to utilize in game I must return if vampire will bite or not so the action will occur in game
	public void bite() {
		//25% change of biting, also checking if health is near 85 ensure that health doesn't go above is max health
		if (randomBiteChange <= 25 && currentHealth <= 85) {
			int healthBack = 15;
			currentHealth += healthBack;
		}
	}

	@Override
	public void sufferHit(int damage) {
		super.sufferHit(damage);

	}
}
