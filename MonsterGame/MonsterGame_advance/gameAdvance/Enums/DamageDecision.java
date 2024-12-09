package gameAdvance.Enums;

public enum DamageDecision {
	YES(1, "Yes"),
	NO(2, "No");

	private final int id;
	private final String description;

	DamageDecision(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static String getDamageDecision(int damageDecision) {
		for (DamageDecision decision : DamageDecision.values()) {
			if (decision.getId() == damageDecision) {
				return decision.getDescription();
			}
		}

		throw new IllegalArgumentException("This choice doesn't exist");
	}

	public static boolean finalDamageDecision(String decision){
		return decision.equals("Yes");
	}

	public static void displayDecisionOptions() {
		for (DamageDecision decision : DamageDecision.values()) {
			System.out.println(decision.getId() + " " + decision.getDescription());
		}
	}



}
