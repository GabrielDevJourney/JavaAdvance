package gameAdvance.HelperClasses;

import gameAdvance.Enums.DamageDecision;
import gameAdvance.Monsters.Monster;
import gameAdvance.Scanner.GameScannerManager;
import gameAdvance.Player;
import gameAdvance.Supernatural;

public class TurnHandler {


	public void handlePlayerVsPlayerTurn(Player attacker, Player defense) {

		Monster attackerMonster = getUserMonsterChoice(attacker);
		System.out.println(attacker.getName() + " choose " + attackerMonster.getName());

		boolean defenseDecision = DamageDecision.finalDamageDecision(GameScannerManager.handleDamageDecision(defense.getName()));

		if (defenseDecision) {
			defense.decreaseHealth(attackerMonster.getDamage());
			System.out.println(defense.getName() + " was hit and now has HP: " + defense.getHealth());
		} else {
			Monster defenseMonster = getUserMonsterChoice(defense);
			defenseMonster.sufferHit(attackerMonster.getDamage());
			System.out.println(defenseMonster.getName() + " was hit remaing HP: " + defenseMonster.getCurrentHealth());
		}


	}

	public void handleBotTurnBotVsBot(Player attacker, Player defense) {

		Monster attackerMonsterChoice = Generator.generateRoundPick(attacker);
		Monster defenseMonsterChoice = Generator.generateRoundPick(defense);
		boolean damageDecision = Generator.generateDecisionToTakeDamage();


		GameConsole.printRoundInfo(attacker.getName(), defense.getName(),
				attackerMonsterChoice.getName(), defenseMonsterChoice.getName(),
				attackerMonsterChoice.getCurrentHealth(), defenseMonsterChoice.getCurrentHealth());

		if (damageDecision) {
			dealDamage(attackerMonsterChoice, null, defense, true);
		} else {
			dealDamage(attackerMonsterChoice, defenseMonsterChoice, defense, false);
		}


		if(defense.hasNoCards()){
			defense.setHasLost(true);
		}

	}

	public void handleBotVsPlayerTurn(Player attacker, Player defense) {

		if (attacker.isAttacking()) {
			Monster botMonster = Generator.generateRoundPick(defense);

			//*GET USER MONSTER CHOICE
			Monster attackerMonster = getUserMonsterChoice(attacker);

			GameConsole.printPlayerVsBotRoundInfo(attacker.getName(), attackerMonster.getName(), defense.getName(),
					botMonster.getName(), attackerMonster.getCurrentHealth(), botMonster.getCurrentHealth());

			boolean damageDecisionBot = Generator.generateDecisionToTakeDamage();

			//decision of getting hit or let monster be hit
			if (damageDecisionBot) {
				dealDamage(attackerMonster, null, defense, true);
			} else {
				dealDamage(attackerMonster, botMonster, defense, false);
			}


		} else {

			Monster botMonster = Generator.generateRoundPick(defense);
			GameConsole.printBotMonsterSelection(defense.getName(), botMonster.getName(),
					botMonster.getCurrentHealth());


			//*GET USER MOSNTER CHOICE
			Monster attackerMonster = getUserMonsterChoice(attacker);


			GameConsole.printPlayerMonsterSelection(attacker.getName(), attackerMonster.getName(),
					attackerMonster.getCurrentHealth());

			boolean attackerDecision =
					DamageDecision.finalDamageDecision(GameScannerManager.handleDamageDecision(attacker.getName()));

			if (attackerDecision) {
				dealDamage(botMonster, null, attacker, true);
			} else {
				dealDamage(botMonster, attackerMonster, attacker, false);
			}

		}

	}


	public void handleTurnWithObstaclePlayervsBot(Supernatural obstacle, Player attacker,
	                                              Player defense){

		Monster attackerMonster = getUserMonsterChoice(attacker);
		Monster defenseMonster = Generator.generateRoundPick(defense);

		GameConsole.printForObstacleRound(obstacle.getName(), attackerMonster.getName(), defenseMonster.getName(),
				attackerMonster.getCurrentHealth(), defenseMonster.getCurrentHealth());

		obstacle.attack(attacker,defense,attackerMonster,defenseMonster);


		attacker.updatePlayerCardsState(attackerMonster);
		defense.updatePlayerCardsState(defenseMonster);

		GameConsole.printAfterDamageObstacleRound(attackerMonster.getName(), defenseMonster.getName(),
				attackerMonster.getCurrentHealth(), defenseMonster.getCurrentHealth());


	}

	//this one handles when exist 2 players
	public void handleTurnWithObstaclePlayerVsPlayer(Supernatural obstacle, Player attacker,
	                                   Player defense
	) {

		Monster attackerMonster = getUserMonsterChoice(attacker);
		Monster defenseMonster = getUserMonsterChoice(defense);

		GameConsole.printForObstacleRound(obstacle.getName(), attackerMonster.getName(), defenseMonster.getName(),
				attackerMonster.getCurrentHealth(), defenseMonster.getCurrentHealth());

		obstacle.attack(attacker, defense, attackerMonster, defenseMonster);


		attacker.updatePlayerCardsState(attackerMonster);
		defense.updatePlayerCardsState(defenseMonster);

		GameConsole.printAfterDamageObstacleRound(attackerMonster.getName(), defenseMonster.getName(),
				attackerMonster.getCurrentHealth(), defenseMonster.getCurrentHealth());

	}

	public void handleTurnWithObstacleBotVsBot(Supernatural obstacle, Player attacker, Player defense){
		Monster attackerMonster = Generator.generateRoundPick(attacker);
		Monster defenseMonster = Generator.generateRoundPick(defense);

		GameConsole.printForObstacleRound(obstacle.getName(), attackerMonster.getName(), defenseMonster.getName(),
				attackerMonster.getCurrentHealth(), defenseMonster.getCurrentHealth());

		obstacle.attack(attacker,defense,attackerMonster,defenseMonster);

		GameConsole.printAfterDamageObstacleRound(attackerMonster.getName(), defenseMonster.getName(),
				attackerMonster.getCurrentHealth(), defenseMonster.getCurrentHealth());

	}

	//*DEAL DAMAGE LOGIC
	private void dealDamage(Monster attackerMonster, Monster defenseMonster, Player defense, boolean randomDecision) {

		if (randomDecision) {

			//if true player take damage else take monster damage
			defense.decreaseHealth(attackerMonster.getDamage());


			GameConsole.displayPlayerHealthWhenHit(defense.getName(), defense.getHealth());

		} else {

			System.out.println(defense.getName() + " choose to let monster take the hit");

			attackerMonster.specialAbility();
			defenseMonster.sufferHit(attackerMonster.getDamage());

			//!terminal
			GameConsole.printDamageInfo(attackerMonster.getDamage(),
					defenseMonster.getName(), defenseMonster.getCurrentHealth());

		}

	}


	//*METHODS FOR FIND AND RETURN ACTUAL USER MONSTER CHOICE

	private Monster findMonsterById(Player player, int userChoice) {
		Monster[] currentAliveCards = player.getCardsAlive();
		for (Monster currentMonster : currentAliveCards) {
			if (userChoice == currentMonster.getId()) {
				return currentMonster;
			}
		}
		throw new IllegalArgumentException("That monster isn't available");
	}

	private Monster getUserMonsterChoice(Player player) {
		int userChoice = GameScannerManager.handleMonsterChoice(player);
		return findMonsterById(player, userChoice);
	}


}
