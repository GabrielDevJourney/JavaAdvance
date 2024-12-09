package gameAdvance.Enums;

public enum TypeMonsters {
	VAMPIRE(1,100,18, "Vampire"),
	MUMMY(2,120,15, "Mummy"),
	WEREWOLF(3,140,20, "Werewolf");

	private final int id;
	private final int health;
	private final int damage;
	private final String name;

	private TypeMonsters(int id, int health, int damage, String name){
		this.id = id;
		this.health = health;
		this.damage = damage;
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public int getCurrentHealth() {
		return health;
	}

	public int getDamage() {
		return damage;
	}
}
