package gameAdvance.Monsters;

import gameAdvance.Enums.TypeMonsters;

public class Werewolf extends Monster {

	@Override
	public void specialAbility() {

	}

	public Werewolf() {
		super(TypeMonsters.WEREWOLF);
	}

	@Override
	public void sufferHit(int damage) {
		super.sufferHit(damage);

	}
}
