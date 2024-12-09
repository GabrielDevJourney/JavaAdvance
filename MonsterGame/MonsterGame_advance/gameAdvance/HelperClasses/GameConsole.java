package gameAdvance.HelperClasses;

public class GameConsole {
	public static void printRoundInfo(String attackerName, String defenseName,
	                                  String attackerMonsterName, String defenseMonsterName,
	                                  int attackerHealth, int defenseHealth) {

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


	public static void displayPlayerHealthWhenHit(String defenseName, int healthBeforeHit){
		System.out.println(defenseName + "choose to take hit, health after hit HP : " + healthBeforeHit);
	}

	public static void printPlayerVsBotRoundInfo(String attackerName, String attackerMonsterName,
	                                             String defenseName, String defenseMonsterName,int attackMonsterHealth,
	                                             int defenseMonsterHealth) {

		System.out.println(attackerName + " is attacking with " + attackerMonsterName +
				" (HP: " + attackMonsterHealth + ")");
		System.out.println(defenseName + " defends with " + defenseMonsterName +
				" (HP: " + defenseMonsterHealth + ")");
	}


	public static void printBotMonsterSelection(String botName, String botMonsterName, int botMonsterHealth) {
		System.out.println(botName + " selected " +botMonsterName +
				" (HP: " + botMonsterHealth + ")");
	}



	public static void printPlayerMonsterSelection(String playerName, String playerMonsterName,
	                                               int playerMonsterHealth) {
		System.out.println(playerName + " selected " + playerMonsterName+
				" (HP: " + playerMonsterHealth + ")");
	}

	public static void printGameStart(String modeDescription) {
		System.out.println("\n=========================");
		System.out.println("Game Starting - Mode: " + modeDescription);
		System.out.println("=========================\n");
	}

	public static void printRoundStart(int roundNumber) {
		System.out.println("\n=========================");
		System.out.println("Round " + roundNumber);
		System.out.println("=========================");
	}

	public static void printRoundEnd() {
		System.out.println("\n-------------------------\n");
	}

	public static void printForObstacleRound(String obstacleName,
	                                         String attackerMonsterName, String defenseMonsterName,
	                                         int attackerHealth, int defenseHealth) {

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
