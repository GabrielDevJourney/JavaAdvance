package game.Monsters;

public class Mummy extends Monster {

	//static so I can reset when other cards are played
	private int repeatedAttacks = 0;

	public Mummy() {
		super();
		health = 120;
		damage = 15;
		name = "Mummy";
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
		health -= penaltyDamage;
	}
}
