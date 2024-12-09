package game.HelperFunctions;

//todo print player vida cada round

public class GameConsole {
	public static void printRoundInfo(int round, String attackerName, String defenseName,
	                                  String attackerMonsterName, String defenseMonsterName,
	                                  int attackerHealth, int defenseHealth) {

		System.out.println("\nRound: " + round);
		System.out.println("This round no obstacles are playing");
		System.out.println(attackerName + " is attacking and " + defenseName + " is being attacked!");
		System.out.println(attackerName + " monster: " + attackerMonsterName + " - HP: " + attackerHealth);
		System.out.println(defenseName + " monster: " + defenseMonsterName + " - HP: " + defenseHealth);
	}

	public static void printDamageInfo(int attackerDamage,
	                                   String defenseMonsterName,
	                                   int defenseHealth) {
		System.out.println("Damage dealt to " + defenseMonsterName + " " + attackerDamage + " damage");
		System.out.println(defenseMonsterName + " health after hit - HP: " + defenseHealth);
	}

	public static void announceWinner(String winnerName, String loserName) {
		System.out.println(winnerName + " wins! " + loserName + " has lost!");
	}

	public static void printForObstacleRound(int round, String obstacleName,
	                               String attackerMonsterName, String defenseMonsterName,
	                               int attackerHealth, int defenseHealth) {

		System.out.println("\nRound: " + round);
		System.out.println("This round " + obstacleName + " is playing!");
		System.out.println("No monster will attack they will only both be attacked!");

		System.out.println(obstacleName + " has attacked " + attackerMonsterName + " and " + defenseMonsterName);

		System.out.println(attackerMonsterName + " health HP: " + attackerHealth);
		System.out.println(defenseMonsterName + " health HP: " + defenseHealth);

	}

	public static void printAfterDamageObstacleRound(String attackerMonsterName, String defenseMonsterName,
	                                                 int attackerHealth,
	                                                int defenseHealth){
		System.out.println(attackerMonsterName + " health after hit HP: " + attackerHealth);
		System.out.println(defenseMonsterName + " health after hit HP: " + defenseHealth);
	}
}
