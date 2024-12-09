package gameAdvance.Monsters;

import gameAdvance.Enums.TypeMonsters;

public class Mummy extends Monster {

	//static so I can reset when other cards are played
	private int repeatedAttacks = 0;

	public Mummy() {
		super(TypeMonsters.MUMMY);
	}

	@Override
	public void specialAbility() {
		if (canPlayAgain()) {
			increaseRepeatedAttacks();
		} else {
			penaltyDamage();
			resetRepeatedAttacks();

		}
	}

	public boolean canPlayAgain() {
		return repeatedAttacks != 3;
	}

	public void increaseRepeatedAttacks() {
		repeatedAttacks++;
	}

	public void resetRepeatedAttacks() {
		repeatedAttacks = 0;
	}

	public void penaltyDamage() {
		int penaltyDamage = 10;
		currentHealth -= penaltyDamage;
	}

	@Override
	public void sufferHit(int damage) {
		super.sufferHit(damage);

	}
}
